package PaooGame.Menu.Buttons;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.States.State;


import java.awt.*;

/*! \class SaveGameButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a interoga si scriere in baza de date a status-urilor tuturor caracterelor in tabel CharactersName.
 */
public class SaveGameButton extends MenuButton
{
    public SaveGameButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"Save Game", x, y);
    }

    @Override
    public void Update() {
        super.Update();

        if(isReleased())
        {
            refLinks.getDatabase().SaveGame(PlayState.getAliveCharacters(), PlayState.getHandler());
            ContinueButton.setActive(true);
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
