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
public class FertilityDisplayMode extends MapDisplayMode {

    @Override
    public void update() {
        // TODO
    }

    @Override
    public Color getColorForTile(Tile tile, ModelDelegate model) {
        if (tile.getDomain() == TileDomain.AQUA) {
            return new Color(0, 0, 0, 120);
        }
        double fertility = Math.min(tile.getAdjustedFertility() * 10, 1000) / 1000.0;
        fertility *= 255;
        if (fertility < 60) {
            fertility *= 0.5;
        }
        return new Color(126, 0, (int) fertility, (int) fertility);
    }

    @Override
    public String getValueForTile(Tile tile, ModelDelegate model) {
        return "" + tile.getAdjustedFertility();
    }

}