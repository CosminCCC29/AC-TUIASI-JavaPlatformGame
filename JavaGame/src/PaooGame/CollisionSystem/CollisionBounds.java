package PaooGame.CollisionSystem;
import PaooGame.Components.GameObject;
import java.awt.*;

/*! \class CollisionBounds
    \brief Implementeaza notiunea de dreptunghi de coliziune.
 */
public class CollisionBounds
{
    GameObject Object; /*!< Referinta catre un obiect dupa pozitia caruia se actualizeaza pozitia dreptunghiurilor de coliziune.*/

    Rectangle Top;  /*!< Referinta catre un obiect de tip Rectangle care reprezinta dreptunghiul de coliziune din partea de sus.*/
    Rectangle Down; /*!< Referinta catre un obiect de tip Rectangle care reprezinta dreptunghiul de coliziune din partea de jos.*/
    Rectangle Left; /*!< Referinta catre un obiect de tip Rectangle care reprezinta dreptunghiul de coliziune din partea de stanga.*/
    Rectangle Right;    /*!< Referinta catre un obiect de tip Rectangle care reprezinta dreptunghiul de coliziune din partea de dreapta.*/

    /*! \fn public CollisionBounds(GameObject Object)
        \brief Constructor de initializare al clasei CollisionBounds.

        \param Object Obiectul dupa pozitia caruia se actualizeaza pozitia dreptunghiurilor de coliziune
     */
    public CollisionBounds(GameObject Object)
    {
        this.Object = Object;

        Top = new Rectangle();
        Down = new Rectangle();
        Left = new Rectangle();
        Right = new Rectangle();
    }

    /*! \fn private void Update()
        \brief Actualizeaza pozitia dreptunghiurilor de coliziune.
     */
    public void Update()
    {
        Top.x = (int)(Object.transformComponent.getPositionx() + Object.transformComponent.getWidth()/2.9);
        Top.y = (int)Object.transformComponent.getPositiony();
        Top.width = (int)(Object.transformComponent.getWidth()/3.5);
        Top.height = (int)(Object.transformComponent.getHeight()/2);

        Down.x = (int)(Object.transformComponent.getPositionx() + Object.transformComponent.getWidth()/3);
        Down.y = (int)(Object.transformComponent.getPositiony() + Object.transformComponent.getHeight()/2);
        Down.width = (int)(Object.transformComponent.getWidth()/3.15);
        Down.height = (int)(Object.transformComponent.getHeight()/2);

        Left.x = (int)(Object.transformComponent.getPositionx() + Object.transformComponent.getWidth()/3.8);
        Left.y = (int)(Object.transformComponent.getPositiony() + 10);
        Left.width = (int)(Object.transformComponent.getWidth()/4);
        Left.height = (int)(Object.transformComponent.getHeight() - 20);

        Right.x = (int)(Object.transformComponent.getPositionx() + Object.transformComponent.getWidth()/2.05);
        Right.y = (int)(Object.transformComponent.getPositiony() + 10);
        Right.width = (int)(Object.transformComponent.getWidth()/4);
        Right.height = (int)(Object.transformComponent.getHeight() - 20);
    }

    /*! \fn private void Draw()
        \brief Deseneaza dreptunghiurile de coliziune.
     */
    public void Draw(Graphics2D g)
    {
        g.draw(Top);
        g.draw(Down);
        g.draw(Left);
        g.draw(Right);
    }

    /*! \fn public Rectangle getBoundsDown()
        \brief Returneaza o referinta catre dreptunghiul de coliziune din partea de jos.
     */
    public Rectangle getBoundsDown()
    {
        return Down;
    }

    /*! \fn public Rectangle getBoundsTop()
       \brief Returneaza o referinta catre dreptunghiul de coliziune din partea de sus.
    */
    public Rectangle getBoundsTop()
    {
        return Top;
    }

    /*! \fn public Rectangle getBoundsLeft()
       \brief Returneaza o referinta catre dreptunghiul de coliziune din partea din stanga.
    */
    public Rectangle getBoundsLeft()
    {
        return Left;
    }

    /*! \fn public Rectangle getBoundsRight()
       \brief Returneaza o referinta catre dreptunghiul de coliziune din partea din dreapta.
    */
    public Rectangle getBoundsRight()
    {
        return Right;
    }

}
