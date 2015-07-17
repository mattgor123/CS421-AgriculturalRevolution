package rev.model.ordnance;

import rev.model.core.ModelDynamic;
import rev.model.properties.ContinuouslyPositioned;
import rev.utility.Coordinate;

/**
 * Carries an ordnance effect to a target.
 * 
 *
 * 
 */
public interface Shell extends ModelDynamic, ContinuouslyPositioned {

    /**
     * Returns the ordnance of the shell.
     * 
     * @return the ordnance of the shell
     */
    public OrdnanceEffect getOrdnanceEffect();

    /**
     * Returns the velocity of the shell.
     * 
     * @return the velocity of the shell.
     */
    public Coordinate<Double> getVelocity();

    /**
     * Returns the origin of the shell.
     * 
     * @return the origin of the shell.
     */
    public Coordinate<Double> getOrigin();

    /**
     * Returns the target of the shell.
     * 
     * @return the target of the shell.
     */
    public Coordinate<Double> getTarget();

    /**
     * Returns the number of ticks until the shell reaches its target.
     * 
     * @return the number of ticks.
     */
    public Integer getTimeToTarget();
}