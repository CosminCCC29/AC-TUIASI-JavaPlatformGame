package PaooGame.Graphics;

import PaooGame.Components.Characters.Characters;
import PaooGame.Components.Component;
import PaooGame.States.PlayState;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class Animation implements Component
    \brief Implementeaza notiunea de animatie a caracterelor.
 */
public class Animation implements Component {

    Characters character;               /*!< Referinta catre caracterului al carei animatie este desenata.*/

    private int lastState = 0;          /*!< Directia pe care s-a oprit caracterul. -1 -> Stanga, 0 -> Default, 1 -> Dreapta*/
    private int animationState = 0;     /*!< Numarul animatiei care urmeaza a fi desenata */
    private boolean Attacking = false;  /*!< Indica daca caracterul ataca */
    private boolean Dead = false;       /*!< Indica daca caracterul este mort */

    private int speed;                  /*!< Viteza de schimbare a frame-urilor */
    private int frames;                 /*!< Nr. de frame-uri */

    private int currentIndex = 0;       /*!< Numar care specifica ce frame se deseneaza. */
    private int nextIndex = 0;         /*!< Numar care specifica ce frame urmeaza sa fie desenat. */

    private BufferedImage[][] images;   /*!< Referinta catre un tablou bidimensional de imagini ale caracterului. */
    private BufferedImage currentImg;   /*!< Rreferinta catre imaginea curenta care este desenata. */

    /*! \fn public Animation(Characters character)
        \brief Constructor de initializare al clasei Animation.

        \param character caracterul pentru care se deseneaza animatia.
     */
    public Animation(Characters character)
    {
        this.character = character;
        frames = Assets.characterFramesXLength;
    }

    /*! \fn public void Update()
        \brief Actualizeaza animatia.
     */
    @Override
    public void Update()
    {
        if(character.getLives() <= 0 && !Dead)
        {
            Dead = true;
            nextIndex = 0;
        }
        else if(character.getLives() > 0 && Dead)
        {
            Dead = false;
            nextIndex = 0;
        }
        else if(character.getAttackSystem().isAttacking()) {
            Attacking = true;
            nextIndex = 4;
        }

        ++currentIndex;
        if(currentIndex > speed)
        {
            currentIndex = 0;
            nextFrame();
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza animatia.
     */
    @Override
    public void Draw(Graphics g) {

        if(lastState < 0)
        {
            g.drawImage(currentImg, (int)(character.transformComponent.getPositionx() + character.transformComponent.getWidth()), (int)character.transformComponent.getPositiony(), -(int)character.transformComponent.getWidth(), (int)character.transformComponent.getHeight(), null);
        }
        else
        {
            g.drawImage(currentImg, (int)character.transformComponent.getPositionx(), (int)character.transformComponent.getPositiony(), (int)character.transformComponent.getWidth(), (int)character.transformComponent.getHeight(), null);
        }

    }

    /*! \fn public void nextFrame()
        \brief Metoda care schimba urmatorul frame.
     */
    private void nextFrame()
    {
        if (character.transformComponent.getVelocityx() > 0)
        {
            lastState = 1;
        }
        else if (character.transformComponent.getVelocityx() < 0) {
                lastState = -1;
            }


        if(!Attacking && !Dead) {
            if (character.transformComponent.getVelocityx() == 0) {
                animationState = 0; //Sta pe loc
            } else {
                if (character.transformComponent.getVelocityx() != 0) {
                    animationState = 2; //Merge
                }
            }
        }

        if(Dead)
        {
            animationState = 4;
        }
        else if(Attacking)
        {
            animationState = 3;
        }

        for(int i = 0; i< frames; i++)
        {
            if(nextIndex == i)
            {
                currentImg = images[animationState][i];
            }
        }
        ++nextIndex;


        if(nextIndex > frames)
        {
            if(Dead)
            {
                nextIndex = Assets.characterFramesXLength;
                lastState = 0;
                PlayState.setAliveCharacters(character.GetId().ordinal(), false);
            }
            else
            {
                nextIndex = 0;
                Attacking = false;
            }

        }

    }

    /*! \fn public void setSpeed(int speed)
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*! \fn  public void setImages(BufferedImage[][] images)
     */
    public void setImages(BufferedImage[][] images) {
        this.images = images;
    }

    /*! \fn public int getLastState()
     */
    public int getLastState() {
        return lastState;
    }
}
