package PaooGame.Menu.Buttons;
import PaooGame.Mechanics.TransformComponent;
import PaooGame.Menu.ButtonInterface;
import PaooGame.Menu.ButtonState;
import PaooGame.RefLinks;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class AbstractButton implements ButtonInterface
{
    /*! \class AbstractButton
    \brief Clasa abstracta pentru implementarea unui buton.
 */
    protected final Clip RELEASED;
    protected final Clip ONBUTTON;

    protected int BUTTONWIDTH;
    protected int BUTTONHEIGHT;

    protected RefLinks refLinks;
    protected BufferedImage[] buttonStatesImage;
    public TransformComponent transformComponent;
    private ButtonState buttonState;
    protected String name;
    private final static int NAMESIZE = 35;

    public AbstractButton(RefLinks refLinks, BufferedImage[] buttonStatesImage, String name, int x, int y, Clip onbutton, Clip released)
    {
        this.refLinks = refLinks;
        this.buttonStatesImage = buttonStatesImage;
        this.transformComponent = new TransformComponent(x,y);
        this.name = name;

        ONBUTTON = onbutton;
        RELEASED = released;

    }

    @Override
    public boolean isOnButton() {
        return refLinks.getMouseInput().getMouseX() >= transformComponent.getPositionx() && refLinks.getMouseInput().getMouseX() <= transformComponent.getPositionx() + transformComponent.getWidth() && (refLinks.getMouseInput().getMouseY() >= transformComponent.getPositiony() && refLinks.getMouseInput().getMouseY() <= transformComponent.getPositiony() + transformComponent.getHeight());
    }

    @Override
    public boolean isPressed()
    {
        return isOnButton() && refLinks.getMouseInput().getPressedMouseKey() != null && refLinks.getMouseInput().getPressedMouseKey().getButton() == MouseEvent.BUTTON1;
    }

    @Override
    public boolean isReleased() {
        return isOnButton() && refLinks.getMouseInput().getReleasedMouseKey() != null && refLinks.getMouseInput().getReleasedMouseKey().getButton() == MouseEvent.BUTTON1;
    }



    @Override
    public void Update() {

        buttonState = ButtonState.NONE;
        if(isOnButton()) buttonState = ButtonState.ONBUTTON;
        if(isPressed()) buttonState = ButtonState.PRESSED;
        if(isReleased()) buttonState = ButtonState.RELEASED;

    }

    @Override
    public void Draw(Graphics g) {

        if(buttonState == null)
        {
            buttonState = ButtonState.NONE;
        }

        switch (buttonState)
        {
            case NONE:
                g.drawImage(this.buttonStatesImage[1], (int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), (int)transformComponent.getWidth(), (int)transformComponent.getHeight(), null);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, NAMESIZE));
                g.drawString(this.name, (int)(transformComponent.getPositionx() + transformComponent.getWidth()/2 - NAMESIZE * 2), (int)(transformComponent.getPositiony() + transformComponent.getHeight()/1.5));
                break;

            case ONBUTTON:
                g.drawImage(this.buttonStatesImage[2], (int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), (int)transformComponent.getWidth(), (int)transformComponent.getHeight(), null);
                g.setColor(Color.lightGray);
                g.setFont(new Font("Arial", Font.BOLD, NAMESIZE));
                g.drawString(this.name, (int)(transformComponent.getPositionx() + transformComponent.getWidth()/2 - NAMESIZE * 2), (int)(transformComponent.getPositiony() + transformComponent.getHeight()/1.5));
                break;

            case PRESSED:
                g.drawImage(this.buttonStatesImage[0], (int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), (int)transformComponent.getWidth(), (int)transformComponent.getHeight(), null);
                break;

            case RELEASED:

                g.drawImage(this.buttonStatesImage[2], (int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), (int)transformComponent.getWidth(), (int)transformComponent.getHeight(), null);
                break;
        }

        refLinks.getMouseInput().setReleasedMouseKey(null);
    }
}
