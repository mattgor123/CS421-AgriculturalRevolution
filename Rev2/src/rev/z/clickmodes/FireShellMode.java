package rev.z.clickmodes;

import java.awt.event.MouseEvent;

import rev.model.map.core.TileDomain;
import rev.utility.Coordinate;
import rev.z.view.core.GameViewController;

/**
 * Clicking in this mode will fire a cobalt shell from the active settlement.
 * 
 *
 * 
 */
public class FireShellMode implements ClickMode {

    @Override
    public String getName() {
        return "FIRE COBALT SHELL";
    }

    @Override
    public String getDescription() {
        return "Fee: 100";
    }

    @Override
    public void execute(GameViewController controller, MouseEvent event) {
        int tileSize = controller.getStatus().getTileSize();
        int x = event.getX() / tileSize;
        int y = event.getY() / tileSize;
        /*
         * Do not fire if a water tile.
         */
        if (controller.getModel().getTileMap().getTile(x, y).getDomain() == TileDomain.AQUA) {
            return;
        }
        Coordinate<Double> target = new Coordinate<Double>(
                (double) event.getX() / tileSize, (double) event.getY()
                        / tileSize);
        controller.getStatus().getActiveSettlement().getDefenses()
                .fireCobaltShell(target, controller.getModel());
    }

}