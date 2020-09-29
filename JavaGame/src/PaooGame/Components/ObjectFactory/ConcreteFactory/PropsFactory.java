package PaooGame.Components.ObjectFactory.ConcreteFactory;

import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectFactory.ObjectFactory;
import PaooGame.Components.ObjectId;
import PaooGame.Components.Props.House;
import PaooGame.EventHandler.KeyInput;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

/*! \class PropsFactory extends ObjectFactory
    \brief Clasa concreta al Design Pattern-ului Factory.

    Clasa implementeaza notiunea de fabrica de obiecte pentru producerea de prop-uri.
 */
public class PropsFactory extends ObjectFactory {

    /*! \fn GameObject CreateGameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler, KeyInput KeyboardInput)
        \brief Metoda de creare al unui caracter.

        Metoda returneaza o referinta de tip GameObject reprezentand un prop concret.
        Metoda returneaza un anumit prop in functie de parametru ID care determina ce tip de prop va fi returnat de metoda.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param Id Tipul caracterului.
        \param handler Referinta catre handler-ul de obiecte.
        \param KeyboardInput Referinta catre un obiect de tip KeyInput pentru input-ul de la tastatura.
     */
    @Override
    public GameObject CreateGameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler, KeyInput KeyboardInput) {
        {
            switch (Id) {
                case House:
                    return new House(refLinks, (int)x, (int)y, handler);

            }
            return null;
        }
    }
}
