package rev.z.view.overlays;

import java.awt.Color;
import java.awt.Graphics;

import rev.model.core.ModelDelegate;
import rev.model.ordnance.CobaltOrdnanceEffect;
import rev.z.view.core.GameControllerStatus;

/**
 * Draws ordnance effects to the map.
 * 
 *
 * 
 */
public class OrdnanceEffectOverlay extends MapOverlayMode {

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawOverlay(Graphics g, ModelDelegate model,
            GameControllerStatus status) {
        for (CobaltOrdnanceEffect cobalt : model.getOrdnanceManager()
                .getCobaltOrdnanceEffects()) {
            g.setColor(new Color(200, 200, 0, 255));
            g.fillRect(cobalt.getX() * this.getTileSize() + 1, cobalt.getY()
                    * this.getTileSize() + 1, this.getTileSize() - 1,
                    this.getTileSize() - 1);
        }
    }

}