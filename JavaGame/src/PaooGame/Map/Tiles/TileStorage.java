package PaooGame.Map.Tiles;

/*! \class TileStorage
    \brief Implementeaza notiunea de depozit al referintelor dalelor.
 */
public class TileStorage
{
    private static final int NO_TILES   = 32;
    public Tile[] tiles   = new Tile[NO_TILES];
    static Tile grassTile        = new GrassTile(1);
    static Tile grassCornerL     = new GrassTileCornerL(2);
    static Tile grassCornerR     = new GrassTileCornerR(3);
    static Tile dirtTile         = new DirtTile(4);
    static Tile grass2Tile       = new Grass2Tile(5);
    static Tile dirt2Tile        = new Dirt2Tile(6);

    /*! \fn TileStorage()
        \brief Constructor de initializare al clasei TileStorage.
     */
    public TileStorage()
    {
        tiles[0] = null;
        tiles[1] = grassTile;
        tiles[2] = grassCornerL;
        tiles[3] = grassCornerR;
        tiles[4] = dirtTile;
        tiles[5] = grass2Tile;
        tiles[6] = dirt2Tile;
    }
}
