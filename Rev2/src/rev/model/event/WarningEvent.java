package rev.model.event;

import rev.model.map.core.Tile;

/**
 * A model event with a tile location.
 * 
 *
 * 
 */
public class WarningEvent extends BasicEvent {

    /**
     * The location of the warning.
     * 
     */
    private Tile tile;

    /**
     * Initializes the warning event.
     * 
     * @param id
     *            the event identifier.
     * @param message
     *            the event message.
     * @param tile
     *            the event location.
     */
    public WarningEvent(String id, String message, Tile tile) {
        super(id, message);
        this.tile = tile;
    }

    /**
     * Returns the location of the warning.
     * 
     * @return the location.
     */
    public Tile getTile() {
        return this.tile;
    }
}