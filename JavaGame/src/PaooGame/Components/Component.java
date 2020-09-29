package PaooGame.Components;

import java.awt.*;

/*! \interface Component
    \brief Implementeaza o interfata pentru toate componentele jocului.
 */
public interface Component
{
    /*! \fn void Update()
        \brief Actualizeaza componentele.
     */
    void Update();
    /*! \fn void Draw(Graphics g)
        \brief Deseneaza componentele.
     */
    void Draw(Graphics g);
}
