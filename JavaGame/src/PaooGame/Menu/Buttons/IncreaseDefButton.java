package PaooGame.Menu.Buttons;

import PaooGame.Components.Characters.Characters;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.Map.MapType;
import PaooGame.Map.Tiles.Tile;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class IncreaseDefButton extends AbstractButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a creste apararea cu 5 puncte caracterului principal.
 */
public class IncreaseDefButton extends AbstractButton {

    private Characters mainCharacter;

    public IncreaseDefButton(RefLinks refLinks, int x, int y, Characters mainCharacter) {
        super(refLinks, new BufferedImage[] {Assets.IncreaseDefence, Assets.IncreaseDefence, Assets.IncreaseDefence}, "", x, y, null, null);

        this.mainCharacter = mainCharacter;

        this.BUTTONWIDTH = 100;
        this.BUTTONHEIGHT= 100;

        transformComponent.setPositionx(x - BUTTONWIDTH/2f);
        transformComponent.setPositiony(y - BUTTONHEIGHT/2f);

        transformComponent.setWidth(BUTTONWIDTH);
        transformComponent.setHeight(BUTTONHEIGHT);

    }

    @Override
    public void Update() {
        super.Update();

        if(isPressed())
        {
            AudioAssets.StopAllMusic();
            AudioAssets.StopAllDeathSounds();
            AudioAssets.StopAllHitSounds();

            mainCharacter.setLife(100);
            mainCharacter.setDefence(mainCharacter.getDefence() + 5);
            mainCharacter.transformComponent.setPositionx(8 * Tile.TILE_WIDTH);
            mainCharacter.transformComponent.setPositiony(9 * Tile.TILE_HEIGHT);

            refLinks.getGame().getPlayState2().setLevelMap(MapType.MainMap);
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }


}
