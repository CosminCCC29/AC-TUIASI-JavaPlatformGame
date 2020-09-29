package PaooGame.States;
import PaooGame.Audio.AudioAssets;
import PaooGame.Camera.Camera;
import PaooGame.EventHandler.KeyInput;
import PaooGame.Map.Map;
import PaooGame.Map.MapType;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.RefLinks;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;

/*! \class WinState extends State
    \brief Implementeaza notiunea de poveste de final al jocului.
 */
public class WinState extends State
{

    private static short transparecyIndex = 0;

    /*! \fn public WinState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public WinState(RefLinks refLink)
    {
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza componentele povestii de final a jocului.
     */
    @Override
    public void Update()
    {
        if(!AudioAssets.BossBattleMusic.isRunning())
        {
            AudioAssets.StopAllMusic();
            AudioAssets.BossBattleMusic.start();
            AudioAssets.BossBattleMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }

        if (refLinks.getKeyInput().getPressedKey() != null && refLinks.getKeyInput().getPressedKey().getKeyCode() == KeyEvent.VK_ENTER) {
                transparecyIndex = 0;
                State.SetState(refLinks.getGame().getMenuState());
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a povestii.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        ++transparecyIndex;
        transparecyIndex = (transparecyIndex > 255)? 255 : transparecyIndex;

        g.setColor(Color.BLACK);
        g.fillRect(0,0, refLinks.getWndWidth(), refLinks.getWndHeight());

        g.setColor(new Color(192, 192, 192, transparecyIndex));
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
        g.drawString("      And so the Blue Cleric earned his right to rule the kingdom. He brought peace and joy to the", 0, 32 * 10);
        g.drawString("  whole kingdom until the end of his days. During his life he had no son, and in this situation,", 0, 32 * 13);
        g.drawString("  the future of the kingdom remains unknown...", 0, 32 * 16);

        g.drawString("Press ENTER to Continue.." , refLinks.getWndWidth()/2 - 32 * 5, (int) (refLinks.getWndHeight() * 0.925));
    }
}
