package rev.z.view.maps;

import java.awt.Color;

import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;

/**
 * Displays the map tile outlines with no data.
 * 
 *
 * 
 */
public class BlankMapMode extends MapDisplayMode {

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public Color getColorForTile(Tile tile, ModelDelegate model) {
        return Color.BLACK;
    }

    @Override
    public String getValueForTile(Tile tile, ModelDelegate model) {
        return "";
    }
}