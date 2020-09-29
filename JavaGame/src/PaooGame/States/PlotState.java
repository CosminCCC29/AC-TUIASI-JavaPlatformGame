package PaooGame.States;
import PaooGame.Audio.AudioAssets;
import PaooGame.RefLinks;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;

/*! \class PlotState extends State
    \brief Implementeaza notiunea de poveste a jocului.
 */
public class PlotState extends State
{
    private static short transparecyIndex = 0;

    /*! \fn public PlotState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlotState(RefLinks refLink)
    {
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza componentele povestii.
     */
    @Override
    public void Update() {

        if (!AudioAssets.PlotMusic.isRunning()) {
            AudioAssets.StopAllMusic();
            AudioAssets.PlotMusic.start();
            AudioAssets.PlotMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }

        if (refLinks.getKeyInput().getPressedKey() != null && refLinks.getKeyInput().getPressedKey().getKeyCode() == KeyEvent.VK_ENTER) {

            refLinks.getDatabase().NewGame();

            transparecyIndex = 0;

            refLinks.getGame().setPlayState(new PlayState(refLinks));
            PlayState.setEnemiesKilled(0);

            State.SetState(refLinks.getGame().getPlayState());
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a povestii.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, refLinks.getWndWidth(), refLinks.getWndHeight());

        ++transparecyIndex;
        transparecyIndex = (transparecyIndex > 255)? 255 : transparecyIndex;

        g.setColor(new Color(192,192,192, transparecyIndex));
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
        g.drawString("      The Blue Cleric is the last of his royal family. He was endowed with a brilliant mind, and", 0, 32 * 5);
        g.drawString("  his influence brought peace, both at home during his father's reign and abroad. However, as the", 0, 32 * 7);
        g.drawString("  Father of The Blue Cleric died when he was too young to lead, the issue of succession was raised", 0, 32 * 9);
        g.drawString("  and the temperament of some people came to an end. A secret discussion broke out between", 0, 32 * 11);
        g.drawString("  aristocrats, soldiers and villagers who think they are best suited to lead. The feudal lords", 0, 32 * 13);
        g.drawString("  became more and more desperate with each passing day. As the days of peace between them pass,", 0, 32 * 15);
        g.drawString("  The Blue Cleric knows that he must fight to preserve the kingdom that rightfully belongs to him.", 0, 32 * 17);

        g.drawString("Press ENTER to Continue.." , refLinks.getWndWidth()/2 - 32 * 5, (int) (refLinks.getWndHeight() * 0.925));

    }

}
