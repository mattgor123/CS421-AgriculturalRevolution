package rev.model.properties;

/**
 * An entity with a position relative to the continuous map.
 * 
 *
 * 
 */
public interface ContinuouslyPositioned {

    /**
     * The x position of the entity.
     * 
     * @return the x position of the entity.
     */
    public Double getX();

    /**
     * The y position of the entity.
     * 
     * @return the y position of the entity.
     */
    public Double getY();
}