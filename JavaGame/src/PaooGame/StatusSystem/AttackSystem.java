package PaooGame.StatusSystem;
import PaooGame.Components.Characters.Characters;
import PaooGame.Components.Component;
import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectId;
import PaooGame.Graphics.Assets;
import PaooGame.Map.MapType;
import PaooGame.Map.Tiles.Tile;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.Random;

/*! \class AttackSystem implements Component
    \brief Implementeaza notiunea sistem de atac al caracterului.
 */
public class AttackSystem implements Component
{
    private RefLinks refLinks;                                                      /*!< Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program. */
    private ObjectsHandler handler;                                                 /*!< Referinta catre handler-ul de obiecte. */
    private final static int ACTIVATED_ATTACK_AT_RANGE = (int) Math.pow(230,2);     /*!< Referinta raza faza de caracterul principal de la care poate incepe sa atace inamicul. */

    private static Random rand = new Random();

    private Characters character;                                                   /*!< Referinta catre caracterul care detine sistemul de atac. */
    private Rectangle attackRectangle;                                              /*!< Referinta un dreptunghi de atac. */
    private boolean active;                                                         /*!< Activ cand caracterul doreste sa atace. */
    private boolean ready;                                                          /*!< Activ cand caracterului i s-a scurs un anumit de cand a atacat ultima data. */
    private boolean attacking;                                                      /*!< Activ cand caracterul ataca. */

    private int timeIndex = 0;                                                      /*!< Timpul curent de cand a atacat caracterul. */
    private int speed;                                                              /*!< Viteza de atac a caracterului. */

    private final static int WIDTH = 48;                                            /*!< Latimea implicita dreptunghiului de atac.*/
    private final static int HEIGHT = 50;                                           /*!< Inaltimea implicita dreptunghiului de atac.*/

    /*! \fn public public AttackSystem(RefLinks refLinks, Characters character, ObjectsHandler handler, int speed)
        \brief Constructor de initializare al clasei AttackSystem.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param character Referinta catre caracterul ce detine sistemul de atac.
        \param handler Referinta catre handler-ul de obiecte.
        \param speed Viteza de atac a caracterului.
     */
    public AttackSystem(RefLinks refLinks, Characters character, ObjectsHandler handler, int speed)
    {
        this.refLinks = refLinks;
        this.handler = handler;
        this.character = character;
        this.speed = speed;
        active = false;
        ready = true;
        attacking = false;

        attackRectangle = new Rectangle(0, 0, WIDTH, HEIGHT);
    }

    /*! \fn public void Update()
        \brief Actualizeaza cand caracterul poate ataca de atac al caracterului si pozitia dreptunghiului de atac.
     */
    @Override
    public void Update()
    {
        if(this.character.GetId() != ObjectId.Player && refLinks.getMap().getMapType() == MapType.ArenaMap )
        {
            int centerPointMainPlayerX = (int)(handler.getMainCharacter().transformComponent.getPositionx() + handler.getMainCharacter().transformComponent.getWidth()/2);
            int centerPointMainPlayerY = (int)(handler.getMainCharacter().transformComponent.getPositiony() + handler.getMainCharacter().transformComponent.getHeight()/2);

            int centerPointCharacterX = (int)(character.transformComponent.getPositionx() + character.transformComponent.getWidth()/2);
            int centerPointCharacterY = (int)(character.transformComponent.getPositiony() + character.transformComponent.getHeight()/2);

            if(Math.pow(centerPointCharacterX - centerPointMainPlayerX, 2) + Math.pow(centerPointCharacterY - centerPointMainPlayerY, 2) < ACTIVATED_ATTACK_AT_RANGE)
            {
                Attack(true);
            }
            else
            {
                Attack(false);
            }
        }

        if(character.getLives() <= 0)
        {
            Attack(false);
        }

        if(character.getAnimation().getLastState() < 0)
        {
            attackRectangle.x = (int) (character.getCollisionBounds().getBoundsLeft().x - WIDTH/2.5);
        }
        else
        {
            attackRectangle.x = (int) (character.getCollisionBounds().getBoundsRight().x + character.getCollisionBounds().getBoundsRight().width - WIDTH/1.95);
        }
        attackRectangle.y = (int)(character.transformComponent.getPositiony() + character.transformComponent.getHeight()/4);

        if(active && ready)
        {
            int i = rand.nextInt(character.getATTACKSOUND()[1].length);
            character.getATTACKSOUND()[1][i].setFramePosition(0);
            character.getATTACKSOUND()[1][i].start();

            active = false;
            ready = false;
            attacking = true;
        }
        else
        {
            attacking = false;
            if (timeIndex > speed) {
                timeIndex = 0;
                ready = true;
            }
            ++timeIndex;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Metoda vida.
     */
    @Override
    public void Draw(Graphics g) {
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void Attack(boolean active) {
        this.active = active;
    }

    public Rectangle getAttackRectangle() {
        return attackRectangle;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
