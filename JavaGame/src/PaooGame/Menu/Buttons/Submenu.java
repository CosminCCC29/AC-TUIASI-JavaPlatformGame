package PaooGame.Menu.Buttons;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;

/*! \class Submenu extends AbstractButton
    \brief Clasa concreta pentru implementarea unui submeniu.
 */
public class Submenu extends AbstractButton {

    private ArrayList<AbstractButton> buttons = new ArrayList<PaooGame.Menu.Buttons.AbstractButton>();
    private final int BUTTONWIDTH = 500;
    private final int BUTTONHEIGHT = 55;
    private boolean showSubmenu = false;
    private boolean activeSubmenu = false;

    public Submenu(RefLinks refLinks, String name, int x, int y, Submenu previousSubmenu) {
        super(refLinks, Assets.MenuButtons, name, x, y, null, null);

        transformComponent.setWidth(BUTTONWIDTH);
        transformComponent.setHeight(BUTTONHEIGHT);
    }

    public void addButton(AbstractButton tmp) {
        buttons.add(tmp);
    }

    public void removeButton(AbstractButton tmp) {
        buttons.remove(tmp);
    }

    @Override
    public void Update() {

        if(this.isReleased())
        {
            showSubmenu = true;
        }

        if(showSubmenu) {
            for(AbstractButton button : buttons)
            {
                if(button.getClass().getName().contains("Submenu")) {
                    Submenu submenu = (Submenu) button;

                    if (submenu.showSubmenu) {
                        activeSubmenu = true;
                    }
                    else
                    {
                        activeSubmenu = false;
                    }
                }
            }

            if(activeSubmenu) {
                for (AbstractButton button : buttons) {
                    if (button.getClass().getName().contains("Submenu")) {
                        button.Update();
                    }
                }
            }
            else
            {
                for (AbstractButton button : buttons) {
                        button.Update();
                }
            }
        }
        else
        {
            super.Update();
        }

    }

    @Override
    public void Draw(Graphics g) {

        if(showSubmenu) {
            if (activeSubmenu) {
                for (AbstractButton button : buttons) {
                    if (button.getClass().getName().contains("Submenu")) {
                        button.Draw(g);
                    }
                }
            } else {
                for (AbstractButton button : buttons) {
                    button.Draw(g);
                }
            }
        }
        else
        {
            super.Draw(g);
        }

    }

    public void showSubmenu(boolean active) {
        showSubmenu = active;
    }


}
