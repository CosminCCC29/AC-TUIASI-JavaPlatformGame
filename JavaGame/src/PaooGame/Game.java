package PaooGame;

import PaooGame.Components.ObjectFactory.ConcreteFactory.CharactersFactory;
import PaooGame.Components.ObjectFactory.ConcreteFactory.PropsFactory;
import PaooGame.Components.ObjectFactory.ObjectFactory;
import PaooGame.Database.Database;
import PaooGame.EventHandler.KeyInput;
import PaooGame.EventHandler.MouseInput;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.Map.MapFactory.ConcreteFactory.ConcreteMapFactory;
import PaooGame.Map.MapFactory.MapFactory;
import PaooGame.States.*;
import PaooGame.States.PlayState;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game implements Runnable {
    private GameWindow wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean runState;   /*!< Flag ce starea firului de executie.*/
    private Thread gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private DrawThread drawThread;  /*!< Referinta catre thread-ul de draw */
    public static BufferStrategy bs;


    /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************

    private static volatile Game gameInstance;

    private State playState;
    private State menuState;            /*!< Referinta catre menu.*/
    private State pauseState;        /*!< Referinta catre setari.*/
    private State winState;           /*!< Referinta catre about.*/
    private State plotState;

    private Graphics g;          /*!< Referinta catre un context grafic.*/

    private RefLinks refLinks;
    private KeyInput KeyboardInput;
    private MouseInput MouseInput;
    private Database database;

    private ObjectFactory charactersFactory;
    private ObjectFactory propsFactory;
    private MapFactory mapFactory;

    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */
    private Game(String title, int width, int height) {
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
        wnd = new GameWindow(refLinks, title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
    }

    /*! \fn public static Game getInstance()
        \brief Metoda din cadrul Singleton-ului de returnare a referintei unice
     */
    public static Game getInstance()
    {
        if (gameInstance == null)
        {
            gameInstance = new Game("Adventure Time Game", 1500, 900);
        }
        return gameInstance;
    }

    public static void Reset() { gameInstance = null;}

    /*! \fn private void InitGame()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame() {

        refLinks = new RefLinks(this);
        wnd = new GameWindow(refLinks, wnd.getWndTitle(), wnd.getWndWidth(), wnd.getWndHeight());
        /// Se incarca toate elementele grafice (dale)
        Assets.Init();

        database = new Database();
        AudioAssets.Init();

        KeyboardInput = new KeyInput();
        MouseInput = new MouseInput(refLinks);

        charactersFactory = new CharactersFactory();
        propsFactory = new PropsFactory();
        mapFactory = new ConcreteMapFactory();

        menuState       = new MenuState(refLinks);
        pauseState      = new PauseState(refLinks);
        playState       = new PlayState(refLinks);
        winState        = new WinState(refLinks);
        plotState       = new PlotState(refLinks);

        State.SetState(menuState);

        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();

        drawThread = new DrawThread(this);

    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run() {
        /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame = (double) 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState) {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if ((curentTime - oldTime) > timeFrame) {


                try {
                    Update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /// Contine un thread de desenare
                if(!drawThread.isRunning()) {
                    drawThread.StartThread();
                }



                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void StartGame()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame() {
        if (!runState) {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }  /// Thread-ul este creat si pornit deja

    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
     void Update() throws InterruptedException {

        wnd.getWndFrame().requestFocus();

        if(State.GetState() != null)
        {
            State.GetState().Update();
        }
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
     void Draw() {

        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.getCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if (bs == null) {
            /// Se executa doar la primul apel al metodei Draw()
            try {
                /// Se construieste tripul buffer
                wnd.getCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }

        /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
        /// Se sterge ce era
        g.clearRect(0, 0, wnd.getWndWidth(), wnd.getWndHeight());

        if(State.GetState() != null)
        {
            Toolkit.getDefaultToolkit().sync();
            State.GetState().Draw(g);
        }

        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();

    }

    public KeyInput getKeyInput() {
        return KeyboardInput;
    }

    public PaooGame.EventHandler.MouseInput getMouseInput() { return MouseInput; }

    public int getWndWidth() {
        return wnd.getWndWidth();
    }

    public int getWndHeight() {
        return wnd.getWndHeight();
    }

    public ObjectFactory getCharactersFactory() {
        return charactersFactory;
    }

    public ObjectFactory getPropsFactory() {
        return propsFactory;
    }

    public MapFactory getMapFactory() {
        return mapFactory;
    }

    public Graphics getGraphics() {
        return g;
    }

    public GameWindow getWnd() {
        return wnd;
    }

    public State getPlayState() {
        return playState;
    }

    public PlayState getPlayState2() {
        return (PlayState) playState;
    }

    public void setPlayState(State playState) {
        this.playState = playState;
    }

    public State getPauseState() {
        return pauseState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getWinState() {
        return winState;
    }

    public Database getDatabase() {
        return database;
    }

    public State getPlotState() {
        return plotState;
    }
}
