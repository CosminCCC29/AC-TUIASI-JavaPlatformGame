package PaooGame.Map.Tiles;

import PaooGame.Graphics.Assets;

/*! \class DirtTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip pietris.
 */
public class DirtTile extends Tile
{
    /*! \fn public DirtTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DirtTile(int id)
    {
        super(Assets.dirt, id);
    }

    @Override
    public boolean IsSolid()
    {
        return false;
    }

}
