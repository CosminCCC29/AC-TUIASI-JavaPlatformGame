package PaooGame.Menu.Buttons;
import PaooGame.Database.Database;
import PaooGame.Map.MapType;
import PaooGame.RefLinks;
import PaooGame.States.State;


import java.awt.*;

/*! \class MainMenuButton extends MenuButton
    \brief Clasa concreta pentru implementarea unui buton.
    
    Functionalitatea butonului este aceea de a revenii la meniul principal.
 */
public class MainMenuButton extends MenuButton
{
    public MainMenuButton(RefLinks refLinks, int x, int y) {
        super(refLinks,"Main Menu", x, y);
    }

    @Override
    public void Update() {
        super.Update();

        if(isReleased())
        {
            if(refLinks.getMap().getMapType() != MapType.MainMap) {
                refLinks.getGame().getPlayState2().setLevelMap(MapType.MainMap);
            }

            State.SetState(refLinks.getGame().getMenuState());
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }
}
