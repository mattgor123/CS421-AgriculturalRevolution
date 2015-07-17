package rev.model.units.core;

import rev.model.core.ModelDelegate;

/**
 * A unit behavior.
 * 
 *
 * 
 */
public interface UnitBehavior {

    /**
     * Executes the behavior.
     * 
     * @param unit
     *            the unit.
     * @param model
     *            the model.
     */
    public void execute(Unit unit, ModelDelegate model);
}