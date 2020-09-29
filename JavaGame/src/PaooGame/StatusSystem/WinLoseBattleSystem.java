package PaooGame.StatusSystem;
import PaooGame.Components.Characters.Characters;
import PaooGame.Components.Component;
import PaooGame.Graphics.Assets;
import PaooGame.Map.MapType;
import PaooGame.Menu.Buttons.AbstractButton;
import PaooGame.Menu.Buttons.IncreaseADButton;
import PaooGame.Menu.Buttons.IncreaseASButton;
import PaooGame.Menu.Buttons.IncreaseDefButton;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;

import java.awt.*;

/*! \class HealthSystem implements Component
    \brief Implementeaza notiunea sistem de castig sau pierdere a unei batalii.
 */
public class WinLoseBattleSystem implements Component
{
    private RefLinks refLinks;          /*!< Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program. */
    private ObjectsHandler handler;     /*!< Referinta catre handler-ul de obiecte. */

    private AbstractButton[] IncreaseButtons;   /*!< Referinta catre un tablou de butoane de crestere al status-ului caracterului principal. */

    /*! \fn public public WinLoseBattleSystem(RefLinks refLinks, ObjectsHandler handler)
        \brief Constructor de initializare al clasei WinLoseBattleSystem.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public WinLoseBattleSystem(RefLinks refLinks, ObjectsHandler handler)
    {
        this.refLinks = refLinks;
        this.handler = handler;

        IncreaseButtons= new AbstractButton[3];

    }

    /*! \fn public void Update()
        \brief Actualizeaza butoanele de crestere al status-ului caracterului principal.
     */
    @Override
    public void Update() {

        if(IncreaseButtons[0] == null && IncreaseButtons[1] == null && IncreaseButtons[2] == null && refLinks.getMap().getMapType() == MapType.ArenaMap && handler.getFirstEnemy() != null && handler.getFirstEnemy().getLives() <= 0)
        {
            IncreaseButtons[0] = new IncreaseADButton(refLinks, refLinks.getWndWidth()/4, refLinks.getWndHeight()/5, handler.getMainCharacter());
            IncreaseButtons[1] = new IncreaseDefButton(refLinks, refLinks.getWndWidth()/4 * 2, refLinks.getWndHeight()/5, handler.getMainCharacter());
            IncreaseButtons[2] = new IncreaseASButton(refLinks, refLinks.getWndWidth()/4 * 3, refLinks.getWndHeight()/5, handler.getMainCharacter());
        }
        else if(refLinks.getMap().getMapType() != MapType.ArenaMap)
        {
            for(int i = 0; i < IncreaseButtons.length; ++i)
            {
                if(IncreaseButtons[i] != null)
                {
                    IncreaseButtons[i] = null;
                }
            }
        }

        for (AbstractButton increaseButton : IncreaseButtons) {
            if (increaseButton != null) {
                increaseButton.Update();
            }
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza butoanele de crestere al status-ului caracterului principal cat si un text atunci cand carracterul principal ramane fara viata.
     */
    @Override
    public void Draw(Graphics g) {

        for(AbstractButton abstractButton : IncreaseButtons)
        {
            if(abstractButton != null)
            {
                abstractButton.Draw(g);
            }
        }

        if(handler.getMainCharacter().getLives() <= 0 && refLinks.getMap().getMapType() == MapType.ArenaMap)
        {
            g.setColor(new Color(128, 0, 0));
            g.setFont(new Font("Times new Roman", Font.PLAIN, 125));
            g.drawString("You Died", refLinks.getWndWidth()/2 - 175,refLinks.getWndHeight()/5);

            g.setFont(new Font("Times new Roman", Font.PLAIN, 60));
            g.drawString("Press ENTER to retry..", refLinks.getWndWidth()/2 - 185, (int) (refLinks.getWndHeight()/1.8f));
        }

        if (refLinks.getCurrentMap().getMapType() == MapType.MainMap) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 32)); //Brush Script MT

            g.drawImage(Assets.IncreaseAttackDamageLogo, 10, 5, 50, 50, null);
            g.drawImage(Assets.IncreaseDefenceLogo, 10, 55, 50, 50, null);
            g.drawImage(Assets.IncreaseAttackSpeedLogo, 10, 105, 50, 50, null);

            g.drawString("AD: " + handler.getMainCharacter().getAttackDamage(), 12 + 50, 40 + 5);
            g.drawString("Def: " + handler.getMainCharacter().getDefence(), 12+ 50, 40 + 50);
            g.drawString("AS: " + handler.getMainCharacter().getAttackSpeed(), 12 + 50, 40 + 100);
            g.drawString("Enemies killed: " + PlayState.getEnemiesKilled() + "/" + (PlayState.CHARACTERS - 1), 10, 40 + 150);
        }
        else
        if (refLinks.getCurrentMap().getMapType() == MapType.ArenaMap && handler.getFirstEnemy() != null && handler.getFirstEnemy().getLives() <= 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 32)); //Brush Script MT

            g.drawImage(Assets.IncreaseAttackDamageLogo, 10, 70, 50, 50, null);
            g.drawImage(Assets.IncreaseDefenceLogo, 10, 50 + 70, 50, 50, null);
            g.drawImage(Assets.IncreaseAttackSpeedLogo, 10, 100 + 70, 50, 50, null);

            g.drawString("AD: " + handler.getMainCharacter().getAttackDamage(), 12 + 50, 40 + 70);
            g.drawString("Def: " + handler.getMainCharacter().getDefence(), 12+ 50, 40 + 45 + 70);
            g.drawString("AS: " + handler.getMainCharacter().getAttackSpeed(), 12 + 50, 40 + 95 + 70);

        }

    }

}
