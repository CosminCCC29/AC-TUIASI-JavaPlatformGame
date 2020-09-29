package PaooGame.Components.ObjectFactory.ConcreteFactory;
import PaooGame.Components.Characters.*;
import PaooGame.Components.Characters.Enemies.*;
import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectFactory.ObjectFactory;
import PaooGame.Components.ObjectId;
import PaooGame.EventHandler.KeyInput;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

/*! \class CharactersFactory extends ObjectFactory
    \brief Clasa concreta al Design Pattern-ului Factory.

    Clasa implementeaza notiunea de fabrica de obiecte pentru producerea de caractere.
 */
public class CharactersFactory extends ObjectFactory
{

    /*! \fn GameObject CreateGameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler, KeyInput KeyboardInput)
        \brief Metoda de creare al unui caracter.

        Metoda returneaza o referinta de tip GameObject reprezentand un caracter concret.
        Metoda returneaza un anumit caracter in functie de parametru ID care determina ce tip de caracter va fi returnat de metoda.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param Id Tipul caracterului.
        \param handler Referinta catre handler-ul de obiecte.
        \param KeyboardInput Referinta catre un obiect de tip KeyInput pentru input-ul de la tastatura.
     */
    @Override
    public GameObject CreateGameObject(RefLinks refLinks, float x, float y, ObjectId Id, ObjectsHandler handler, KeyInput KeyboardInput) {

        switch (Id)
        {
            case Player:
                return new MainCharacter(refLinks,x,y, handler,KeyboardInput);

            case BlackKnight:
                return new BlackKnight(refLinks,x,y, handler);

            case Psychopath:
                return new Psychopath(refLinks,x,y, handler);

            case BlueCleric:
                return new BlueCleric(refLinks,x,y, handler);

            case DarkRanger:
                return new DarkRanger(refLinks,x,y, handler);

            case MitheralKnight:
                return new MitheralKnight(refLinks,x,y, handler);

            case PinkThief:
                return new PinkThief(refLinks,x,y, handler);

            case PrinceRanger:
                return new PrinceRanger(refLinks,x,y, handler);

            case BronzeKnight:
                return new BronzeKnight(refLinks,x,y, handler);

            case Simpleton:
                return new Simpleton(refLinks,x,y, handler);

            case CrazyWizard:
                return new CrazyWizard(refLinks,x,y, handler);

            case Death:
                return new Death(refLinks,x,y, handler);

            case EvilCleric:
                return new EvilCleric(refLinks,x,y, handler);

            case FierceWizard:
                return new FierceWizard(refLinks,x,y, handler);

            case Ghost:
                return new Ghost(refLinks,x,y, handler);

            case GreenMonk:
                return new GreenMonk(refLinks,x,y, handler);

            case HunterOrc:
                return new HunterOrc(refLinks,x,y, handler);

            case LuckyOrc:
                return new LuckyOrc(refLinks,x,y, handler);

            case MysteryBandit:
                return new MysteryBandit(refLinks,x,y, handler);

            case NobleRanger:
                return new NobleRanger(refLinks,x,y, handler);

            case Priest:
                return new Priest(refLinks,x,y, handler);

            case RedOrc:
                return new RedOrc(refLinks,x,y, handler);

            case YoungThief:
                return new YoungThief(refLinks,x,y, handler);

        }
    return null;
    }
}
