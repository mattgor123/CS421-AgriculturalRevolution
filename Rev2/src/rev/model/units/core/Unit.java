package rev.model.units.core;

import rev.model.core.ModelDynamic;
import rev.model.ordnance.AffectedByCobaltOrdnance;
import rev.model.ordnance.AffectedByExplosiveOrdnance;
import rev.model.properties.Movable;

/**
 * A behaviored entity.
 * 
 *
 * 
 */
public interface Unit extends ModelDynamic, Movable, AffectedByCobaltOrdnance, AffectedByExplosiveOrdnance {

    /**
     * Sets the behavior of the unit.
     * 
     * @param behavior
     *            the unit behavior.
     */
    public void setBehavior(UnitBehavior behavior);

    /**
     * Returns the speed of the unit.
     * 
     * @return the speed of the unit.
     */
    public Integer getSpeed();

    /**
     * Returns the number of movement points for the unit.
     * 
     * @return the number of movement points.
     */
    public Integer getMovementPoints();

    /**
     * Sets number of movement points for the unit.
     * 
     * @param points
     *            the number of movement points.
     */
    public void setMovementPoints(Integer points);    
}