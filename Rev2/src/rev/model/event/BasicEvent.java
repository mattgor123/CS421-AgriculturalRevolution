package rev.model.event;

/**
 * A basic model event, with a message.
 * 
 *
 * 
 */
public class BasicEvent implements ModelEvent {

    private final String id;

    private final String message;

    public BasicEvent(String id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}