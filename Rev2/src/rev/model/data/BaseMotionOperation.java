package rev.model.data;

import rev.model.map.core.Tile;

/**
 * An implementation of the motion operation interface.
 * 
 *
 * 
 */
public class BaseMotionOperation implements MotionOperation {

    /**
     * The motion direction.
     * 
     */
    private Motion motion;

    /**
     * The origin tile.
     * 
     */
    private Tile origin;

    /**
     * The destination tile.
     * 
     */
    private Tile destination;

    /**
     * Initializes the operation.
     * 
     * @param origin
     *            the origin tile.
     * @param destination
     *            the destination tile.
     */
    public BaseMotionOperation(Tile origin, Tile destination) {
        this.origin = origin;
        this.destination = destination;
        int dx = this.destination.getX() - this.origin.getX();
        int dy = this.destination.getY() - this.origin.getY();
        if (dx == 1 && dy == 0) {
            this.motion = Motion.EAST;
        } else if (dx == -1 && dy == 0) {
            this.motion = Motion.WEST;
        } else if (dx == 0 && dy == 1) {
            this.motion = Motion.NORTH;
        } else if (dx == 0 && dy == -1) {
            this.motion = Motion.SOUTH;
        }
    }

    @Override
    public Tile getOrigin() {
        return this.origin;
    }

    @Override
    public Tile getDestination() {
        return this.destination;
    }

    @Override
    public Motion getDirection() {
        return this.motion;
    }

    @Override
    public Integer getMotionPoints() {
        return 0;
    }

}