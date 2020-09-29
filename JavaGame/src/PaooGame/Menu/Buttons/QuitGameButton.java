package PaooGame.Menu.Buttons;

import PaooGame.RefLinks;
import PaooGame.States.State;

import java.awt.*;

/*! \class QuitGameButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a inchide jocul.
 */
public class QuitGameButton extends MenuButton
{
    public QuitGameButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"Quit Game", x, y);
    }

    @Override
    public void Update() {
        super.Update();

        if(isReleased())
        {
            System.exit(0);
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
