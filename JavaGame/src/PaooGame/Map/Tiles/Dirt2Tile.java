package PaooGame.Map.Tiles;

import PaooGame.Graphics.Assets;

/*! \class Dirt2Tile extends Tile
    \brief Abstractizeaza notiunea de dala de tip pietris.
 */
public class Dirt2Tile extends Tile
{
    /*! \fn public Dirt2Tile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Dirt2Tile(int id)
    {
        super(Assets.dirt2, id);
    }

    @Override
    public boolean IsSolid()
    {
        return false;
    }

}
