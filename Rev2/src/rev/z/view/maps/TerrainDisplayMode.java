package rev.z.view.maps;

import java.awt.Color;

import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;
import rev.model.map.core.TileDomain;

/**
 * Displays the terrain of the map.
 * 
 *
 * 
 */
public class TerrainDisplayMode extends MapDisplayMode {

    @Override
    public void update() {
        // TODO
    }

    @Override
    public Color getColorForTile(Tile tile, ModelDelegate model) {
        if (tile.getDomain() == TileDomain.AQUA) {
            return new Color(0, 0, 0, 120);
        }
        double elevation = Math.min(tile.getElevation() * 10, 255) / 255.0;
        elevation *= 255;
        elevation = 255 - elevation;
        return new Color(0, 126, 0, (int) elevation);
    }

    @Override
    public String getValueForTile(Tile tile, ModelDelegate model) {
        return "" + tile.getElevation();
    }
}