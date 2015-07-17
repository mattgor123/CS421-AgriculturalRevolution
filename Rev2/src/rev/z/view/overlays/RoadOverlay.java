package rev.z.view.overlays;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;
import rev.z.view.core.GameControllerStatus;

/**
 * Draws roads onto the map.
 * 
 *
 * 
 */
public class RoadOverlay extends MapOverlayMode {

    /**
     * Draws a route between two tiles.
     * 
     * @param g
     *            the graphics context.
     * @param origin
     *            the origin tile.
     * @param destination
     *            the destination tile.
     * @param color
     *            the tile color.
     */
    private void drawRouteBetween(Graphics g, Tile origin, Tile destination,
            Color color) {
        g.setColor(color);
        int halfTile = this.getTileSize() / 2;
        int originX = origin.getX() * this.getTileSize() + halfTile;
        int originY = origin.getY() * this.getTileSize() + halfTile;
        int destX = destination.getX() * this.getTileSize() + halfTile;
        int destY = destination.getY() * this.getTileSize() + halfTile;
        g.drawLine(originX, originY, destX, destY);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawOverlay(Graphics g, ModelDelegate model,
            GameControllerStatus status) {
        g.setColor(Color.YELLOW);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        for (int i = 1; i < model.getTileMap().getWidth() - 1; i++) {
            for (int j = 1; j < model.getTileMap().getHeight() - 1; j++) {
                Tile tile = model.getTileMap().getTile(i, j);
                Tile east = model.getTileMap().getTile(i + 1, j);
                Tile north = model.getTileMap().getTile(i, j + 1);
                if (model.getRoadNetworkManager().usableRoadExistsBetween(tile,
                        east)) {
                    this.drawRouteBetween(g, tile, east, Color.YELLOW);
                }
                if (model.getRoadNetworkManager().usableRoadExistsBetween(tile,
                        north)) {
                    this.drawRouteBetween(g, tile, north, Color.YELLOW);
                }
            }
        }
    }
}