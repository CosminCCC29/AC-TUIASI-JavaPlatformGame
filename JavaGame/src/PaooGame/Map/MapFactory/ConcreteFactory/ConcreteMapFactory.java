package PaooGame.Map.MapFactory.ConcreteFactory;

import PaooGame.Camera.Camera;
import PaooGame.Map.LevelMaps.ArenaMap;
import PaooGame.Map.LevelMaps.MainMap;
import PaooGame.Map.Map;
import PaooGame.Map.MapFactory.MapFactory;
import PaooGame.Map.MapType;
import PaooGame.RefLinks;

/*! \class ConcreteMapFactory extends MapFactory
    \brief Clasa concreta al Design Pattern-ului Factory.

    Clasa implementeaza notiunea de fabrica de obiecte pentru producerea hartilor din joc.
 */
public class ConcreteMapFactory extends MapFactory {

    /*! \fn Map CreateMap(MapType mapType, Camera cam, RefLinks refLinks)
       \brief Metoda de creare al unei harti.

       Metoda returneaza o referinta de tip Map reprezentand o harta concreta.
       Metoda returneaza un anumita referinta catre o harta in functie de parametru ID care determina ce tip de harta va fi returnata de metoda

       \param mapType Tipul hartii.
       \param cam Referinta catre Camera jocului.
       \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    @Override
    public Map CreateMap(MapType mapType, Camera cam, RefLinks refLinks) {

        switch (mapType)
        {
            case MainMap:
                return new MainMap(mapType, cam, refLinks);

            case ArenaMap:
                return new ArenaMap(mapType, cam, refLinks);
        }
        return null;
    }
}
