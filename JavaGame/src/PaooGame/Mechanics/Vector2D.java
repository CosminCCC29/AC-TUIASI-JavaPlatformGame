package PaooGame.Mechanics;

/*! \class Vector2D
    \brief Implementeaza notiunea de vector cu o pozitie x si y.
 */
public class Vector2D
{
    float x;
    float y;

    /*! \fn Vector2D()
        \brief Constructor implicit de initializare al clasei Vector2D.
     */
    Vector2D()
    {
        x = 0.0f;
        y = 0.0f;
    }

    /*! \fn Vector2D(float x, float y)
        \brief Constructor cu parametrii de initializare al clasei Vector2D.
     */
    Vector2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

}
