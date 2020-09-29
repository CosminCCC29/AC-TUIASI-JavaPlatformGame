package PaooGame.Menu.Buttons;

import PaooGame.Audio.AudioAssets;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.States.State;

import java.awt.*;

/*! \class SoundButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.
 */
public class SoundButton extends MenuButton
{
    private static float currentVolume = AudioAssets.SOUNDSVOLUME;

    public SoundButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"Sound", x, y);
    }

    @Override
    public void Update() {
        super.Update();

        this.name = "Sounds: " + (int)AudioAssets.SOUNDSVOLUME + "%";

        if(isReleased()) {

            //currentVolume = AudioAssets.SOUNDSVOLUME;

            currentVolume += 20;
            if(currentVolume > 100)
            {
                currentVolume = 0f;
            }

            refLinks.getDatabase().setVolumeValues(AudioAssets.MUSICVOLUME, currentVolume);
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
