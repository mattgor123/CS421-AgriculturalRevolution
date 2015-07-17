package rev.z.clickmodes;

import java.awt.event.MouseEvent;

import rev.model.settlement.core.BaseSettlement;
import rev.model.settlement.core.SettlementManager;
import rev.model.settlement.core.SettlementNameGenerator;
import rev.z.view.core.GameViewController;

/**
 * Clicking on the map will place a new settlement, paid for by the active
 * settlement.
 * 
 *
 * 
 */
public class PlaceSettlementMode implements ClickMode {

    @Override
    public String getName() {
        return "BUILD SETTLEMENT";
    }

    @Override
    public String getDescription() {
        return "Fee: " + SettlementManager.NEW_SETTLEMENT_GRAIN_COST + " GRAIN";
    }

    @Override
    public void execute(GameViewController controller, MouseEvent event) {
        int tileSize = controller.getStatus().getTileSize();
        int x = event.getX() / tileSize;
        int y = event.getY() / tileSize;
        String name = SettlementNameGenerator.getNext();
        controller
                .getModel()
                .getSettlementManager()
                .placePaidSettlement(
                        new BaseSettlement(name, x, y,
                                SettlementManager.NEW_SETTLEMENT_PEOPLE_COST),
                        controller.getStatus().getActiveSettlement());
    }

}