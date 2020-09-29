package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    public static final int gameLogoWidth = 960;
    public static final int gameLogoHeight = 640;

    public static final int characterFramesXLength = 10;
    public static final int characterFramesYLength = 5;

    private static final int tileWidth = 64;
    private static final int tileHeight = 64;

    private static final int tile2Width = 32;
    private static final int tile2Height = 32;

    private static final int objectWidth = 32;
    private static final int objectHeight = 32;

    public static final int hpBarWidth = 204;
    public static final int hpBarHeight = 30;

    public static final int menuButtonWidth = 200;
    public static final int menuButtonsHeight = 20;


        /// Referinte catre elementele grafice (dale) utilizate in joc.

    public static BufferedImage Popup;
    public static BufferedImage IncreaseAttackDamage;
    public static BufferedImage IncreaseDefence;
    public static BufferedImage IncreaseAttackSpeed;

    public static BufferedImage IncreaseAttackDamageLogo;
    public static BufferedImage IncreaseDefenceLogo;
    public static BufferedImage IncreaseAttackSpeedLogo;

    public static BufferedImage grass;
    public static BufferedImage dirt;
    public static BufferedImage grassCornerL;
    public static BufferedImage grassCornerR;

    public static BufferedImage grass2;
    public static BufferedImage dirt2;

    public static BufferedImage clouds;
    public static BufferedImage mountain2;
    public static BufferedImage pine1;
    public static BufferedImage pine2;
    public static BufferedImage sky;
    public static BufferedImage sky_clouds;

    public static BufferedImage sky2;
    public static BufferedImage mountains2;
    public static BufferedImage mountains;
    public static BufferedImage trees2;
    public static BufferedImage trees;

    public static BufferedImage sky3;
    public static BufferedImage clouds3;
    public static BufferedImage mountains32;
    public static BufferedImage mountain31;
    public static BufferedImage trees32;
    public static BufferedImage trees31;

    public static BufferedImage gameLogo;

    public static BufferedImage house;

    public static BufferedImage[] HPbar = new BufferedImage[5];
    public static BufferedImage[] MenuButtons = new BufferedImage[3];
    public static BufferedImage InactiveMenuButton;

    public static BufferedImage[][] BlackKnight = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] Psychopath = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] BlueCleric = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] DarkRanger = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] MitheralKnight = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] PinkThief = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] PrinceRanger = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] Simpleton = new BufferedImage[characterFramesYLength][characterFramesXLength];

    public static BufferedImage[][] BronzeKnight = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] CrazyWizard = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] Death = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] EvilCleric = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] Ghost = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] GreenMonk = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] HunterOrc = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] LuckyOrc = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] MysteryBandit = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] NobleRanger = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] Priest = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] RedOrc = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] YoungThief = new BufferedImage[characterFramesYLength][characterFramesXLength];
    public static BufferedImage[][] FierceWizard = new BufferedImage[characterFramesYLength][characterFramesXLength];

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Textures/Map/Map1/MapTileSet1.png"));
            /// Variabila TEMPORARA doar pantru a initializa celelalte BufferImage-uri
            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(1, 0, tileWidth, tileHeight);
        dirt = sheet.crop(4,0, tileWidth, tileHeight);
        grassCornerL = sheet.crop(2,0, tileWidth, tileHeight);
        grassCornerR = sheet.crop(7,0, tileWidth, tileHeight);

        SpriteSheet sheet20 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/BlackKnight.png"));
        SpriteSheet sheet21 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/Psychopath.png"));
        SpriteSheet sheet22 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/BlueCleric.png"));
        SpriteSheet sheet23 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/DarkRanger.png"));
        SpriteSheet sheet24 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/MitheralKnight.png"));
        SpriteSheet sheet25 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/PinkThief.png"));
        SpriteSheet sheet26 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/PrinceRanger.png"));
        SpriteSheet sheet27 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/Simpleton.png"));

        SpriteSheet sheet28 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Stuff/HPbar.png"));
        SpriteSheet sheet29 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Stuff/MenuButtons.png"));

        SpriteSheet sheet31 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/BronzeKnight.png"));
        SpriteSheet sheet32 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/CrazyWizard.png"));
        SpriteSheet sheet33 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/Death.png"));
        SpriteSheet sheet34 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/EvilCleric.png"));
        SpriteSheet sheet35 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/Ghost.png"));
        SpriteSheet sheet36 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/GreenMonk.png"));
        SpriteSheet sheet37 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/HunterOrc.png"));
        SpriteSheet sheet38 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/LuckyOrc.png"));
        SpriteSheet sheet39 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/MysteryBandit.png"));
        SpriteSheet sheet40 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/NobleRanger.png"));
        SpriteSheet sheet41 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/Priest.png"));
        SpriteSheet sheet42 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/RedOrc.png"));
        SpriteSheet sheet43 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/YoungThief.png"));
        SpriteSheet sheet44 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Characters/FierceWizard.png"));

        for(int j = 0; j < characterFramesYLength; ++j) {
            for (int i = 0; i < characterFramesXLength; ++i) {
                BlackKnight[j][i] = sheet20.crop(i, j, objectWidth, objectHeight);
                Psychopath[j][i] = sheet21.crop(i, j, objectWidth, objectHeight);
                BlueCleric[j][i] = sheet22.crop(i, j, objectWidth, objectHeight);
                DarkRanger[j][i] = sheet23.crop(i, j, objectWidth, objectHeight);
                MitheralKnight[j][i] = sheet24.crop(i, j, objectWidth, objectHeight);
                PinkThief[j][i] = sheet25.crop(i, j, objectWidth, objectHeight);
                PrinceRanger[j][i] = sheet26.crop(i, j, objectWidth, objectHeight);
                Simpleton[j][i] = sheet27.crop(i, j, objectWidth, objectHeight);

                BronzeKnight[j][i] = sheet31.crop(i, j, objectWidth, objectHeight);
                CrazyWizard[j][i] = sheet32.crop(i, j, objectWidth, objectHeight);
                Death[j][i] = sheet33.crop(i, j, objectWidth, objectHeight);
                EvilCleric[j][i] = sheet34.crop(i, j, objectWidth, objectHeight);
                Ghost[j][i] = sheet35.crop(i, j, objectWidth, objectHeight);
                GreenMonk[j][i] = sheet36.crop(i, j, objectWidth, objectHeight);
                HunterOrc[j][i] = sheet37.crop(i, j, objectWidth, objectHeight);
                LuckyOrc[j][i] = sheet38.crop(i, j, objectWidth, objectHeight);
                MysteryBandit[j][i] = sheet39.crop(i, j, objectWidth, objectHeight);
                NobleRanger[j][i] = sheet40.crop(i, j, objectWidth, objectHeight);
                Priest[j][i] = sheet41.crop(i, j, objectWidth, objectHeight);
                RedOrc[j][i] = sheet42.crop(i, j, objectWidth, objectHeight);
                YoungThief[j][i] = sheet43.crop(i, j, objectWidth, objectHeight);
                FierceWizard[j][i] = sheet44.crop(i, j, objectWidth, objectHeight);
            }
        }

        gameLogo = ImageLoader.LoadImage("/Textures/Logo.png");
        house = ImageLoader.LoadImage("/Textures/Props/house.png");

        InactiveMenuButton = ImageLoader.LoadImage("/Textures/Stuff/InactiveButton.png");

        Popup = ImageLoader.LoadImage("/Textures/Stuff/Popup.png");
        IncreaseAttackDamage = ImageLoader.LoadImage("/Textures/Stuff/IncreaseAttack.png");
        IncreaseDefence = ImageLoader.LoadImage("/Textures/Stuff/IncreaseDefence.png");
        IncreaseAttackSpeed = ImageLoader.LoadImage("/Textures/Stuff/IncreaseAttackSpeed.png");

        IncreaseAttackDamageLogo = ImageLoader.LoadImage("/Textures/Stuff/IncreaseAttackLogo.png");
        IncreaseDefenceLogo = ImageLoader.LoadImage("/Textures/Stuff/IncreaseDefenceLogo.png");
        IncreaseAttackSpeedLogo = ImageLoader.LoadImage("/Textures/Stuff/IncreaseAttackSpeedLogo.png");

        for(int i = 0 ; i < 5; ++i)
        {
            HPbar[i] = sheet28.crop(4 - i, 0, hpBarWidth, hpBarHeight);
        }

        for(int i = 0 ; i < 3; ++i)
        {
            MenuButtons[i] = sheet29.crop(0, i, menuButtonWidth, menuButtonsHeight);
        }

        clouds = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/MainMap/cloud.png");
        mountain2 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/MainMap/mountain2.png");
        pine1 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/MainMap/pine1.png");
        pine2 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/MainMap/pine2.png");
        sky = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/MainMap/sky.png");
        sky_clouds = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/MainMap/sky_cloud.png");

        sky2 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/ArenaMap/sky2.png");
        mountains2 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/ArenaMap/mountains2.png");
        mountains = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/ArenaMap/mountains.png");
        trees2 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/ArenaMap/trees2.png");
        trees = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/ArenaMap/trees.png");

        sky3 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/PauseState/landscape6.png");
        clouds3 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/PauseState/landscape5.png");
        mountains32 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/PauseState/landscape4.png");
        mountain31 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/PauseState/landscape3.png");
        trees32  = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/PauseState/landscape2.png");
        trees31 = ImageLoader.LoadImage("/Textures/Backgrounds/ParallaxBackground/PauseState/landscape1.png");

        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/Textures/Map/Map2/Tileset_ground.png"));

        grass2 =sheet3.crop(2,0, tile2Width, tile2Height);
        dirt2 =sheet3.crop(2,1, tile2Width, tile2Height);

    }
}
