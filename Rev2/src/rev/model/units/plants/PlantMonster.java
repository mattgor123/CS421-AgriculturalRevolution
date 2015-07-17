package rev.model.units.plants;

import rev.model.units.core.Unit;

/**
 * A plant monster that consumes settlements.
 * 
 *
 * 
 */
public interface PlantMonster extends Unit {

    /**
     * Returns the health of the monster.
     * 
     * @return the health of the monster.
     */
    public Integer getHealth();
}