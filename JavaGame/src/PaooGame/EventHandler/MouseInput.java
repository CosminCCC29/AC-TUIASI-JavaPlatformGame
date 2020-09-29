package PaooGame.EventHandler;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*! \class MouseInput extends MouseAdapter
    \brief Implementeaza posibilitatea input-ului de la mouse.
 */
public class MouseInput extends MouseAdapter {

    private static int MouseX = 0;  /*!< Pozitia mouse-ului pe tasta x.*/
    private static int MouseY = 0;  /*!< Pozitia mouse-ului pe tasta y.*/

    private RefLinks refLinks;              /*!< Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private MouseEvent pressedMouseKey;     /*!< Referinta catre un obiect KeyEvent pentru tasta apasata.*/
    private MouseEvent releasedMouseKey;    /*!< Referinta catre un obiect KeyEvent pentru tasta ridicata.*/

    /*! \class public class KeyInput extends KeyAdapter
    \brief Implementeaza posibilitatea input-ului de la mouse.

    \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    public MouseInput(RefLinks refLinks)
    {
        this.refLinks = refLinks;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia curenta a mouse-ului
     */
    public void Update()
    {
        MouseX = MouseInfo.getPointerInfo().getLocation().x-refLinks.getCanvas().getLocationOnScreen().x;
        MouseY = MouseInfo.getPointerInfo().getLocation().y-refLinks.getCanvas().getLocationOnScreen().y;
    }

    /*! \fn public void public void keyReleased(KeyEvent e)
        \brief Metoda apelata la apasarea unei taste
     */
    @Override
    public void mousePressed(MouseEvent e) {
        releasedMouseKey = null;
        pressedMouseKey = e;
    }

    /*! \fn public void public void keyReleased(KeyEvent e)
        \brief Metoda apelata la ridicarea unei taste
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        pressedMouseKey = null;
        releasedMouseKey = e;
    }

    /*! \fn  public void setPressedMouseKey(MouseEvent pressedMouseKey)
     */
    public void setPressedMouseKey(MouseEvent pressedMouseKey) {
        this.pressedMouseKey = pressedMouseKey;
    }

    /*! \fn public void setReleasedMouseKey(MouseEvent releasedMouseKey)
     */
    public void setReleasedMouseKey(MouseEvent releasedMouseKey) {
        this.releasedMouseKey = releasedMouseKey;
    }

    /*! \fn public MouseEvent getPressedMouseKey()
     */
    public MouseEvent getPressedMouseKey() {
        return pressedMouseKey;
    }

    /*! \fn public MouseEvent getReleasedMouseKey()
     */
    public MouseEvent getReleasedMouseKey() {
        return releasedMouseKey;
    }

    /*! \fn public int getMouseX()
     */
    public int getMouseX() {
        return MouseX;
    }

    /*! \fn public int getMouseY()
     */
    public int getMouseY() {
        return MouseY;
    }
}

