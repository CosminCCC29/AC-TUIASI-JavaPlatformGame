package PaooGame.Components.ObjectFactory;
import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectId;
import PaooGame.EventHandler.KeyInput;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

/*! \class ObjectFactory
    \brief Clasa abstracta al Design Pattern-ului Factory.

    Clasa implementeaza notiunea de fabrica de obiecte pentru producerea de obiecte din joc.
 */
public abstract class ObjectFactory
{
    /*! \fn GameObject CreateGameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler, KeyInput KeyboardInput)
        \brief Metoda abstracta de creare al unui obiect din joc.

        Metoda returneaza o referinta de tip GameObject reprezentand un caracter concret.
        Metoda returneaza un anumit caracter in functie de parametru ID care determina ce tip de caracter va fi returnat de metoda

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param Id Tipul caracterului.
        \param handler Referinta catre handler-ul de obiecte.
        \param KeyboardInput Referinta catre un obiect de tip KeyInput pentru input-ul de la tastatura.
     */
    public abstract GameObject CreateGameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler, KeyInput KeyboardInput);
}
