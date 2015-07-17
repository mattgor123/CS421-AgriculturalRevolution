package rev.model.map.core;

import rev.model.attributes.Position;
import rev.model.core.ModelDynamic;

/**
 * A base implementation of a tile.
 * 
 *
 * 
 */
public abstract class BaseTile implements Tile, ModelDynamic {

    /**
     * The tile position.
     * 
     */
    private Position position;

    /**
     * Initializes the tile position.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     */
    public BaseTile(int x, int y) {
        this.position = new Position(x, y);
    }

    @Override
    public Integer getX() {
        return this.position.getX();
    }

    @Override
    public Integer getY() {
        return this.position.getY();
    }
}