package PaooGame.Menu.Buttons;

import PaooGame.Audio.AudioAssets;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class MenuButton extends AbstractButton
    \brief Clasa abstracta pentru implementarea unui buton din meniu.
 */
public abstract class MenuButton extends AbstractButton {

    public final static int MENUBUTTONWIDTH = 500;
    public final static int MENUBUTTONHEIGHT = 55;

    public MenuButton(RefLinks refLinks, String name, int x, int y)
    {
        super(refLinks, Assets.MenuButtons, name, x, y, AudioAssets.OnButton, AudioAssets.Released);

        this.BUTTONWIDTH = MENUBUTTONWIDTH;
        this.BUTTONHEIGHT = MENUBUTTONHEIGHT;

        this.transformComponent.setWidth(BUTTONWIDTH);
        this.transformComponent.setHeight(BUTTONHEIGHT);
    }

    @Override
    public void Update() {
        super.Update();

        if(isReleased())
        {
            RELEASED.setFramePosition(0);
            RELEASED.start();
        }

    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
