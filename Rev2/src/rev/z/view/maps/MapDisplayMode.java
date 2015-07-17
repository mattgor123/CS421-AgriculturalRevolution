package rev.z.view.maps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;
import rev.model.map.core.TileDomain;
import rev.z.view.core.MapColor;
import rev.z.view.core.ViewStandard;

/**
 * Defines how to draw the map.
 * 
 *
 * 
 */
public abstract class MapDisplayMode {

    /**
     * Whether or not the map view displays tile values.
     * 
     */
    private Boolean showValue = false;

    private int tileSize = ViewStandard.TILE_SIZE;

    public void setShowValue(boolean showValue) {
        this.showValue = showValue;
    }

    /**
     * Updates the display.
     * 
     */
    public abstract void update();

    /**
     * Draws the map to the specified graphics context.
     * 
     * @param g
     *            the graphics context.
     * @param model
     *            the model.
     */
    public void drawMap(Graphics g, ModelDelegate model) {
        for (Tile tile : model.getTileMap()) {
            g.setColor(this.getColorForTile(tile, model));
            int x = tile.getX() * this.tileSize;
            int y = tile.getY() * this.tileSize;
            g.fillRect(x, y, this.tileSize, this.tileSize);
        }
        for (Tile tile : model.getTileMap()) {
            if (tile.getDomain() == TileDomain.TERRA) {
                int x = tile.getX() * this.tileSize;
                int y = tile.getY() * this.tileSize;
                g.setColor(MapColor.LAND_BORDER_COLOR);
                g.drawRect(x, y, this.tileSize, this.tileSize);
                if (!this.showValue) {
                    g.setFont(new Font("Helvetica", Font.PLAIN, 4));
                    g.setColor(new Color(255, 255, 255, 120));
                    g.drawString(this.getValueForTile(tile, model), x + 2,
                            y + 8);
                }
            }
        }
    }

    /**
     * Returns the color of a particular tile.
     * 
     * @param tile
     *            the tile.
     * @param model
     *            the model.
     * @return the tile color.
     */
    public abstract Color getColorForTile(Tile tile, ModelDelegate model);

    /**
     * Returns a value to display in the specified tile.
     * 
     * @param tile
     *            the tile.
     * @param model
     *            the model.
     * @return the value to display.
     */
    public abstract String getValueForTile(Tile tile, ModelDelegate model);
}