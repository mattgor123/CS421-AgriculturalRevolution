package rev.model.defense;

import rev.model.civic.CivicOperation;
import rev.model.core.ModelDelegate;
import rev.utility.Coordinate;

/**
 * Manages the defenses of a settlement.
 * 
 *
 * 
 */
public interface DefenseOperation extends CivicOperation {

    /**
     * The price of a cobalt shell.
     * 
     */
    public static final int COBALT_SHELL_COST = 100;

    /**
     * The price of an explosive shell.
     * 
     */
    public static final int EXPLOSIVE_SHELL_COST = 100;

    /**
     * Instructs the settlement to fire a cobalt shell at the specified target.
     * 
     * @param target
     *            the target.
     * @param model
     *            the model.
     * @return true if the shell could be fired, false if too expensive.
     */
    public Boolean fireCobaltShell(Coordinate<Double> target,
            ModelDelegate model);

    /**
     * Instructs the settlement to fire an explosive shell at the specified
     * target.
     * 
     * @param target
     *            the target.
     * @param model
     *            the model.
     * @return true if the shell could be fired, false if too expensive.
     */
    public Boolean fireExplosiveShell(Coordinate<Double> target,
            ModelDelegate model);
}