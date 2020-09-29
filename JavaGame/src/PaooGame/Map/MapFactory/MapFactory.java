package PaooGame.Map.MapFactory;
import PaooGame.Camera.Camera;
import PaooGame.Map.Map;
import PaooGame.Map.MapType;
import PaooGame.RefLinks;

/*! \class MapFactory
    \brief Clasa abstracta al Design Pattern-ului Factory.

    Clasa implementeaza notiunea de fabrica de obiecte pentru producerea hartilor din joc.
 */
public abstract class MapFactory {

    /*! \fn public abstract Map CreateMap(MapType mapType, Camera cam, RefLinks refLinks)
        \brief Metoda abstracta de creerea hartilor din joc.

        \param mapType Tipul hartii.
        \param cam Referinta catre Camera jocului.
        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    public abstract Map CreateMap(MapType mapType, Camera cam, RefLinks refLinks);
}
