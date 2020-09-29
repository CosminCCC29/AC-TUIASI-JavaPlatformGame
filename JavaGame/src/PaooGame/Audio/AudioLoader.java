package PaooGame.Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/*! \class AudioLoader
    \brief Clasa ce contine o metoda statica pentru incarcarea unui sunet in memorie.
 */
public class AudioLoader
{

    /*! \fn  public static BufferedImage loadImage(String path)
        \brief Incarca un sunet/o muzica intr-un obiect Clip si returneaza o referinta catre acesta.

        \param filePath Calea relativa pentru localizarea fisierul de tip sunet(.wav).
     */
    public static synchronized Clip LoadSoundOrMusic(String filePath) {

        Clip clip = null;

        try
        {
            File musicPath = new File(filePath);

            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            }
            else
            {
                System.out.println("File is does not exist!");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return clip;

    }

}
