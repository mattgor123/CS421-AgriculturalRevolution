package rev.model.event;

/**
 * An object that respond to model events.
 * 
 *
 * 
 */
public interface ModelListener {

    /**
     * Responds to a model event.
     * 
     * @param event
     *            the event.
     */
    public void respondToEvent(ModelEvent event);
}