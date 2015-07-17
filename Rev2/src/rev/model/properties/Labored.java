package rev.model.properties;

/**
 * An entity that requires labor.
 * 
 *
 * 
 */
public interface Labored {

    /**
     * Returns number of people requested by the entity.
     * 
     * @return the number of people requested.
     */
    public Integer getLaborDemand();
}