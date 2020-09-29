package PaooGame.Components.Characters.Enemies;

import PaooGame.Components.Characters.Characters;
import PaooGame.Components.ObjectId;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import javax.sound.sampled.Clip;
import java.awt.*;

/*! \class LuckyOrc extends Characters
    \brief Implementeaza un personaj al jocului de tip LuckyOrc.
 */
public final class LuckyOrc extends Characters {

    /*! \fn public LuckyOrc(RefLinks refLinks, float x, float y, ObjectsHandler handler)
        \brief Constructor de initializare al clasei LuckyOrc.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public LuckyOrc(RefLinks refLinks, float x, float y, ObjectsHandler handler) {
        super(Assets.LuckyOrc,refLinks, x, y, ObjectId.LuckyOrc, new Clip[][]{AudioAssets.SwordHitAttacks, AudioAssets.SwordMissAttacks}, AudioAssets.OrcDeath, handler);

        this.transformComponent.setWidth(CHARACTER_DEFAULT_WIDTH);
        this.transformComponent.setHeight(CHARACTER_DEFAULT_HEIGHT);

        animation.setSpeed(2);
        animation.setImages(this.img);

        AttackDamage = 70;
        Defence = 50;
        setAttackSpeed(12);
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia caracterului.
     */
    @Override
    public void Update() {
        super.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza caracterul
     */
    @Override
    public void Draw(Graphics g)
    {
        super.Draw(g);
    }

    /*! \fn public ObjectId GetId()
        \brief Returneaza un enum de tip ObjectId reprezentand tipul caaracterului.
     */
    @Override
    public ObjectId GetId() {
        return this.Id;
    }
}
