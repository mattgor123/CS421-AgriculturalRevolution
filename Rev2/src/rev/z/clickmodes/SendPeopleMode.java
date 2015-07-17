package rev.z.clickmodes;

import java.awt.event.MouseEvent;

import rev.model.settlement.core.Settlement;
import rev.model.settlement.core.SettlementManager;
import rev.utility.Coordinate;
import rev.z.view.core.GameViewController;
import rev.z.view.effects.TransferEffect;
import rev.z.view.effects.WarningEffect;

/**
 * Clicking on the map will transfer 500 grain from the active settlement to the
 * clicked-on settlement.
 * 
 *
 * 
 */
public class SendPeopleMode implements ClickMode {

    /**
     * The transfer quantity.
     * 
     */
    public static final int TRANSFER_QUANTITY = 500;

    @Override
    public String getName() {
        return "SEND " + TRANSFER_QUANTITY + " PEOPLE";
    }

    @Override
    public String getDescription() {
        return "Fee: " + SettlementManager.PEOPLE_TRANSFER_FEE + " GRAIN";
    }

    @Override
    public void execute(GameViewController controller, MouseEvent event) {
        int tileSize = controller.getStatus().getTileSize();
        int x = event.getX() / tileSize;
        int y = event.getY() / tileSize;
        Settlement giftee = controller.getModel().getSettlementManager()
                .getSettlement(x, y);
        if (giftee == null) {
            return;
        }
        Settlement gifter = controller.getStatus().getActiveSettlement();
        controller.getModel().getSettlementManager()
                .transferPeople(gifter, giftee, TRANSFER_QUANTITY);
        controller.addEffect(new TransferEffect(gifter, giftee));
        controller.addEffect(new WarningEffect(new Coordinate<Integer>(event
                .getX(), event.getY())));
    }
}