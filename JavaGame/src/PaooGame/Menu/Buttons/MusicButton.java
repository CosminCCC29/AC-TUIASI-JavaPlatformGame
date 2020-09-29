package PaooGame.Menu.Buttons;

import PaooGame.Audio.AudioAssets;
import PaooGame.Database.Database;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.States.State;

import java.awt.*;

/*! \class MusicButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a seta volumul muzicii jocului prin interogarea si scrierea in baza de date.
 */
public class MusicButton extends MenuButton
{
    private static float currentVolume = AudioAssets.MUSICVOLUME;

    public MusicButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"Music", x, y);
    }

    @Override
    public void Update() {
        super.Update();

        this.name = "Music: " + (int)AudioAssets.MUSICVOLUME + "%";

        if(isReleased()) {

            // currentVolume = AudioAssets.MUSICVOLUME;

            currentVolume += 20;
            if(currentVolume > 100)
            {
                currentVolume = 0f;
            }

            refLinks.getDatabase().setVolumeValues(currentVolume, AudioAssets.SOUNDSVOLUME);
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
