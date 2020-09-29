package PaooGame.Menu.Buttons;

import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.States.PlotState;
import PaooGame.States.State;

import java.awt.*;

/*! \class NewGameButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a seta volumul muzicii jocului prin interogarea si scrierea in baza de date.
 */
public class NewGameButton extends MenuButton
{
    public NewGameButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"New Game", x, y);
    }

    @Override
    public void Update() {
        super.Update();

        if(isReleased())
        {
            ContinueButton.setActive(false);
            State.SetState(refLinks.getGame().getPlotState());
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
