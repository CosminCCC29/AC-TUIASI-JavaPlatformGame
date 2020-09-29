package PaooGame.Components;

import PaooGame.Graph.Node;
import PaooGame.Map.Tiles.Tile;
import PaooGame.Mechanics.TransformComponent;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class GameObject implements Component
    \brief Implementeaza notiunea de obiect al jocului.
 */
public abstract class GameObject implements Component {

    public TransformComponent transformComponent;
    protected ObjectId Id;
    protected ObjectsHandler handler;
    protected RefLinks refLinks;

    /*! \fn public GameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler)
        \brief Constructor de initializare al clasei GameObject.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al prop-ului.
        \param y Pozitia de pe axa y de pe harta al prop-ului.
        \param Id Tipul prop-ului.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public GameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler)
    {
        this.handler = handler;
        this.refLinks = refLinks;
        this.Id = Id;
        transformComponent = new TransformComponent(x,y);
    }

    /*! \fn public Node getNodeCoordonates()
        \brief Returneaza o referinta de tip Node.
     */
    public Node getNodeCoordonates()
    {
        return new Node(((int)(transformComponent.getPositionx() + transformComponent.getWidth()/2) / Tile.TILE_WIDTH), (int)(transformComponent.getPositiony() + transformComponent.getHeight()/2) / Tile.TILE_HEIGHT);
    }

    /*! \fn public abstract void Update();
        \brief Metoda abstracta care actualizeaza pozitia obiectelor.
     */
    @Override
    public abstract void Update();

    /*! \fn public void Draw(Graphics g)
        \brief Metoda abstracta deseneaza obiectele.
     */
    @Override
    public abstract void Draw(Graphics g);

    /*! \fn public ObjectId GetId()
        \brief Returneaza un enum de tip ObjectId reprezentand tipul obiectului.
     */
    public abstract ObjectId GetId();
}
