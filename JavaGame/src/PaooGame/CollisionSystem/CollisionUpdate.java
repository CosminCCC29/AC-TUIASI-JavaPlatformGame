package PaooGame.CollisionSystem;
import PaooGame.Components.Characters.Characters;
import PaooGame.Components.Props.Props;
import PaooGame.Map.Map;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;

import java.awt.*;

/*! \class CollisionUpdate
    \brief Implementeaza notiunea de coliziune cu harta.
 */
public class CollisionUpdate {

    /*! \fn public static void mapCollisionUpdate(Map map, ObjectsHandler handler)
        \brief Metoda statica de realizare a coliziunilor obiectelor de tip GameObject cu harta.

        Metoda parcurge fiecare obiect din tabloul de Caractere si Prop-uri din handler si verifica coliziunea cu fiecare tile solid al hartii.

        \param map Referinta catre harta curenta.
        \param handler Referinta catre handler-ul de obiecte.
     */
    public static void mapCollisionUpdate(Map map, ObjectsHandler handler) {
        for (Characters chr : handler.getCharactersHandler()) {
            for (Rectangle rect : map.getBounds()) {
                if (chr.getCollisionBounds().Down.intersects(rect)) {
                    chr.transformComponent.setPositiony(rect.y - chr.transformComponent.getHeight());
                    chr.transformComponent.setVelocityy(0);
                    chr.getCollisionBounds().Update();
                    chr.transformComponent.setFalling(false);
                    chr.transformComponent.setJumping(false);
                    chr.transformComponent.setJumping2(false);
                } else {
                    chr.transformComponent.setFalling(true);
                }

                if (chr.getCollisionBounds().Top.intersects(rect)) {
                    chr.transformComponent.setPositiony(rect.y + rect.height);
                    chr.transformComponent.setVelocityy(0);
                    chr.getCollisionBounds().Update();
                }
                if (chr.getCollisionBounds().Right.intersects(rect)) {
                    chr.transformComponent.setPositionx(rect.x - chr.transformComponent.getWidth() * 0.73f);
                    chr.getCollisionBounds().Update();
                }

                if (chr.getCollisionBounds().Left.intersects(rect)) {
                    chr.transformComponent.setPositionx(rect.x + rect.width - chr.transformComponent.getWidth() * 0.242423f);
                    chr.getCollisionBounds().Update();
                }

            }

            if (chr.getCollisionBounds().Left.x < 0) {
                chr.transformComponent.setPositionx(- chr.transformComponent.getWidth() * 0.185f);
                chr.getCollisionBounds().Update();
            }

            if (chr.getCollisionBounds().Right.x + chr.getCollisionBounds().Right.width > map.getMapWidth()) {
                chr.transformComponent.setPositionx(map.getMapWidth() - chr.transformComponent.getWidth() * 0.79f);
                chr.getCollisionBounds().Update();
            }

        }

        for (Props prop : handler.getPropsHandler()) {
            for (Rectangle rect : map.getBounds()) {
                if (prop.getCollisionBounds() != null && prop.getCollisionBounds().intersects(rect)) {
                    prop.transformComponent.setPositiony(rect.y - prop.transformComponent.getHeight());
                    prop.transformComponent.setVelocityy(0);
                    prop.Update();
                    prop.transformComponent.setFalling(false);
                    prop.transformComponent.setJumping(false);
                    prop.transformComponent.setJumping2(false);
                } else {
                    prop.transformComponent.setFalling(true);
                }
            }
        }
    }
}