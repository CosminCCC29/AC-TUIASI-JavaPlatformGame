package PaooGame.EventHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*! \class KeyInput extends KeyAdapter
    \brief Implementeaza posibilitatea input-ului de la tastatura.
 */
public class KeyInput extends KeyAdapter {

    private KeyEvent pressedKey;    /*!< Referinta catre un obiect KeyEvent pentru tasta apasata.*/
    private KeyEvent releasedKey;   /*!< Referinta catre un obiect KeyEvent pentru tasta ridicata.*/

    /*! \fn public void public void keyPressed(KeyEvent e)
        \brief Metoda apelata la apasarea unei taste
     */
    @Override
    public void keyPressed(KeyEvent e) {

        releasedKey = null;
        pressedKey = e;

    }

    /*! \fn public void public void keyReleased(KeyEvent e)
        \brief Metoda apelata la ridicarea unei taste
     */
    @Override
    public void keyReleased(KeyEvent e) {

        pressedKey = null;
        releasedKey = e;
    }

    /*! \fn public void setPressedKey(KeyEvent pressedKey)
     */
    public void setPressedKey(KeyEvent pressedKey) {
        this.pressedKey = pressedKey;
    }

    /*! \fn public void setReleasedKey(KeyEvent releasedKey)
     */
    public void setReleasedKey(KeyEvent releasedKey) {
        this.releasedKey = releasedKey;
    }

    /*! \fn public KeyEvent getPressedKey()
     */
    public KeyEvent getPressedKey() {
        return pressedKey;
    }

    /*! \fn KeyEvent getReleasedKey()
     */
    public KeyEvent getReleasedKey() {
        return releasedKey;
    }
}