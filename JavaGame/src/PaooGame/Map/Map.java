package PaooGame.Map;

import PaooGame.Camera.Camera;
import PaooGame.Components.Component;
import PaooGame.Graph.Graph;
import PaooGame.Graph.Node;
import PaooGame.Map.Tiles.Tile;
import PaooGame.Map.Tiles.TileStorage;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/*! \class Map implements Component
    \brief Implementeaza harta concreta de batalie.
 */
public abstract class Map implements Component
{
    private int Rows;
    private int Columns;
    protected MapType mapType;
    protected Graph graph;

    private Camera cam;

    protected int Lvl;
    protected int[][] Matrix;
    protected RefLinks refLinks;
    protected static TileStorage storage = new TileStorage();
    protected BufferedImage[] Images;

    private LinkedList<Rectangle> Bounds;

    /*! \fn public ArenaMap(MapType mapType, Camera cam, RefLinks refLinks)
        \brief Constructor de initializare al clasei ArenaMap.

        \param mapType Tipul hartii.
        \param cam Referinta catre Camera jocului.
        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param Rows Numarul de randuri de tile-uri ale hartii.
        \param Columns Numarul de coloane de tile-uri ale hartii.
     */
    public Map(RefLinks refLinks, MapType mapType, Camera cam, int Rows, int Columns)
    {
        this.cam = cam;
        this.mapType = mapType;
        this.Rows = Rows;
        this.Columns = Columns;

        this.refLinks = refLinks;

        Bounds = new LinkedList<>();

        graph = new Graph();

    }

    /*! \fn protected void InitMapComponents()
        \brief Metoda de initializare al componentelor hartii.
     */
    protected void InitMapComponents()
    {
        for (int j = 0; j < Rows; ++j) {
            for (int i = 0; i < Columns; ++i) {
                if(Matrix[j][i] != 0 && Matrix[j][i] != 8 && storage.tiles[Matrix[j][i]].IsSolid())
                {
                    this.addBounds(new Rectangle(i * Tile.TILE_WIDTH, j * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT));
                }
                else if(Matrix[j][i] == 0)
                {
                    graph.addNode(i,j,500);
                }
                else if(Matrix[j][i] == 8) {
                    graph.addNode(i, j, 0);
                }
            }
        }

        for(int j = 0; j < Rows; ++j)
        {
            for(int i = 0; i < Columns; ++i)
            {
                if(Matrix[j][i] == 0)
                    graph.addNeighbours(new Node(i,j,500));

                if(Matrix[j][i] == 8)
                    graph.addNeighbours(new Node(i,j,0));
            }
        }
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia componentelor hartii.
     */
    @Override
    public void Update()
    {
        Bounds.forEach(it -> it.setBounds(it.x,it.y,it.width,it.height));
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza elementele componente hartii.
     */
    @Override
    public void Draw(Graphics g)
    {
        for (int j = (int) (cam.transformComponent.getPositiony()/Tile.TILE_HEIGHT); j < (cam.transformComponent.getPositiony() + cam.transformComponent.getHeight())/Tile.TILE_HEIGHT; ++j) {
            for (int i = (int) (cam.transformComponent.getPositionx()/Tile.TILE_WIDTH); i < (cam.transformComponent.getPositionx() + cam.transformComponent.getWidth())/Tile.TILE_WIDTH; ++i) {
                if(this.getMapType() == MapType.ArenaMap && i >= this.getColumns()) i = this.getColumns() - 1;
                if(this.getMapType() == MapType.MainMap && i >= this.getColumns()) i = this.getColumns() - 1;

                if(this.getMapType() == MapType.ArenaMap && i < 0) i = 0;
                if(this.getMapType() == MapType.MainMap && i < 0) i = 0;

                if (Matrix[j][i] != 0 && Matrix[j][i] != 8) {
                    storage.tiles[Matrix[j][i]].Draw(g, i * Tile.TILE_WIDTH, j * Tile.TILE_HEIGHT);
                }
            }
        }
    }

    public LinkedList<Rectangle> getBounds() { return Bounds; }
    public void addBounds(Rectangle rect) {this.Bounds.add(rect);}

    public abstract int getMapWidth();
    public abstract int getMapHeight();

    public Graph getGraph() {
        return graph;
    }

    public abstract BufferedImage[] getImages();

    public abstract MapType getMapType();

    public int getRows() {
        return Rows;
    }

    public int getColumns() {
        return Columns;
    }

    public Camera getCam() {
        return cam;
    }
}
