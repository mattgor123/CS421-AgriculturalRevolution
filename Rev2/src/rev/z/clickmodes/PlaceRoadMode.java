package rev.z.clickmodes;

import java.awt.event.MouseEvent;

import rev.model.map.core.Tile;
import rev.model.settlement.core.Settlement;
import rev.z.view.core.GameViewController;

/**
 * A mode to place roads, purchased from a source city.
 * 
 *
 * 
 */
public class PlaceRoadMode implements ClickMode {

    @Override
    public void execute(GameViewController controller, MouseEvent event) {
        int tileSize = controller.getStatus().getTileSize();
        int x = event.getX() / tileSize;
        int y = event.getY() / tileSize;
        Settlement settlement = controller.getStatus().getActiveSettlement();
        Tile tile = controller.getModel().getTileMap().getTile(x, y);
        controller.getModel().getRoadNetworkManager().placePaidRoad(tile, settlement);
    }

    @Override
    public String getName() {
        return "PLACE ROAD";
    }

    @Override
    public String getDescription() {
        return "Fee: 100 GRAIN";
    }
}