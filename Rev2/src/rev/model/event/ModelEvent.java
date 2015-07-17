package rev.model.event;

/**
 * A model event.
 * 
 *
 * 
 */
public interface ModelEvent {

    /**
     * Returns the event id.
     * 
     * @return the event id.
     */
    public String getID();

    /**
     * Returns the event message.
     * 
     * @return the event message.
     */
    public String getMessage();
}