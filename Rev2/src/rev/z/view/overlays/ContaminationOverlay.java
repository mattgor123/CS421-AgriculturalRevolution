package rev.z.view.overlays;

import java.awt.Color;
import java.awt.Graphics;

import rev.model.core.ModelDelegate;
import rev.model.map.core.LandTile;
import rev.model.map.core.Tile;
import rev.model.map.core.TileDomain;
import rev.z.view.core.GameControllerStatus;

/**
 * Draws contamination onto the map.
 * 
 *
 * 
 */
public class ContaminationOverlay extends MapOverlayMode {

    /**
     * This update counter causes the active settlement to glow.
     * 
     */
    private int counter = 75, direction = 5;

    @Override
    public void update() {
        this.counter += this.direction;
        if (this.counter > 50 && this.direction > 0) {
            this.counter = 50;
            this.direction = -1;
        } else if (this.counter < 0 && this.direction < 0) {
            this.counter = 0;
            this.direction = 1;
        }
    }

    @Override
    public void drawOverlay(Graphics g, ModelDelegate model,
            GameControllerStatus status) {
        for (Tile tile : model.getTileMap()) {
            if (tile.getDomain() == TileDomain.TERRA) {
                LandTile land = (LandTile) tile;
                if (land.getContamination() > 0) {
                    g.setColor(new Color(200, 200, 0, counter * 2));
                    if (land.getContamination() > 20) {
                        g.setColor(new Color(180, 80, 0, counter * 2));
                    }
                    if (land.getContamination() > 50) {
                        g.setColor(new Color(255, 0, 0, counter * 2));
                    }
                    g.fillRect(tile.getX() * this.getTileSize() + 1,
                            tile.getY() * this.getTileSize() + 1,
                            this.getTileSize() - 1, this.getTileSize() - 1);
                }
            }
        }
    }
}