package PaooGame.ObjectHandlerObserver;
import PaooGame.Components.Characters.Characters;
import PaooGame.Components.ObjectId;
import PaooGame.Components.Props.Props;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.LinkedList;

/*! \class ObjectsHandler
    \brief Implementeaza notiunea de handler de obiecte.

    Acesta este design pattern-ul Observer.
    Subiectul este clasa ObjectHandler, iar observer-u este clasa GameObject

    Acesta contine doua liste, de Caractere si de Prop-uri, iar in functie de o stare (Update sau Draw), toate obiectele din liste vor fi notificate si vor executa comanda.
 */
public class ObjectsHandler
{
    private RefLinks refLinks;                     /*!< Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program. */
    private LinkedList<Characters> Characters;     /*!< Referinta catre lista de caractere */
    private LinkedList<Props> Props;               /*!< Referinta catre lista de prop-uri */
    private StateType state;                       /*!< Referinta catre starea curenta a Subiectului. */

    /*! \fn public ObjectsHandler(RefLinks refLinks)
        \brief Constructor de initializare al clasei ObjectsHandler.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public ObjectsHandler(RefLinks refLinks)
    {
        this.refLinks = refLinks;
        Characters = new LinkedList<>();
        Props = new LinkedList<>();
    }

    public void setState(StateType state, Graphics g)
    {
        this.state = state;
        notifyAllObjects(g);
    }

    public StateType getState()
    {
        return this.state;
    }

    /*! \fn public ObjectsHandler(RefLinks refLinks)
        \brief Metoda privata ce notifica obiectele din liste cu privire la comanda pe care trebuie sa fie executata.

        \param Referinta catre un context grafic.
     */
    private void notifyAllObjects(Graphics g)
    {
        for (Props prop : Props) {
            switch (state) {
                case Update:
                    prop.Update();
                    break;

                case Draw:
                    prop.Draw(g);
                    break;
            }
        }

        for (Characters character : Characters) {
            switch (state) {
                case Update:
                    character.Update();
                    break;

                case Draw:
                    character.Draw(g);
                    break;
            }
        }

        if(state == StateType.Update)
        {
            refLinks.getKeyInput().setPressedKey(null);
            refLinks.getKeyInput().setReleasedKey(null);
        }

    }

    /*! \fn public Characters getMainCharacter()
        \brief Returneaza caracterul principal(Player-ul) din lista de caractere.
     */
    public Characters getMainCharacter()
    {
        for (Characters character : Characters) {
            if(character.GetId() == ObjectId.Player)
                return character;
        }
        return null;
    }

    /*! \fn public Characters getFirstEnemy()
       \brief Returneaza primul inamic din lista de caractere.
    */
    public Characters getFirstEnemy()
    {
        for (Characters character : Characters) {
            if(character.GetId() != ObjectId.Player)
                return character;
        }
        return null;
    }

    /*! \fn public Characters getFirstEnemyIntersectingPlayer()
       \brief Returneaza primul inamic din lista de caractere care intersecteaza dreptunghiul de coliziune al caracterului principal.
    */
    public Characters getFirstEnemyIntersectingPlayer()
    {
        for (Characters character : Characters) {
            if(character.GetId() != ObjectId.Player && character.getCollisionBounds().getBoundsDown().intersects(getMainCharacter().getCollisionBounds().getBoundsDown()))
                return character;
        }
        return null;
    }

    public void addCharacter(Characters object)
    {
        Characters.add(object);
    }

    public void removeCharacter(Characters object)
    {
        Characters.remove(object);
    }

    public void addProps(Props object)
    {
        Props.add(object);
    }

    public void removeProps(Props object)
    {
        Props.remove(object);
    }

    public LinkedList<Characters> getCharactersHandler() {
        return Characters;
    }

    public LinkedList<Props> getPropsHandler() {
        return Props;
    }

    public Characters getCharacterTypeReference(ObjectId objectId)
    {
        for(Characters character : Characters)
        {
            if(character.GetId() == objectId)
            {
                return character;
            }
        }
        return null;
    }
}
