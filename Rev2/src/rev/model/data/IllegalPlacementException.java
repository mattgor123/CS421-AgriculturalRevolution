package rev.model.data;

/**
 * An exception thrown on account of a tile domain inconsistency.
 * 
 *
 * 
 */
public class IllegalPlacementException extends RuntimeException {

    /**
     * The serial version unique identifier.
     * 
     */
    private static final long serialVersionUID = -8961979028166288765L;

    public IllegalPlacementException() {
        super();
    }

    public IllegalPlacementException(String message) {
        super(message);
    }
}