package PaooGame.Menu.Buttons;
import PaooGame.RefLinks;
import PaooGame.States.State;


import java.awt.*;

/*! \class ResumeButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a reveni la PlayState
 */
public class ResumeButton extends MenuButton
{
    public ResumeButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"Resume", x, y);
    }

    @Override
    public void Update() {
        super.Update();

        if(isReleased())
        {
            State.SetState(refLinks.getGame().getPlayState());
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
