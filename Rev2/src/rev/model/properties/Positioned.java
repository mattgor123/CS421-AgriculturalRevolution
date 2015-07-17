package rev.model.properties;

/**
 * An entity with a position relative to the tile map.
 * 
 *
 * 
 */
public interface Positioned {

    /**
     * Returns the x index of the entity.
     * 
     * @return the x index.
     */
    public Integer getX();

    /**
     * Returns the y index of the entity.
     * 
     * @return the y index.
     */
    public Integer getY();
}