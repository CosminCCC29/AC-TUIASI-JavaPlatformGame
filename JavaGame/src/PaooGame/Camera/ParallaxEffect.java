package PaooGame.Camera;

import PaooGame.Components.Component;
import PaooGame.Map.Tiles.Tile;
import PaooGame.Mechanics.TransformComponent;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class ParallaxEffect implements Component
    \brief Implementeaza notiunea de fundal in mai multe straturi.

    Membrul cam de tip Camera este obiectul dupa pozitia caruia se actualizeaza pozitia straturilor fundalului.
 */
public class ParallaxEffect implements Component
{
    private final int BACKGROUNDS;

    RefLinks refLinks; /*!< Referinta catre un obiect "shortcut".*/
    Camera cam; /*!< Referinta catre o camera.*/

    BufferedImage[] Images; /*!< Referinta catre un tablou de imagini.*/
    TransformComponent[] transformComponent; /*!< Referinta catre un tablou de tip TransformComponent.*/
    private float[] paralaxEffect;  /*!< Referinta catre un tablou de tip float, viteza de deplasare ale fundalurilor.*/
    private float[] startpos;  /*!< Referinta catre un tablou de tip float, pozitia initiala ale fundalurilor.*/
    private float[] distance;   /*!< Referinta catre un tablou de tip float, distanta parcursa de fundaluri relativ la pozitia initiala.*/

    /*! \fn public ParallaxEffect(RefLinks refLinks, Camera camera, BufferedImage[] Images)
        \brief Constructor de initializare al clasei ParallaxEffect.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param camera Referinta catre camera, folosita pentru actualizarea pozitiei fundalului.
        \param Images Referinta catre un tablou de imagini(straturile fundalului).
     */
    public ParallaxEffect(RefLinks refLinks, Camera camera, BufferedImage[] Images) {
        this.refLinks = refLinks;
        this.cam = camera;
        this.Images = Images;
        BACKGROUNDS = Images.length;

        transformComponent = new TransformComponent[BACKGROUNDS];

        if(refLinks.getCurrentMap() == null)
        {
            if(BACKGROUNDS == 5) {
                InitParallaxMainMenu();
            }
            else if(BACKGROUNDS == 6)
            {
                InitParallaxPauseState();
            }
        }
        else {
            switch (refLinks.getCurrentMap().getMapType()) {
                case MainMap:
                    InitParallaxMainMap();
                    break;

                case ArenaMap:
                    InitParallaxBattleMap();
                    break;

                default:
                    return;
            }
        }

        startpos = new float[BACKGROUNDS];

        for(int i = 0; i < BACKGROUNDS; ++i)
        {
            startpos[i] = transformComponent[i].getPositionx();
        }

        distance = new float[BACKGROUNDS];
    }

    /*! \fn public InitParallaxMainMenu()
        \brief Metoda de initializare al tabloului de imagini al  principal.
     */
    private void InitParallaxMainMenu() {
        transformComponent[0] = new TransformComponent(cam.transformComponent.getPositionx(),380 + 125,cam.transformComponent.getWidth(), Images[0].getHeight()*2);
        transformComponent[1] = new TransformComponent(cam.transformComponent.getPositionx(),305 + 100,cam.transformComponent.getWidth(), Images[1].getHeight()*2);
        transformComponent[2] = new TransformComponent(cam.transformComponent.getPositionx(),180 + 100,cam.transformComponent.getWidth(), Images[2].getHeight()*2.2f);
        transformComponent[3] = new TransformComponent(cam.transformComponent.getPositionx(),0,cam.transformComponent.getWidth(), cam.transformComponent.getHeight()/4);
        transformComponent[4] = new TransformComponent(cam.transformComponent.getPositionx(),0,cam.transformComponent.getWidth(), cam.transformComponent.getHeight()/2);

        /// Se initializeaza viteza de deplasare al fundalelor
        paralaxEffect = new float[BACKGROUNDS];
        paralaxEffect[0] = 0.0f;
        paralaxEffect[1] = 0.45f;
        paralaxEffect[2] = 0.75f;
        paralaxEffect[3] = 0.9f;
        paralaxEffect[4] = 1.0f;
    }

    /*! \fn public InitParallaxPauseState()
        \brief Metoda de initializare al tabloului de imagini al meniului de pauza.
     */
    private void InitParallaxPauseState() {
        transformComponent[0] = new TransformComponent(cam.transformComponent.getPositionx(),-(1080 - refLinks.getWndHeight()) + 15,cam.transformComponent.getWidth(), cam.transformComponent.getHeight() + (1080 - refLinks.getWndHeight()));
        transformComponent[1] = new TransformComponent(cam.transformComponent.getPositionx(),-(1080 - refLinks.getWndHeight()),cam.transformComponent.getWidth(), cam.transformComponent.getHeight() + (1080 - refLinks.getWndHeight()));
        transformComponent[2] = new TransformComponent(cam.transformComponent.getPositionx(),-(1080 - refLinks.getWndHeight()),cam.transformComponent.getWidth(), cam.transformComponent.getHeight() + (1080 - refLinks.getWndHeight()));
        transformComponent[3] = new TransformComponent(cam.transformComponent.getPositionx(),-(1080 - refLinks.getWndHeight()),cam.transformComponent.getWidth(), cam.transformComponent.getHeight() + (1080 - refLinks.getWndHeight()));
        transformComponent[4] = new TransformComponent(cam.transformComponent.getPositionx(),-(1080 - refLinks.getWndHeight()),cam.transformComponent.getWidth(), cam.transformComponent.getHeight() + (1080 - refLinks.getWndHeight()));
        transformComponent[5] = new TransformComponent(cam.transformComponent.getPositionx(),-(1080 - refLinks.getWndHeight()),cam.transformComponent.getWidth(), cam.transformComponent.getHeight() + (1080 - refLinks.getWndHeight()));

        /// Se initializeaza viteza de deplasare al fundalelor
        paralaxEffect = new float[BACKGROUNDS];
        paralaxEffect[0] = 0.0f;
        paralaxEffect[1] = 0.35f;
        paralaxEffect[2] = 0.65f;
        paralaxEffect[3] = 0.75f;
        paralaxEffect[4] = 0.85f;
        paralaxEffect[5] = 1.0f;
    }

    /*! \fn public InitParallaxPauseState()
        \brief Metoda de initializare al tabloului de imagini al meniului de pauza.
     */
    private void InitParallaxMainMap() {
        transformComponent[0] = new TransformComponent(cam.transformComponent.getPositionx(),380 + 50,cam.transformComponent.getWidth(), Images[0].getHeight()*2);
        transformComponent[1] = new TransformComponent(cam.transformComponent.getPositionx(),305 + 50,cam.transformComponent.getWidth(), Images[1].getHeight()*2);
        transformComponent[2] = new TransformComponent(cam.transformComponent.getPositionx(),180 + 50,cam.transformComponent.getWidth(), Images[2].getHeight()*2);
        transformComponent[3] = new TransformComponent(cam.transformComponent.getPositionx(),0,cam.transformComponent.getWidth(), cam.transformComponent.getHeight()/4);
        transformComponent[4] = new TransformComponent(cam.transformComponent.getPositionx(),0,cam.transformComponent.getWidth(), cam.transformComponent.getHeight()/2);

        paralaxEffect = new float[BACKGROUNDS];
        paralaxEffect[0] = 0.0f;
        paralaxEffect[1] = 0.45f; //0.4f
        paralaxEffect[2] = 0.75f; //0.6f
        paralaxEffect[3] = 0.95f; //0.9f
        paralaxEffect[4] = 1.0f;
    }

    /*! \fn public InitParallaxBattleMap()
        \brief Metoda de initializare al tabloului de imagini al hartii de duel.
     */
    private void InitParallaxBattleMap() {
        transformComponent[0] = new TransformComponent(cam.transformComponent.getPositionx(), 12 * Tile.TILE_HEIGHT - Images[0].getHeight()*3 ,cam.transformComponent.getWidth(), Images[0].getHeight() * 3);
        transformComponent[1] = new TransformComponent(cam.transformComponent.getPositionx(),12.1f * Tile.TILE_HEIGHT - Images[1].getHeight() * 4,cam.transformComponent.getWidth(), Images[1].getHeight() * 4);
        transformComponent[2] = new TransformComponent(cam.transformComponent.getPositionx(),11.5f * Tile.TILE_HEIGHT - Images[2].getHeight()*4f,cam.transformComponent.getWidth(), Images[2].getHeight() * 4f);
        transformComponent[3] = new TransformComponent(cam.transformComponent.getPositionx(), 9.75f * Tile.TILE_HEIGHT - Images[2].getHeight()*4, cam.transformComponent.getWidth(), Images[3].getHeight() * 4);
        transformComponent[4] = new TransformComponent(cam.transformComponent.getPositionx(),0,cam.transformComponent.getWidth(), cam.transformComponent.getHeight()/1.5f);

        paralaxEffect = new float[BACKGROUNDS];

        paralaxEffect[0] = 0.0f;
        paralaxEffect[1] = 0.4f;
        paralaxEffect[2] = 0.6f;
        paralaxEffect[3] = 0.8f;
        paralaxEffect[4] = 0.98f;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia fiecarui fundal;
     */
    @Override
    public void Update()
    {

        for(int i = 0; i< BACKGROUNDS; ++i)
        {
            distance[i] = cam.transformComponent.getPositionx() * paralaxEffect[i];
            transformComponent[i].setPositionx(startpos[i] + distance[i]);

            if(transformComponent[i].getPositionx() < cam.transformComponent.getPositionx() - cam.transformComponent.getWidth())
            {
                startpos[i] += transformComponent[i].getWidth();
            }
            else
            {
                if(transformComponent[i].getPositionx() > cam.transformComponent.getPositionx() + cam.transformComponent.getWidth())
                {
                    startpos[i] -= transformComponent[i].getWidth();
                }
            }
        }


    }

    /*! \fn public void Draw()
        \brief Deseneaza elementele din tabloul de imagini(fundalurile).
     */
    @Override
    public void Draw(Graphics g) {

        for(int i = BACKGROUNDS - 1; i >= 0; i--)
        {

            if(transformComponent[i].getPositionx() > cam.transformComponent.getPositionx()) {
                g.drawImage(Images[i], (int) (transformComponent[i].getPositionx() - transformComponent[i].getWidth()), (int) transformComponent[i].getPositiony(), (int) transformComponent[i].getWidth(), (int) transformComponent[i].getHeight(), null);
            }

            g.drawImage(Images[i], (int) (transformComponent[i].getPositionx()), (int)transformComponent[i].getPositiony(), (int) transformComponent[i].getWidth(), (int) transformComponent[i].getHeight(), null);

            if(transformComponent[i].getPositionx() + transformComponent[i].getWidth() < cam.transformComponent.getPositionx() + cam.transformComponent.getWidth()) {
                g.drawImage(Images[i], (int) (transformComponent[i].getPositionx() + transformComponent[i].getWidth()), (int) transformComponent[i].getPositiony(), (int) transformComponent[i].getWidth(), (int) transformComponent[i].getHeight(), null);
            }
        }

    }
}
