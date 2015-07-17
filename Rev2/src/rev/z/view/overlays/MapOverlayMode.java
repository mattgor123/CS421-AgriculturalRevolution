package rev.z.view.overlays;

import java.awt.Graphics;

import rev.model.core.ModelDelegate;
import rev.z.view.core.GameControllerStatus;
import rev.z.view.core.ViewStandard;

/**
 * Defines how to draw elements onto the map.
 * 
 *
 * 
 */
public abstract class MapOverlayMode {

    /**
     * Returns the tile size.
     * 
     * @return the tile size.
     */
    public Integer getTileSize() {
        return ViewStandard.TILE_SIZE;
    }

    /**
     * Updates the overlay.
     * 
     */
    public abstract void update();

    /**
     * Draws the overlay to the specified graphics context.
     * 
     * @param g
     *            the graphics context.
     * @param model
     *            the model.
     * @param status
     *            the game controller status.
     */
    public abstract void drawOverlay(Graphics g, ModelDelegate model,
            GameControllerStatus status);
}