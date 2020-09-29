package PaooGame.States;

import PaooGame.Camera.Camera;
import PaooGame.Camera.ParallaxEffect;
import PaooGame.CollisionSystem.CollisionUpdate;
import PaooGame.Components.Characters.Characters;
import PaooGame.Components.GameObject;
import PaooGame.Components.ObjectId;
import PaooGame.Audio.AudioAssets;
import PaooGame.Components.Props.House;
import PaooGame.Map.LevelMaps.EmptyMap;
import PaooGame.Map.Map;
import PaooGame.Map.MapType;
import PaooGame.Map.Tiles.Tile;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;
import PaooGame.ObjectHandlerObserver.StateType;
import PaooGame.RefLinks;
import PaooGame.StatusSystem.WinLoseBattleSystem;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/*! \class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    public static final int CHARACTERS = 22;
    private static boolean[] aliveCharacters = new boolean[CHARACTERS];
    private static int EnemiesKilled = 0;

    private final WinLoseBattleSystem winLoseBattleSystem;

    private static ObjectsHandler handler;
    protected final Camera cam;
    protected ParallaxEffect parallaxEffect;
    protected Map CurrentMap;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLinks)
    {
        super(refLinks);
        CurrentMap = new EmptyMap();

        for(int i = 0; i < CHARACTERS; ++i)
            aliveCharacters[i] = true;

        handler = new ObjectsHandler(refLinks);

        refLinks.getCharactersFactory().CreateGameObject(refLinks,8 * Tile.TILE_WIDTH,9 * Tile.TILE_HEIGHT, ObjectId.Player, handler, refLinks.getKeyInput());
        refLinks.getPropsFactory().CreateGameObject(refLinks, -4 * Tile.TILE_WIDTH,12 * Tile.TILE_HEIGHT - House.HOUSEHEIGHT, ObjectId.House, handler, refLinks.getKeyInput());
        refLinks.getPropsFactory().CreateGameObject(refLinks, 26 * Tile.TILE_WIDTH,12 * Tile.TILE_HEIGHT - House.HOUSEHEIGHT, ObjectId.House, handler, refLinks.getKeyInput());
        refLinks.getPropsFactory().CreateGameObject(refLinks, 53* Tile.TILE_WIDTH,12 * Tile.TILE_HEIGHT - House.HOUSEHEIGHT, ObjectId.House, handler, refLinks.getKeyInput());


        cam = new Camera(handler.getMainCharacter(),refLinks);

        winLoseBattleSystem = new WinLoseBattleSystem(refLinks, handler);

        setLevelMap(MapType.MainMap);
    }

    @Override
    public void Update() {

        refLinks.getMouseInput().Update();

        if (!AudioAssets.MainMapMusic.isRunning() && !AudioAssets.WinningMusic.isRunning() && CurrentMap.getMapType() == MapType.MainMap) {
            AudioAssets.StopAllMusic();
            AudioAssets.MainMapMusic.start();
            AudioAssets.MainMapMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } else if (!AudioAssets.BattleArenaMusic.isRunning() && !AudioAssets.WinningMusic.isRunning() && !AudioAssets.BossBattleMusic.isRunning() && CurrentMap.getMapType() == MapType.ArenaMap) {
            AudioAssets.StopAllMusic();
            AudioAssets.BattleArenaMusic.start();
            AudioAssets.BattleArenaMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }

            if (refLinks.getKeyInput().getPressedKey() != null && refLinks.getKeyInput().getPressedKey().getKeyCode() == KeyEvent.VK_ENTER) {
                for (Characters character : handler.getCharactersHandler()) {
                    if (character.GetId() != ObjectId.Player && CurrentMap.getMapType() != MapType.ArenaMap && handler.getMainCharacter().getCollisionBounds().getBoundsDown().intersects(character.getCollisionBounds().getBoundsDown())) {
                        if (character.GetId() == ObjectId.Death) {

                            AudioAssets.StopAllMusic();
                            AudioAssets.BossBattleMusic.start();
                            AudioAssets.BossBattleMusic.loop(Clip.LOOP_CONTINUOUSLY);
                        }

                        setLevelMap(MapType.ArenaMap);

                    }
                }

            }

            if (refLinks.getKeyInput().getPressedKey() != null && refLinks.getKeyInput().getPressedKey().getKeyCode() == KeyEvent.VK_ENTER) {
                if(handler.getMainCharacter().getLives() <= 0) {
                    AudioAssets.StopAllMusic();
                    AudioAssets.MainMapMusic.start();
                    AudioAssets.MainMapMusic.loop(Clip.LOOP_CONTINUOUSLY);

                    aliveCharacters[0] = true;
                    setLevelMap(MapType.MainMap);
                }
            }


        if (refLinks.getKeyInput().getPressedKey() != null && refLinks.getKeyInput().getPressedKey().getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if (handler.getMainCharacter().getLives() > 0)
            State.SetState(refLinks.getGame().getPauseState());
        }

        synchronized (cam) {
            cam.Update();
        }

        synchronized (CurrentMap) {
            CurrentMap.Update();
        }

        synchronized (handler) {
            handler.setState(StateType.Update, null);
        }

        CollisionUpdate.mapCollisionUpdate(CurrentMap, handler);

        synchronized (cam) {
            cam.Update();
        }

        synchronized (parallaxEffect) {
            parallaxEffect.Update();
        }


        winLoseBattleSystem.Update();


    }

    @Override
    public void Draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        /// operatie de desenare
        ///////////////////////////

        synchronized (cam) {
            g2d.translate(-cam.transformComponent.getPositionx(), -cam.transformComponent.getPositiony());
            parallaxEffect.Draw(g);
            g2d.translate(cam.transformComponent.getPositionx(), cam.transformComponent.getPositiony());

            winLoseBattleSystem.Draw(g);

            g2d.translate(-cam.transformComponent.getPositionx(), -cam.transformComponent.getPositiony());
        }

        synchronized (CurrentMap) {
            CurrentMap.Draw(g);
        }

        synchronized (handler) {
            handler.setState(StateType.Draw, g);
        }


        synchronized (cam) {
            g2d.translate(cam.transformComponent.getPositionx(), cam.transformComponent.getPositiony());
        }

        ///////////////////////////

    }

    public Camera getCamera() { return cam; }

    public void setLevelMap(MapType mapType)
    {
        synchronized (CurrentMap) {

            switch (mapType) {
                case MainMap:

                    Random rand = new Random();

                    synchronized (handler) {
                        if (handler.getMainCharacter().getLives() <= 0) {
                            handler.getMainCharacter().setLife(100);
                            handler.removeCharacter(handler.getFirstEnemy());

                            handler.getMainCharacter().transformComponent.setPositionx(8 * Tile.TILE_WIDTH);
                            handler.getMainCharacter().transformComponent.setPositiony(9 * Tile.TILE_HEIGHT);
                        }
                        else
                        {
                            if(handler.getMainCharacter() != null && handler.getFirstEnemy() != null) {
                                handler.getMainCharacter().setLife(100);
                                handler.getMainCharacter().transformComponent.setPositionx(8 * Tile.TILE_WIDTH);
                                handler.getMainCharacter().transformComponent.setPositiony(9 * Tile.TILE_HEIGHT);

                                handler.getFirstEnemy().setLife(100);
                                handler.getFirstEnemy().transformComponent.setPositionx(rand.nextInt(60 * Tile.TILE_WIDTH - 110));
                                handler.getFirstEnemy().transformComponent.setPositiony(10 * Tile.TILE_HEIGHT);
                            }
                        }

                        for (int i = 1; i < CHARACTERS; ++i) {
                            if (aliveCharacters[i]) {
                                refLinks.getCharactersFactory().CreateGameObject(refLinks, rand.nextInt(60 * Tile.TILE_WIDTH - 110), 10 * Tile.TILE_HEIGHT, ObjectId.values()[i], handler, null);
                            }
                        }

                        for (int i = 0; i < handler.getCharactersHandler().size(); ++i) {
                            if (!aliveCharacters[handler.getCharactersHandler().get(i).GetId().ordinal()]) {
                                handler.removeCharacter(handler.getCharactersHandler().get(i));
                                --i;
                            }
                        }
                    }
                    CurrentMap = refLinks.getMapFactory().CreateMap(mapType, this.cam, refLinks);
                    refLinks.setMap(CurrentMap);
                    parallaxEffect = new ParallaxEffect(refLinks, cam, CurrentMap.getImages());
                    break;

                case ArenaMap:

                    synchronized (handler) {
                        for (int i = 1; i < handler.getCharactersHandler().size(); ++i) {
                            if (!handler.getCharactersHandler().get(i).getCollisionBounds().getBoundsDown().intersects(handler.getMainCharacter().getCollisionBounds().getBoundsDown())) {
                                handler.removeCharacter(handler.getCharactersHandler().get(i));
                                --i;
                            }
                        }

                        while (handler.getCharactersHandler().size() > 2) {
                            handler.removeCharacter(handler.getCharactersHandler().get(2));
                        }

                        CurrentMap = refLinks.getMapFactory().CreateMap(mapType, this.cam, refLinks);
                        refLinks.setMap(CurrentMap);
                        parallaxEffect = new ParallaxEffect(refLinks, cam, CurrentMap.getImages());

                        handler.getMainCharacter().transformComponent.setPositionx(16 * Tile.TILE_WIDTH);
                        handler.getCharactersHandler().get(handler.getCharactersHandler().size() - 1).transformComponent.setPositionx((43 - 16) * Tile.TILE_WIDTH);
                    }
                    break;
            }
        }
    }

    public static ObjectsHandler getHandler() {
        return handler;
    }

    public static void setAliveCharacters(int i, boolean value) {
        PlayState.aliveCharacters[i] = value;
    }

    public static boolean[] getAliveCharacters() {
        return aliveCharacters;
    }

    public static void setEnemiesKilled(int enemiesKilled) {
        EnemiesKilled = enemiesKilled;
    }

    public static int getEnemiesKilled() {
        return EnemiesKilled;
    }
}
