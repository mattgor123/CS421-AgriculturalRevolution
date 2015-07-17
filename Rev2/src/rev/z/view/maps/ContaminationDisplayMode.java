package rev.z.view.maps;

import java.awt.Color;

import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;
import rev.model.map.core.TileDomain;

/**
 * Displays the fertility of the map.
 * 
 *
 * 
 */
public class ContaminationDisplayMode extends MapDisplayMode {

    /**
     * This update counter causes the active settlement to glow.
     * 
     */
    private int counter = 150, direction = 5;

    @Override
    public void update() {
        this.counter += this.direction;
        if (this.counter > 255 && this.direction > 0) {
            this.counter = 255;
            this.direction = -5;
        } else if (this.counter < 150 && this.direction < 0) {
            this.counter = 150;
            this.direction = 5;
        }
    }

    @Override
    public Color getColorForTile(Tile tile, ModelDelegate model) {
        if (tile.getDomain() == TileDomain.AQUA) {
            return new Color(0, 0, 0, 120);
        }
        double con = Math.min(tile.getContamination() * 10, 1000) / 1000.0;
        con *= 255;
        if (con < 60) {
            con *= 0.5;
        }
        return new Color((int) con, 0, 0, (int) con);
    }

    @Override
    public String getValueForTile(Tile tile, ModelDelegate model) {
        return "" + tile.getContamination();
    }

}