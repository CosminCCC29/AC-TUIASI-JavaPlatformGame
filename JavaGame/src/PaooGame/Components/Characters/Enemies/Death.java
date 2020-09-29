package PaooGame.Components.Characters.Enemies;

import PaooGame.Components.Characters.Characters;
import PaooGame.Components.ObjectId;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;
import PaooGame.States.State;

import javax.sound.sampled.Clip;
import java.awt.*;

/*! \class Death extends Characters
    \brief Implementeaza un personaj al jocului de tip Death.
 */
public final class Death extends Characters {

    /*! \fn public Death(RefLinks refLinks, float x, float y, ObjectsHandler handler)
        \brief Constructor de initializare al clasei Death.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public Death(RefLinks refLinks, float x, float y, ObjectsHandler handler) {
        super(Assets.Death,refLinks, x, y, ObjectId.Death, new Clip[][]{AudioAssets.BossHit, AudioAssets.BossHit}, AudioAssets.BossDeath, handler);

        this.transformComponent.setWidth(CHARACTER_DEFAULT_WIDTH + 15);
        this.transformComponent.setHeight(CHARACTER_DEFAULT_HEIGHT + 15);

        animation.setSpeed(2);
        animation.setImages(this.img);

        AttackDamage = 100;
        Defence = 80;
        setAttackSpeed(38);
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia caracterului.
     */
    @Override
    public void Update() {
        super.Update();

        if(this.getLives() <= 0)
        {
            State.SetState(refLinks.getGame().getWinState());
        }
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
