package PaooGame.Menu.Buttons;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.States.State;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class ContinueButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a interoga si citirea din baza de date inainte de inceperea jocului.
 */
public class ContinueButton extends MenuButton
{
    private static boolean active;  /*!< Indica daca butonul este activ sau inactiv. */

    public ContinueButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"Continue", x, y);

        active = !refLinks.getDatabase().isCharactersNameTableEmpty();
    }

    @Override
    public void Update() {
        super.Update();

        if(active)
        {
            this.buttonStatesImage = Assets.MenuButtons;
        }
        else
        {
            this.buttonStatesImage = new BufferedImage[]{Assets.MenuButtons[0], Assets.InactiveMenuButton, Assets.InactiveMenuButton};
        }

        if(isReleased() && active)
        {
            refLinks.getDatabase().ContinueGame(PlayState.getAliveCharacters(), PlayState.getHandler());

            PlayState.setEnemiesKilled(0);
            for(boolean b : PlayState.getAliveCharacters())
            {
                if(!b)
                {
                    PlayState.setEnemiesKilled(PlayState.getEnemiesKilled() + 1);
                }
            }

            State.SetState(refLinks.getGame().getPlayState());
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }

    public static void setActive(boolean active) {
        ContinueButton.active = active;
    }


}
