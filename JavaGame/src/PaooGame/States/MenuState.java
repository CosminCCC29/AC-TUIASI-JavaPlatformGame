package PaooGame.States;
import PaooGame.Camera.Camera;
import PaooGame.Camera.ParallaxEffect;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.Menu.Buttons.*;
import PaooGame.RefLinks;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class MenuState extends State
    \brief Implementeaza notiunea de meniu pentru joc.
 */
public class MenuState extends State
{
    private final Camera cam;
    private ParallaxEffect parallaxEffect;

    private Submenu MainMenu;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
        super(refLink);

        BufferedImage[] images = new BufferedImage[5];
        images[0] = Assets.pine2;
        images[1] = Assets.pine1;
        images[2] = Assets.mountain2;
        images[3] = Assets.clouds;
        images[4] = Assets.sky;

        cam = new Camera(refLinks);
        parallaxEffect = new ParallaxEffect(refLinks, cam, images);

        MainMenu = new Submenu(refLinks, "", 0, 0, null);
        Submenu settingsMenu = new Submenu(refLink, "Settings", refLink.getWndWidth() / 2 - MenuButton.MENUBUTTONWIDTH / 2, refLink.getWndHeight() / 2 + 180 + 50, MainMenu);
        settingsMenu.addButton(new MusicButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 50));
        settingsMenu.addButton(new SoundButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 115 + 50));
        settingsMenu.addButton(new BackButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 230 + 50, settingsMenu));

        MainMenu.addButton(new ContinueButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 50));
        MainMenu.addButton(new NewGameButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 90 + 50));
        MainMenu.addButton(settingsMenu);
        MainMenu.addButton(new QuitGameButton(refLinks, refLink.getWndWidth()/2 - MenuButton.MENUBUTTONWIDTH/2, refLink.getWndHeight()/2 + 270 + 50));

        MainMenu.showSubmenu(true);
    }


    /*! \fn public void Update()
        \brief Actualizeaza componentele povestii.
     */
    @Override
    public void Update()
    {
        if(!AudioAssets.MainMenuMusic.isRunning())
        {
            AudioAssets.StopAllMusic();

            AudioAssets.MainMenuMusic.start();
            AudioAssets.MainMenuMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }


        refLinks.getMouseInput().Update();

        synchronized (cam) {
            cam.transformComponent.setPositionx(cam.transformComponent.getPositionx() - 2f);
            parallaxEffect.Update();
        }

        MainMenu.Update();

    }


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

    }

}
