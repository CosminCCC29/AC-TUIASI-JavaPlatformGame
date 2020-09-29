package PaooGame;

/*! \class DrawThread extends Thread
    \brief Implementeaza un thread de desenat.
 */
public class DrawThread extends Thread {

    private boolean isRunning;  /*!< Starea thread-ului (activ/inactiv). */

    Game game;

    /*! \fn DrawThread(Game game)
        \brief Constructor de initializare al clasei DrawThread.

        \param game Referinta catre joc.
     */
    DrawThread(Game game)
    {
        this.game = game;
        isRunning = false;
    }

    @Override
    public void run() {

        while(isRunning) {
            game.Draw();
        }
    }

    /*! \fn public void StartThread()
        \brief Metoda de pornire a thread-ului.
     */
    public void StartThread() {
        this.isRunning = true;
        this.start();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
