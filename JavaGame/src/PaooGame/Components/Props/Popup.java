package PaooGame.Components.Props;

import PaooGame.Components.Characters.Characters;
import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectId;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class Popup extends GameObject
    \brief Implementeaza notiunea de fereastra pop-up.

    Obiectul de tip Popup va afisa valorile de atac, aparare si viteza de atac ale caracterului.
 */
public class Popup extends GameObject
{
    private BufferedImage img;
    private Characters character;

    final int WIDTH = 930/4;
    final int HEIGHT = 498/4;

    /*! \fn public Popup(RefLinks refLinks, GameObject Object)
        \brief Constructor de initializare al clasei CollisionBounds.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param Object Obiectul pentru care se afiseaza status-ul.
     */
    public Popup(RefLinks refLinks, Characters character) {
        super(refLinks, 0, 0, ObjectId.Popup, null);

        this.character = character;
        img = Assets.Popup;

        this.transformComponent.setWidth(WIDTH);
        this.transformComponent.setHeight(HEIGHT);

        transformComponent.setPositionx(character.transformComponent.getPositionx() - character.transformComponent.getWidth()/2);
        transformComponent.setPositiony(character.transformComponent.getPositiony() - HEIGHT * 1.5f );
    }


    /*! \fn public void Update()
        \brief Metoda vida.
     */
    @Override
    public void Update() { }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza obiectul de tip Pop-up.
     */
    @Override
    public void Draw(Graphics g) {

            g.drawImage(img, (int)transformComponent.getPositionx(), (int)transformComponent.getPositiony(), (int)transformComponent.getWidth(), (int)transformComponent.getHeight(), null);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
            g.drawString("Press ENTER to fight..", (int)transformComponent.getPositionx() - 28, (int)(transformComponent.getPositiony() + HEIGHT * 1.3f));
            g.drawString(character.GetId().name(), (int)(transformComponent.getPositionx() + WIDTH/2 - character.GetId().name().length() * 7.5), (int)(transformComponent.getPositiony() - HEIGHT * 0.125f));

            g.setColor(Color.RED);
            g.drawString(String.valueOf(character.getAttackDamage()), (int)(transformComponent.getPositionx() + WIDTH/2.05f), (int)(transformComponent.getPositiony() + HEIGHT/3.4f));

            g.setColor(Color.BLUE);
            g.drawString(String.valueOf(character.getDefence()), (int)(transformComponent.getPositionx() + WIDTH/1.75f), (int)(transformComponent.getPositiony() + HEIGHT/1.7));

            g.setColor(Color.YELLOW);
            g.drawString(String.valueOf(character.getAttackSpeed()), (int)(transformComponent.getPositionx() + WIDTH/2.0f), (int)(transformComponent.getPositiony() + HEIGHT/1.125));

    }

    /*! \fn public ObjectId GetId()
        \brief Returneaza un enum de tip ObjectId reprezentand tipul caaracterului.
     */
    @Override
    public ObjectId GetId() {
        return null;
    }

}
