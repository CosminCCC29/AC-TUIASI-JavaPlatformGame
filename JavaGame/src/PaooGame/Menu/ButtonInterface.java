package PaooGame.Menu;
import PaooGame.Components.Component;

/*! \interface ButtonInterface extends Component
    \brief Implementeaza o interfata pentru toate butoanele jocului.
 */
public interface ButtonInterface extends Component
{
    /*! \fn public void isOnButton()
        \brief Returneaza un boolean care indica daca cursorul se afla pe buton.
     */
    boolean isOnButton();

    /*! \fn public void Update()
        \brief Returneaza un boolean care indica daca butonul este apasat.
     */
    boolean isPressed();

    /*! \fn public void Update()
        \brief Returneaza un boolean care indica daca butonul a fost ridicat.
     */
    boolean isReleased();
}
