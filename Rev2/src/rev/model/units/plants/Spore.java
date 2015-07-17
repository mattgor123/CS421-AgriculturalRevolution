package rev.model.units.plants;

import rev.model.core.ModelDynamic;
import rev.model.properties.Positioned;

/**
 * A spore that can contaminate land.
 * 
 *
 * 
 */
public interface Spore extends ModelDynamic, Positioned {

    /**
     * Returns the potency of the spore.
     * 
     * @return the potency.
     */
    public Integer getPotency();

    /**
     * Destroys the spore.
     * 
     */
    public void eliminate();
}