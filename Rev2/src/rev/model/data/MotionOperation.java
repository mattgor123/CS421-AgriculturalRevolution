package rev.model.data;

import rev.model.map.core.Tile;

/**
 * A motion operation.
 * 
 *
 * 
 */
public interface MotionOperation {

    /**
     * The origin tile.
     * 
     * @return the origin tile.
     */
    public Tile getOrigin();

    /**
     * The destination tile.
     * 
     * @return the destination tile.
     */
    public Tile getDestination();

    /**
     * Returns the direction of the motion.
     * 
     * @return the direction.
     */
    public Motion getDirection();

    /**
     * Returns the number of motion points that the action costs.
     * 
     * @return the number of points.
     */
    public Integer getMotionPoints();
}