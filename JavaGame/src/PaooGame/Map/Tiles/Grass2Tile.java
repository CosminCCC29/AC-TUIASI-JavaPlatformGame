package PaooGame.Map.Tiles;

import PaooGame.Graphics.Assets;

/*! \class Grass2Tile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class Grass2Tile extends Tile
{
    /*! \fn public Grass2Tile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Grass2Tile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.grass2, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
