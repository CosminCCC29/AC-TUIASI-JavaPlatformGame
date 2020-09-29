package PaooGame.Components.Props;

import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectId;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class Props extends GameObject
    \brief Implementeaza notiunea de prop al jocului.
 */
public abstract class Props extends GameObject {

    private BufferedImage img;  /*!<Referinta catre un tablou bidimensional de imagini ale caracterului.*/
    protected Rectangle bnds;   /*!< Referinta catre dreptunghiurile de coliziune.*/
    protected boolean Bounds;   /*!< Membru de tip boolean care specifica daca prop-ului i se va crea dreptunghiuri de coliziune*/

    /*! \fn public Props(BufferedImage[][] img, RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler)
        \brief Constructor de initializare al clasei Props.

        \param img Referinta catre un tablou bidimensional de imagini ale prop-ului.
        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al prop-ului.
        \param y Pozitia de pe axa y de pe harta al prop-ului.
        \param Id Tipul prop-ului.
        \param handler Referinta catre handler-ul de obiecte.
        \param Bounds Parametru de tip boolean care specifica daca prop-ului i se va crea dreptunghiuri de coliziune.
     */
    public Props(BufferedImage img, RefLinks refLinks, int x, int y, ObjectId Id, ObjectsHandler handler, boolean Bounds) {
        super(refLinks,x, y, Id, handler);

        this.img = img;
        handler.addProps(this);

        this.Bounds = Bounds;

        if(Bounds)
        bnds = new Rectangle(x, y,0,0);
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia dreptunghiurilor de coliziune.
     */
    @Override
    public void Update() {

        if(Bounds)
        bnds.setBounds((int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), (int)transformComponent.getWidth(), (int)transformComponent.getHeight());
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza prop-ul.
     */
    @Override
    public void Draw(Graphics g) {

        g.drawImage(img,(int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), (int)transformComponent.getWidth(), (int)transformComponent.getHeight(), null);

    }

    /*! \fn public ObjectId GetId()
        \brief Returneaza un enum de tip ObjectId reprezentand tipul prop-ului.
     */
    @Override
    public abstract ObjectId GetId();

    /*! \fn public Rectangle getCollisionBounds()
        \brief Returneaza o referinta de tip Rectangle reprezentand dreptunghiul de coliziune.
     */
    public Rectangle getCollisionBounds()
    {
        return this.bnds;
    }
}
