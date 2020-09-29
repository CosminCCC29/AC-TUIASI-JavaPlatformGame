package PaooGame.States;
import PaooGame.Camera.Camera;
import PaooGame.Camera.ParallaxEffect;
import PaooGame.Graphics.Assets;
import PaooGame.Menu.Buttons.*;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/*! \class PauseState extends State
    \brief Implementeaza notiunea de meniu de pauza.
 */
public class PauseState extends State
{
    private Camera cam;
    private ParallaxEffect parallaxEffect;

    private BufferedImage[] Images;

    private Submenu MainMenu;

    int maxSize = 56;
    int minSize = 28;
    int currentSize = minSize;
    boolean reverse = false;

    /*! \fn public PauseState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PauseState(RefLinks refLink)
    {
        super(refLink);

        Images = new BufferedImage[6];
        Images[0] = Assets.trees31;
        Images[1] = Assets.trees32;
        Images[2] = Assets.clouds3;
        Images[3] = Assets.mountain31;
        Images[4] = Assets.mountains32;
        Images[5] = Assets.sky3;

        cam = new Camera(refLinks);
        parallaxEffect = new ParallaxEffect(refLinks, cam, Images);

        MainMenu = new Submenu(refLinks, "", 0, 0, null);

        MainMenu = new Submenu(refLinks, "", 0, 0, null);
        Submenu settingsMenu = new Submenu(refLink, "Settings", refLink.getWndWidth() / 2 - MenuButton.MENUBUTTONWIDTH / 2, refLink.getWndHeight() / 2 + 180 + 50, MainMenu);
        settingsMenu.addButton(new MusicButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 50));
        settingsMenu.addButton(new SoundButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 115 + 50));
        settingsMenu.addButton(new BackButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 230 + 50, settingsMenu));

        MainMenu.addButton(new ResumeButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 50));
        MainMenu.addButton(new SaveGameButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 90 + 50));
        MainMenu.addButton(settingsMenu);
        MainMenu.addButton(new MainMenuButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 270 + 50));

        MainMenu.showSubmenu(true);

    }


    /*! \fn public void Update()
        \brief Actualizeaza componentele meniului de pauza.
     */
    @Override
    public void Update()
    {
        refLinks.getMouseInput().Update();

        if (refLinks.getKeyInput().getPressedKey() != null && refLinks.getKeyInput().getPressedKey().getKeyCode() == KeyEvent.VK_ESCAPE) {
            refLinks.getKeyInput().setPressedKey(null);
            State.SetState(refLinks.getGame().getPlayState());
        }

        synchronized (cam) {
            cam.transformComponent.setPositionx(cam.transformComponent.getPositionx() - 2f);
            parallaxEffect.Update();
        }

        MainMenu.Update();

    }

    /*! \fn public void Draw(Graphics g)
            \brief Deseneaza (randeaza) pe ecran starea curenta a meniului de pauza.
            \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        /// operatie de desenare
        ///////////////////////////
        Graphics2D g2d = (Graphics2D) g;

        synchronized (cam) {
            g2d.translate(-cam.transformComponent.getPositionx(), -cam.transformComponent.getPositiony());

            parallaxEffect.Draw(g);

            g2d.translate(cam.transformComponent.getPositionx(), cam.transformComponent.getPositiony());
        }

        ///////////////////////////

        MainMenu.Draw(g);

        g.drawImage(Assets.gameLogo, refLinks.getWndWidth() / 2 - Assets.gameLogoWidth / 3, (int) (refLinks.getWndHeight() / 2 - Assets.gameLogoHeight / 1.5), (int) (Assets.gameLogoWidth / 1.5f), (int) (Assets.gameLogoHeight / 1.5f), null);

        ++currentSize;
        if(currentSize > maxSize != reverse)
        {
            reverse = true;
        }

        if(reverse)
        {
            --currentSize;
            --currentSize;

            if(currentSize < minSize)
            {
                reverse = false;
            }
        }

        int x = refLinks.getWndWidth() / 2 - Assets.gameLogoWidth / 3 + (int) (Assets.gameLogoWidth / 1.5f) - 385;  //125
        int y = (int) (refLinks.getWndHeight() / 2 - Assets.gameLogoHeight / 1.5) + (int) (Assets.gameLogoHeight / 1.5f) - 385; //-20





        AffineTransform transform = g2d.getTransform();
        transform.translate(x, -y);
        g2d.transform(transform);
        g2d.rotate(-0.6d , x, y);
        g.setColor(Color.BLACK);
        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout tl = new TextLayout("PAUSED", new Font("Arial", Font.BOLD, currentSize), frc);
        Shape shape = tl.getOutline(null);
        g2d.setStroke(new BasicStroke(2.5f));
        g2d.draw(shape);

        g2d.setColor(new Color(153, 204, 255, 165));   //225, 204, 0
        g2d.fill(shape);

    }

}
