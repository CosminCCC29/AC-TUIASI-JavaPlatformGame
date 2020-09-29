package PaooGame;

import PaooGame.Components.ObjectFactory.ObjectFactory;
import PaooGame.Database.Database;
import PaooGame.EventHandler.KeyInput;
import PaooGame.EventHandler.MouseInput;
import PaooGame.Map.Map;
import PaooGame.Map.MapFactory.MapFactory;

import java.awt.*;


/*! \class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.

    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */
public class RefLinks {
    private Game game;          /*!< Referinta catre obiectul Game.*/
    private Map map;            /*!< Referinta catre harta curenta.*/

    /*! \fn public RefLinks(Game game)
        \brief Constructorul de initializare al clasei.

        \param game Referinta catre obiectul game.
     */
    public RefLinks(Game game) {
        this.game = game;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    /*! \fn public KeyInput getKeyInput()
            \brief Returneaza referinta catre managerul evenimentelor de tastatura.
         */
    public KeyInput getKeyInput() {
        return game.getKeyInput();
    }

    /*! \fn public KeyInput getMouseInput()
            \brief Returneaza referinta catre managerul evenimentelor de la mouse.
         */
    public MouseInput getMouseInput() {
        return game.getMouseInput();
    }

    public Map getCurrentMap() {
        return map;
    }

    public int getWndWidth() {
        return game.getWndWidth();
    }

    public int getWndHeight() {
        return game.getWndHeight();
    }

    public ObjectFactory getCharactersFactory() {
        return game.getCharactersFactory();
    }

    public ObjectFactory getPropsFactory() {
        return game.getPropsFactory();
    }

    public MapFactory getMapFactory() {
        return game.getMapFactory();
    }

    public Map getMap() {
        return map;
    }

    public Graphics getGraphics()
    {
        return game.getGraphics();
    }

    public Database getDatabase()
    {
        return game.getDatabase();
    }

    public Canvas getCanvas()
    {
        return game.getWnd().getCanvas();
    }

    public Game getGame() {
        return game;
    }
}