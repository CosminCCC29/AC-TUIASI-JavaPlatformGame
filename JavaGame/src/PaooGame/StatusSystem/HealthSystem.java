package PaooGame.StatusSystem;

import PaooGame.Components.Characters.Characters;
import PaooGame.Components.Component;
import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectId;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.Map.MapType;
import PaooGame.Map.Tiles.Tile;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.Random;

/*! \class HealthSystem implements Component
    \brief Implementeaza notiunea sistem de viata al caracterului.
 */
public class HealthSystem implements Component
{
    private RefLinks refLinks;          /*!< Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program. */
    private ObjectsHandler handler;     /*!< Referinta catre handler-ul de obiecte. */
    private Characters character;       /*!< Referinta catre caracterul care detine sistemul de viata. */

    private static Random rand = new Random();  /*!< Referinta un obiect ce genereaza numere pseudo-aleatoare. */

    private static final int HP_BAR_WIDTH = (int)(Assets.hpBarWidth * 1.5);
    private static final int HP_BAR_HEIGHT = (int)(Assets.hpBarHeight * 1.5);
    private static final int OFFSET = 15;

    /*! \fn public public HealthSystem(RefLinks refLinks, Characters character, ObjectsHandler handler)
        \brief Constructor de initializare al clasei HealthSystem.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param character Referinta catre caracterul ce detine sistemul de viata.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public HealthSystem(RefLinks refLinks, Characters character, ObjectsHandler handler)
    {
        this.refLinks = refLinks;
        this.character = character;
        this.handler = handler;
    }

    /*! \fn public void Update()
        \brief Actualizeaza viata caracterului in diferite circumstante.
     */
    @Override
    public void Update() {

        if(character.GetId() != ObjectId.Player)
        {
            if(
                    handler.getMainCharacter().getAttackSystem().isAttacking() && refLinks.getMap().getMapType() == MapType.ArenaMap &&
                            (handler.getMainCharacter().getAttackSystem().getAttackRectangle().intersects(character.getCollisionBounds().getBoundsLeft())
                                    || handler.getMainCharacter().getAttackSystem().getAttackRectangle().intersects(character.getCollisionBounds().getBoundsRight())
                                    || handler.getMainCharacter().getAttackSystem().getAttackRectangle().intersects(character.getCollisionBounds().getBoundsTop())
                                    || handler.getMainCharacter().getAttackSystem().getAttackRectangle().intersects(character.getCollisionBounds().getBoundsDown())
                            )
            )
            {
                int i = rand.nextInt(handler.getMainCharacter().getATTACKSOUND()[0].length);
                handler.getMainCharacter().getATTACKSOUND()[0][i].setFramePosition(0);
                handler.getMainCharacter().getATTACKSOUND()[0][i].start();

                if(character.getDefence() >= handler.getMainCharacter().getAttackDamage())
                {
                    character.setLife(character.getLives() -1);
                }
                else
                {
                    character.setLife(character.getLives() - handler.getMainCharacter().getAttackDamage() + character.getDefence());
                }

            }
            else
            if(
                    handler.getMainCharacter().getAttackSystem().isAttacking() && refLinks.getMap().getMapType() == MapType.MainMap && handler.getMainCharacter().getAttackSystem().getAttackRectangle().intersects(character.getCollisionBounds().getBoundsDown())
            )
            {
                int i = rand.nextInt(handler.getMainCharacter().getATTACKSOUND()[0].length);
                handler.getMainCharacter().getATTACKSOUND()[0][i].setFramePosition(0);
                handler.getMainCharacter().getATTACKSOUND()[0][i].start();

                Characters firstIntersect = handler.getFirstEnemyIntersectingPlayer();

                if(firstIntersect != null)
                firstIntersect.transformComponent.setPositionx(firstIntersect.transformComponent.getPositionx() + 25 * handler.getMainCharacter().getAnimation().getLastState());

            }
        }
        else
        {
            for(Characters character : handler.getCharactersHandler())
            {
                if(
                        character.GetId() != ObjectId.Player && character.getAttackSystem().isAttacking() && refLinks.getMap().getMapType() == MapType.ArenaMap &&
                                (character.getAttackSystem().getAttackRectangle().intersects(handler.getMainCharacter().getCollisionBounds().getBoundsLeft())
                                        || character.getAttackSystem().getAttackRectangle().intersects(handler.getMainCharacter().getCollisionBounds().getBoundsRight())
                                        || character.getAttackSystem().getAttackRectangle().intersects(handler.getMainCharacter().getCollisionBounds().getBoundsTop())
                                        || character.getAttackSystem().getAttackRectangle().intersects(handler.getMainCharacter().getCollisionBounds().getBoundsDown())
                                )
                )
                {
                    int i = rand.nextInt(character.getATTACKSOUND()[0].length);
                    character.getATTACKSOUND()[0][i].setFramePosition(0);
                    character.getATTACKSOUND()[0][i].start();

                    if(handler.getMainCharacter().getDefence() > character.getAttackDamage())
                    {
                        handler.getMainCharacter().setLife(handler.getMainCharacter().getLives() - 1);
                    }
                    else
                    {
                        handler.getMainCharacter().setLife(handler.getMainCharacter().getLives() - character.getAttackDamage() + handler.getMainCharacter().getDefence());
                    }


                    System.out.println(handler.getMainCharacter().getLives());
                }
            }
        }

        if(character.getLives() <= 0)
        {
            character.setLife(0);

            if(!AudioAssets.WinningMusic.isRunning()) {

                character.getDEATHSOUND().start();

                if(character.GetId() != ObjectId.Player)
                {
                    PlayState.setEnemiesKilled(PlayState.getEnemiesKilled() + 1);
                }

                AudioAssets.StopAllMusic();
                AudioAssets.WinningMusic.start();
                AudioAssets.WinningMusic.loop(Clip.LOOP_CONTINUOUSLY);
            }

        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Afiseaza pe ecran viata curenta a caracterului.
     */
    @Override
    public void Draw(Graphics g) {

        int hpbarIndex = (int)((character.getLives() - 0.01f) / 20);

        if(hpbarIndex < 0) {
            hpbarIndex = 0;
        }


        if(character.GetId() != ObjectId.Player && refLinks.getMap().getMapType() == MapType.ArenaMap) {
            g.drawImage(Assets.HPbar[hpbarIndex], (int) ((int) refLinks.getCurrentMap().getCam().transformComponent.getPositionx() + refLinks.getCurrentMap().getCam().transformComponent.getWidth() - HP_BAR_WIDTH - OFFSET) + HP_BAR_WIDTH, OFFSET, -HP_BAR_WIDTH, HP_BAR_HEIGHT, null);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
            g.drawString(String.valueOf(character.getLives()), (int) (refLinks.getCurrentMap().getCam().transformComponent.getPositionx() + refLinks.getCurrentMap().getCam().transformComponent.getWidth() - HP_BAR_WIDTH - OFFSET*6.5), HP_BAR_HEIGHT/2 + OFFSET*2);

        }
        else if(character.GetId() == ObjectId.Player && refLinks.getMap().getMapType() == MapType.ArenaMap)
        {
            g.drawImage(Assets.HPbar[hpbarIndex], (int) (refLinks.getCurrentMap().getCam().transformComponent.getPositionx() + OFFSET), OFFSET, HP_BAR_WIDTH, HP_BAR_HEIGHT, null);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
            g.drawString(String.valueOf(character.getLives()), (int) (refLinks.getCurrentMap().getCam().transformComponent.getPositionx() + HP_BAR_WIDTH + OFFSET*2), HP_BAR_HEIGHT/2 + OFFSET*2);
        }
    }
}
