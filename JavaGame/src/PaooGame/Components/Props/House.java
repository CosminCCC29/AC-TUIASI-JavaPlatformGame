package PaooGame.Components.Props;

import PaooGame.Components.ObjectId;
import PaooGame.Graphics.Assets;
import PaooGame.Map.MapType;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class House extends Props
    \brief Implementeaza o casa in joc.
 */
public class House extends Props {

    public static final int HOUSEWIDTH = (int) (397 * 1.5);     /*!< Latimea obiectului pentru desenare. */
    public static final int HOUSEHEIGHT = (int) (309 * 1.5);    /*!< Inaltimea obiectului pentru desenare.*/

    /*! \fn public House(RefLinks refLinks, float x, float y, ObjectsHandler handler)
        \brief Constructor de initializare al clasei PinkThief.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public House(RefLinks refLinks, int x, int y, ObjectsHandler handler) {
        super(Assets.house, refLinks, x, y, ObjectId.House, handler, false);

        transformComponent.setWidth(HOUSEWIDTH);
        transformComponent.setHeight(HOUSEHEIGHT);

    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia obiectului
     */
    @Override
    public void Update() {
        super.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza obiectul.
     */
    @Override
    public void Draw(Graphics g) {

        if(refLinks.getMap().getMapType() == MapType.MainMap)
        {
            super.Draw(g);
        }
    }

    /*! \fn public ObjectId GetId()
        \brief Returneaza un enum de tip ObjectId reprezentand tipul obiectului.
     */
    @Override
    public ObjectId GetId() {
        return null;
    }
}
