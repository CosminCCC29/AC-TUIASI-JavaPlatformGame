package PaooGame.Map.Tiles;

import PaooGame.Graphics.Assets;

/*! \class GrassTileCornerR extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTileCornerR extends Tile {

    /*! \fn public GrassTileCornerR(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public GrassTileCornerR(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.grassCornerR, id);
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
