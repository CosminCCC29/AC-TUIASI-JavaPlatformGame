package PaooGame.Menu.Buttons;

import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.States.State;

import java.awt.*;

/*! \class BackButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a revenii la un submeniu anterior.
 */
public class BackButton extends MenuButton
{
    Submenu currentMenu;

    public BackButton(RefLinks refLinks, int x, int y, Submenu currentMenu) {
        super(refLinks,"Back", x, y);

        this.currentMenu = currentMenu;
    }

    @Override
    public void Update() {
        super.Update();

        if(isReleased()) {
            currentMenu.showSubmenu(false);
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
