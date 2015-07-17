package rev.model.data;

/**
 * An exception thrown when a tile is occupied.
 * 
 *
 * 
 */
public class TileOccupiedException extends RuntimeException {

    /**
     * The serial version unique identifier.
     * 
     */
    private static final long serialVersionUID = -8961979028166288765L;

    public TileOccupiedException() {
        super();
    }

    public TileOccupiedException(String message) {
        super(message);
    }
}