package rev.z.clickmodes;

import java.awt.event.MouseEvent;

import rev.model.settlement.core.Settlement;
import rev.utility.Coordinate;
import rev.z.view.core.GameViewController;
import rev.z.view.effects.WarningEffect;

/**
 * Changes the active settlement.
 * 
 *
 * 
 */
public class SetActiveSettlementMode implements ClickMode {

    @Override
    public void execute(GameViewController controller, MouseEvent event) {
        int tileSize = controller.getStatus().getTileSize();
        int x = event.getX() / tileSize;
        int y = event.getY() / tileSize;
        Settlement settlement = controller.getModel().getSettlementManager()
                .getSettlement(x, y);
        if (settlement != null) {
            controller.getStatus().setActiveSettlement(settlement);
            controller.addEffect(new WarningEffect(
                    new Coordinate<Integer>(event.getX(), event.getY())));
        }
    }

    @Override
    public String getName() {
        return "SELECT A SETTLEMENT";
    }

    @Override
    public String getDescription() {
        return "Tap on a settlement...";
    }
}