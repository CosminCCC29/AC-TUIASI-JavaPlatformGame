package PaooGame.Menu.Buttons;

import PaooGame.Components.Characters.Characters;
import PaooGame.Graphics.Assets;
import PaooGame.Audio.AudioAssets;
import PaooGame.Map.MapType;
import PaooGame.Map.Tiles.Tile;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class IncreaseASButton extends AbstractButton
    \brief Clasa concreta pentru implementarea unui buton.

    Functionalitatea butonului este aceea de a creste viteza de atac cu 5 puncte caracterului principal.
 */
public class IncreaseASButton extends AbstractButton {

    private Characters mainCharacter;

    public IncreaseASButton(RefLinks refLinks, int x, int y, Characters mainCharacter) {
        super(refLinks, new BufferedImage[] {Assets.IncreaseAttackSpeed, Assets.IncreaseAttackSpeed, Assets.IncreaseAttackSpeed}, "", x, y, null, null);

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

        if(mainCharacter.getAttackSpeed() < 50) {
            if (isPressed()) {
                AudioAssets.StopAllMusic();
                AudioAssets.StopAllDeathSounds();
                AudioAssets.StopAllHitSounds();

                mainCharacter.setLife(100);
                mainCharacter.setAttackSpeed(mainCharacter.getAttackSpeed() + 5);
                mainCharacter.transformComponent.setPositionx(8 * Tile.TILE_WIDTH);
                mainCharacter.transformComponent.setPositiony(9 * Tile.TILE_HEIGHT);

                refLinks.getGame().getPlayState2().setLevelMap(MapType.MainMap);
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);

        if(mainCharacter.getAttackSpeed() >= 50)
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            g.drawString("MAX!", (int) (transformComponent.getPositionx() + transformComponent.getWidth()/2 - "MAX!".length()/2 - 36.5), (int)(transformComponent.getPositiony() - 11.5));
        }
    }


}
