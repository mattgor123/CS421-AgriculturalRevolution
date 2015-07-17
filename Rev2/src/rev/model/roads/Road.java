package rev.model.roads;

import rev.model.properties.Positioned;

/**
 * A road.
 * 
 *
 * 
 */
public interface Road extends Positioned {

    /**
     * Returns whether the road is usable.
     * 
     * @return true if usable, false otherwise.
     */
    public Boolean isUsable();
}