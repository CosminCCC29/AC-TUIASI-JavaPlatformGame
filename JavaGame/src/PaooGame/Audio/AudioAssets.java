package PaooGame.Audio;

import PaooGame.Database.Database;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/*! \class AudioAssets
    \brief Clasa incarca fiecare element audio necesar jocului.

    Game AudioAssets include toate elementele audio dintr-un joc: muzica, sunete
 */
public class AudioAssets
{
    public static float MUSICVOLUME = 0.0f;     /*!< Valuarea volumului muzicii*/
    public static float SOUNDSVOLUME = 0.0f;    /*!< Valuarea volumului sunetului*/

    public static Clip MainMenuMusic;
    public static Clip MainMapMusic;
    public static Clip BattleArenaMusic;
    public static Clip WinningMusic;
    public static Clip BossBattleMusic;
    public static Clip PlotMusic;

    public static Clip OnButton;
    public static Clip Released;

    public static Clip[] SwordMissAttacks;
    public static Clip[] SwordHitAttacks;
    public static Clip[] BowMissAttacks;
    public static Clip[] BowHitAttacks;
    public static Clip[] Zaps;
    public static Clip[] BossHit;


    public static Clip BossDeath;
    public static Clip MagicDeath;
    public static Clip ManDeath;
    public static Clip OrcDeath;

    /*! \fn public static void Init()
        \brief Metoda de initializare al AudioAsset-urilor.
     */
    public static void Init()
    {
        MainMenuMusic = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundTracks/Soliloquy.wav");
        MainMapMusic = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundTracks/Medieval Rondo.wav");
        BattleArenaMusic = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundTracks/Rage of the Champions - Castle Crashers.wav");
        WinningMusic = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundTracks/old city theme.wav");
        BossBattleMusic = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundTracks/boss theme.wav");
        PlotMusic =  AudioLoader.LoadSoundOrMusic("res/Sounds/SoundTracks/ZANZARAH - Gramire.wav");

        BossDeath = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Deaths/BossDeath.wav");
        MagicDeath = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Deaths/MagicDeath.wav");
        ManDeath = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Deaths/ManDeath.wav");
        OrcDeath = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Deaths/OrcDeath.wav");

        SwordMissAttacks = new Clip[] {
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordMiss1.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordMiss2.wav"),
        };


        SwordHitAttacks = new Clip[] {
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit1.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit2.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit3.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit4.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit5.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit6.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit7.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/SwordHit8.wav"),
        };

        BowMissAttacks = new Clip[] {
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/BowMiss1.wav"),
        };

        BowHitAttacks = new Clip[] {
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/BowHit1.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/BowHit2.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/BowHit3.wav"),
        };

        Zaps = new Clip[] {
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/zap1.wav"),
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/zap2.wav"),
        };

        BossHit = new Clip[] {
                AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Attacks/BossHit1.wav"),
        };

        OnButton = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Button/click2.wav");
        Released = AudioLoader.LoadSoundOrMusic("res/Sounds/SoundEffects/Button/click2.wav");

        Database.InitVolumeValues();
        InitVolume();
    }

    /*! \fn public static void StopAllMusic()
        \brief Metoda de oprire al tuturor AudioAsset-urilor corespunzatoare muzicii.
     */
    public static void StopAllMusic()
    {
        MainMenuMusic.setFramePosition(0);
        MainMenuMusic.stop();

        MainMapMusic.setFramePosition(0);
        MainMapMusic.stop();

        BattleArenaMusic.setFramePosition(0);
        BattleArenaMusic.stop();

        WinningMusic.setFramePosition(0);
        WinningMusic.stop();

        BossBattleMusic.setFramePosition(0);
        BossBattleMusic.stop();

        BossBattleMusic.setFramePosition(0);
        BossBattleMusic.stop();

        PlotMusic.setFramePosition(0);
        PlotMusic.stop();
    }

    /*! \fn public static void StopAllDeathSounds()
        \brief Metoda de oprire al tuturor AudioAsset-urilor corespunzatoare sunetelor in momentul cand personajele raman fara viata.
     */
    public static void StopAllDeathSounds()
    {
        ManDeath.setFramePosition(0);
        ManDeath.stop();

        BossDeath.setFramePosition(0);
        BossDeath.stop();

        MagicDeath.setFramePosition(0);
        MagicDeath.stop();

        OrcDeath.setFramePosition(0);
        OrcDeath.stop();
    }

    /*! \fn public static void StopAllHitSounds()
        \brief Metoda de oprire al tuturor AudioAsset-urilor corespunzatoare suneturilor de atac ale personajelor.
     */
    public static void StopAllHitSounds() {

        for(Clip clip : SwordHitAttacks)
        {
            clip.setFramePosition(0);
            clip.stop();
        }

        for(Clip clip : SwordMissAttacks)
        {
            clip.setFramePosition(0);
            clip.stop();
        }

        for(Clip clip : BowHitAttacks)
        {
            clip.setFramePosition(0);
            clip.stop();
        }

        for(Clip clip : BowMissAttacks)
        {
            clip.setFramePosition(0);
            clip.stop();
        }

        for(Clip clip : Zaps)
        {
            clip.setFramePosition(0);
            clip.stop();
        }

        for(Clip clip : BossHit)
        {
            clip.setFramePosition(0);
            clip.stop();
        }

    }

    /*! \fn public static void InitVolume()
        \brief Metoda de initializare al volumului muzicii si al sunetelor din joc.
     */
    public static void InitVolume()
    {
        float db = (float) (Math.log(MUSICVOLUME/100f) / Math.log(10) * 20);

        FloatControl gain = (FloatControl)MainMenuMusic.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)MainMapMusic.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)BattleArenaMusic.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)WinningMusic.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)BossBattleMusic.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)PlotMusic.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        db = (float) (Math.log(SOUNDSVOLUME/100f) / Math.log(10) * 20);

        for(Clip clip : SwordHitAttacks)
        {
            gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }

        for(Clip clip : SwordMissAttacks)
        {
            gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }

        for(Clip clip : BowHitAttacks)
        {
            gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }

        for(Clip clip : BowMissAttacks)
        {
            gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }

        for(Clip clip : Zaps)
        {
            gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }

        for(Clip clip : BossHit)
        {
            gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }

        gain = (FloatControl)BossDeath.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)MagicDeath.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)ManDeath.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)OrcDeath.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)OnButton.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

        gain = (FloatControl)Released.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(db);

    }
}


