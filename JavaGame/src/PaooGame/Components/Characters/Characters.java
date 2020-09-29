package PaooGame.Components.Characters;
import PaooGame.CollisionSystem.CollisionBounds;
import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectId;
import PaooGame.Components.Props.Popup;
import PaooGame.Graph.Node;
import PaooGame.GraphPathFindingSystem.PathFindingSystem;
import PaooGame.Graphics.Animation;
import PaooGame.Map.MapType;
import PaooGame.StatusSystem.AttackSystem;
import PaooGame.StatusSystem.HealthSystem;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*! \class Characters extends GameObject
    \brief Implementeaza notiunea de caracter al jocului.

    Clasa Characters contine tot ce tine de un caracter al unui joc:
            Viata, Atac, Aparare, Viteza de atac,
            Coliziuni cu harta,
            Sistem de viata,
            Sistem de atac,
            Animatiile gesturilor caracterului.

 */
public abstract class Characters extends GameObject {

    public static final int DEFAULT_LIFE = 100;             /*!< Viata implicita fiecarui caracter.*/
    public static final int DEFAULT_ATTACK_DAMAGE = 10;     /*!< Valoarea de atac implicita fiecarui caracter.*/
    public static final int DEFAULT_DEFENCE = 5;            /*!< Valoarea de apararea implicita fiecarui caracter.*/
    public static final int DEFAULT_ATTACK_SPEED = 5;       /*!< Viteza de atac implicita fiecarui caracter.*/
    public static final int DEFAULT_ATTACK_COOLDOWN = 55;   /*!< Timpul implicit necesar de asteptare fiecarui caracter pentru a putea ataca. */

    protected BufferedImage[][] img;                        /*!< Referinta catre un tablou bidimensional de imagini ale caracterului. */

    protected final Clip[][] ATTACKSOUND;                   /*!< Referinta catre un tablou bidimensional de sunete de atac.*/
    protected final Clip DEATHSOUND;                        /*!< Referinta catre un sunet de moarte.*/

    protected int Lives;                                    /*!< Viata caracterului.*/
    protected int AttackDamage;                             /*!< Atacul caracterului.*/
    protected int Defence;                                  /*!< Apararea catacterului.*/
    protected int AttackSpeed;                              /*!< Viteza de atac al caractrului.*/
    protected CollisionBounds bnds;                         /*!< Referinta catre dreptunghiurile de coliziune.*/
    protected Animation animation;                          /*!< Referinta catre sistemul de animatie al caracterului.*/
    protected AttackSystem attackSystem;                    /*!< Referinta catre sistemul de atac.*/
    protected HealthSystem healthSystem;                    /*!< Referinta catre sistemul de viata.*/
    protected GameObject Popup = null;                      /*!< Referinta catre un obiect de tip Popup.*/

    protected final static int CHARACTER_DEFAULT_WIDTH = 110;   /*!< Latimea implicita a caracterului.*/
    protected final static int CHARACTER_DEFAULT_HEIGHT = 110;  /*!< Inaltimea implicita a caracterului.*/

    /*! \fn public Characters(BufferedImage[][] img, RefLinks refLinks, float x, float y, ObjectId Id, Clip[][] attacksound, Clip deathsound, ObjectsHandler handler)
        \brief Constructor de initializare al clasei Characters.

        \param img Referinta catre un tablou bidimensional de imagini ale caracterului.
        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param x Pozitia de pe axa x de pe harta al personajului.
        \param y Pozitia de pe axa y de pe harta al personajului.
        \param Id Tipul caracterului.
        \param attacksound Referinta catre un sunet de atac.
        \param deathsound Referinta catre sunet cand caracterul moare.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public Characters(BufferedImage[][] img, RefLinks refLinks, float x, float y, ObjectId Id, Clip[][] attacksound, Clip deathsound, ObjectsHandler handler) {
        super(refLinks, x, y, Id, handler);

        this.img = img;
        ATTACKSOUND = attacksound;
        DEATHSOUND = deathsound;

        Lives = DEFAULT_LIFE;
        AttackDamage = DEFAULT_ATTACK_DAMAGE;
        Defence = DEFAULT_DEFENCE;
        AttackSpeed = DEFAULT_ATTACK_SPEED;

        handler.addCharacter(this);

        bnds = new CollisionBounds(this);
        animation = new Animation(this);
        attackSystem = new AttackSystem(refLinks,this, handler, DEFAULT_ATTACK_COOLDOWN - AttackSpeed);
        healthSystem = new HealthSystem(refLinks,this, handler);

    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia caracterului
     */
    @Override
    public void Update() {

        this.getInputFollow();
        if(Lives > 0) transformComponent.WalkUpdate();
        transformComponent.GravityUpdate();
        animation.Update();
        bnds.Update();

        if(Popup == null && refLinks.getMap().getMapType() == MapType.MainMap && this.GetId() != ObjectId.Player && getCollisionBounds().getBoundsDown().intersects(handler.getMainCharacter().getCollisionBounds().getBoundsDown())) {
            Popup = new Popup(refLinks, this);
        }
        else if(Popup != null && refLinks.getMap().getMapType() == MapType.MainMap && this.GetId() != ObjectId.Player && !getCollisionBounds().getBoundsDown().intersects(handler.getMainCharacter().getCollisionBounds().getBoundsDown()))
        {
            Popup = null;
        }


        attackSystem.Update();
        healthSystem.Update();

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza caracterul.
     */
    @Override
    public void Draw(Graphics g) {
        animation.Draw(g);

        if(Popup != null && refLinks.getMap().getMapType() == MapType.MainMap)
        Popup.Draw(g);

        g.setColor(Color.RED);

        attackSystem.Draw(g);
        healthSystem.Draw(g);

    }

    /*! \fn public void getInputFollow()
        \brief Metoda care actualizeaza pozitia caracterelor in functie de caracterul principal

        Metoda este privata deoarece este apelata doar in clasa Characters.
        Metoda primeste ca input o lista de obiecte de tip Node reprezentand output-ul algoritmului Dijkstra(drumul de la caracterul curent, la caraterul principal).

        Urmariea caracterului principal se realizeaza astfel: output-ul fiind o lista de obiecte de tip Node cu anumite pozitii,
        primul nod din lista, respectiv cel mai apropiat nod de pozitia caracterului, indica directia de deplasare al caracterului.
     */
    private void getInputFollow() {
        if (this.Id != ObjectId.Player) {

            ArrayList<Node> path = null;

            if (refLinks.getMap().getMapType() == MapType.ArenaMap)
                path = PathFindingSystem.PathFindingDijkstra(this.getNodeCoordonates(), handler.getMainCharacter().getNodeCoordonates(), refLinks.getMap().getGraph());

            if (path != null) {

                if (path.get(0).getY() < this.getNodeCoordonates().getY()) {

                    if (!transformComponent.isJumping() || !transformComponent.isJumping2()) {

                        if (transformComponent.isJumping() && transformComponent.getVelocityy() > 0.15f) {
                            transformComponent.setJumping2(true);
                            transformComponent.setVelocityy(-1);
                            transformComponent.setSpeedy(12.0f);
                        } else {

                            if (!transformComponent.isJumping()) {
                                transformComponent.setJumping(true);
                                transformComponent.setVelocityy(-1);
                                transformComponent.setSpeedy(15.0f);
                            }
                        }
                    }
                    return;
                }

                if (path.get(0).getX() > this.getNodeCoordonates().getX()) {
                    this.transformComponent.setVelocityx(1);
                    this.transformComponent.setSpeedx(6.0f);
                } else if (path.get(0).getX() < this.getNodeCoordonates().getX()) {
                    this.transformComponent.setVelocityx(-1);
                    this.transformComponent.setSpeedx(6.0f);
                }
            } else {
                transformComponent.setVelocityx(0);
                transformComponent.setSpeedx(0.0f);
            }
        }
    }

    /*! \fn public abstract ObjectId GetId()
        \brief Deseneaza elementele din tabloul de imagini(fundalurile).
     */
    @Override
    public abstract ObjectId GetId();

    /*! \fn public void setLife(int Lives)
     */
    public void setLife(int Lives) {
        this.Lives = Lives;
    }

    /*! \fn public int getLives()
        */
    public int getLives() {
        return this.Lives;
    }

    /*! \fn public void setAttackDamage(int attackDamage)
        */
    public void setAttackDamage(int attackDamage) {
        AttackDamage = attackDamage;
    }

    /*! \fn public int getAttackDamage()
        */
    public int getAttackDamage() {
        return AttackDamage;
    }

    /*! \fn public void setDefence(int defence)
        */
    public void setDefence(int defence) {
        Defence = defence;
    }

    /*! \fn public int getDefence(
        */
    public int getDefence() {
        return Defence;
    }

    /*! \fn public void setAttackSpeed(int attackSpeed)
        */
    public void setAttackSpeed(int attackSpeed) {
        if(attackSpeed < DEFAULT_ATTACK_COOLDOWN) {
            AttackSpeed = attackSpeed;
            attackSystem.setSpeed(DEFAULT_ATTACK_COOLDOWN - attackSpeed);
        }
    }

    /*! \fn public int getAttackSpeed()
        */
    public int getAttackSpeed() {
        return AttackSpeed;
    }

    /*! \fn public CollisionBounds getCollisionBounds()
        */
    public CollisionBounds getCollisionBounds() {
        return this.bnds;
    }

    /*! \fn public Animation getAnimation()
        */
    public Animation getAnimation() {
        return animation;
    }

    /*! \fn public AttackSystem getAttackSystem()
        */
    public AttackSystem getAttackSystem() {
        return attackSystem;
    }

    /*! \fn public HealthSystem getHealthSystem()
        */
    public HealthSystem getHealthSystem() {
        return healthSystem;
    }

    /*! \fn public Clip getDEATHSOUND()
        */
    public Clip getDEATHSOUND() {
        return DEATHSOUND;
    }

    /*! \fn  public Clip[][] getATTACKSOUND()
        */
    public Clip[][] getATTACKSOUND() {
        return ATTACKSOUND;
    }
}
