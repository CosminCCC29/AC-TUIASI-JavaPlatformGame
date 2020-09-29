package PaooGame.Map.LevelMaps;

import PaooGame.Camera.Camera;
import PaooGame.Map.Map;
import PaooGame.Map.MapType;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class EmptyMap extends Map
    \brief Implementeaza o harta goala, fara componente.
 */
public class EmptyMap extends Map {

    /*! \fn public EmptyMap()
        \brief Constructor de initializare al clasei EmptyMap.
     */
    public EmptyMap() {
        super(null, null, null, 0, 0);
    }

    @Override
    public int getMapWidth() {
        return 0;
    }

    @Override
    public int getMapHeight() {
        return 0;
    }

    @Override
    public void Update() {
        super.Update();
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }

    @Override
    public BufferedImage[] getImages() {
        return new BufferedImage[0];
    }

    @Override
    public MapType getMapType() {
        return null;
    }
}
