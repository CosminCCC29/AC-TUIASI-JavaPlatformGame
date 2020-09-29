package PaooGame.Map.Tiles;

import PaooGame.Graphics.Assets;

/*! \class GrassTileCornerL extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTileCornerL extends Tile
{
    /*! \fn public GrassTileCornerL(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTileCornerL(int id)
    {
        super(Assets.grassCornerL, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
