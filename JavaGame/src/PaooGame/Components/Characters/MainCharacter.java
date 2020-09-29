package PaooGame.Components.Characters;

import PaooGame.Components.ObjectId;
import PaooGame.EventHandler.KeyInput;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;

/*! \class MainCharacter extends Characters
    \brief Implementeaza caracterul principal al jocului(player-ul).
 */
public final class MainCharacter extends Characters {

    private KeyInput KeyboardInput; /*!< Referinta catre un obiect de tip KeyInput pentru input-ul de la tastatura.*/

    /*! \fn public MainCharacter(RefLinks refLinks, float x, float y, ObjectsHandler handler, KeyInput KeyboardInput)
        \brief Constructor de initializare al clasei MainCharacter.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param handler Referinta catre handler-ul de obiecte.
        \param KeyboardInput Referinta catre un obiect de tip KeyInput pentru input-ul de la tastatura.
     */
    public MainCharacter(RefLinks refLinks, float x, float y, ObjectsHandler handler, KeyInput KeyboardInput) {
        super(Assets.BlueCleric, refLinks, x, y, ObjectId.Player, new Clip[][]{AudioAssets.SwordHitAttacks, AudioAssets.SwordMissAttacks}, AudioAssets.ManDeath, handler);

        this.KeyboardInput = KeyboardInput;

        this.transformComponent.setWidth(CHARACTER_DEFAULT_WIDTH);
        this.transformComponent.setHeight(CHARACTER_DEFAULT_HEIGHT);

        animation.setSpeed(2);
        animation.setImages(this.img);
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia caracterului.
     */
    @Override
    public void Update() {
        GetInput();
        super.Update();
    }

    /*! \fn public ObjectId GetId()
        \brief Returneaza un enum de tip ObjectId reprezentand tipul caaracterului.
     */
    @Override
    public void Draw(Graphics g)
    {
        super.Draw(g);

        //g.setColor(Color.ORANGE);
        //g.drawRect((int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), this.Width, this.Height);
    }

    /*! \fn public void GetInput()
        \brief Metoda care actualizeaza pozitia caracterului principal in functie input-ul de la tastatura.
     */
    private void GetInput() {

            if (KeyboardInput.getPressedKey() != null) {
                switch (KeyboardInput.getPressedKey().getKeyCode()) {
                    case KeyEvent.VK_A:
                        transformComponent.setSpeedx(10.0f);
                        transformComponent.setVelocityx(-1);
                        break;

                    case KeyEvent.VK_D:
                        transformComponent.setSpeedx(10.0f);
                        transformComponent.setVelocityx(1);
                        break;

                    case KeyEvent.VK_W:

                        if (!transformComponent.isJumping() || !transformComponent.isJumping2()) {

                            if (transformComponent.isJumping()) {
                                transformComponent.setJumping2(true);
                                transformComponent.setSpeedy(12.0f);
                            } else {
                                transformComponent.setJumping(true);
                                transformComponent.setSpeedy(15.0f);
                            }
                            transformComponent.setVelocityy(-1);
                        }
                        break;

                    case KeyEvent.VK_S:
                        transformComponent.setSpeedy(15.0f);
                        transformComponent.setVelocityy(1);

                        transformComponent.setJumping2(true);
                        break;

                    case KeyEvent.VK_J:
                        attackSystem.Attack(true);
                        break;
                }
            }

        if (KeyboardInput.getReleasedKey() != null) {
            switch (KeyboardInput.getReleasedKey().getKeyCode()) {
                case KeyEvent.VK_A:
                case KeyEvent.VK_D:
                    transformComponent.setSpeedx(0);
                    transformComponent.setVelocityx(0);
                    break;

                case KeyEvent.VK_W:

                case KeyEvent.VK_S:
                    break;
            }
        }
    }

    /*! \fn public ObjectId GetId()
        \brief Returneaza un enum de tip ObjectId reprezentand tipul caaracterului.
     */
    @Override
    public ObjectId GetId() {
        return this.Id;
    }

}
