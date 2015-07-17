package rev.model.ordnance;

import java.util.List;

/**
 * Manages ordnance effects on the map.
 * 
 *
 * 
 */
public interface OrdnanceManager {

    /**
     * Adds a shell to the map.
     * 
     * @param shell
     *            the shell.
     */
    public void createShell(Shell shell);

    /**
     * Adds an ordnance effect to the map.
     * 
     * @param effect
     *            the effect.
     */
    public void createOrdnanceEffect(OrdnanceEffect effect);

    /**
     * Adds a cobalt explosive effect.
     * 
     * @param x
     *            the x position of the effect.
     * @param y
     *            the y position of the effect.
     * @param potency
     *            the potency of the effect.
     */
    public void createCobaltOrdnanceEffect(Integer x, Integer y, Integer potency);

    /**
     * Adds an explosive ordnance effect.
     * 
     * @param x
     *            the x position of the effect.
     * @param y
     *            the y position of the effect.
     * @param potency
     *            the potency of the effect.
     */
    public void createExplosiveOrdnanceEffect(Integer x, Integer y,
            Integer potency);

    /**
     * Returns the set of active shells.
     * 
     * @return the set of active shells.
     */
    public List<Shell> getShells();

    /**
     * Returns the set of active cobalt ordnance effects.
     * 
     * @return the set of cobalt effects.
     */
    public List<CobaltOrdnanceEffect> getCobaltOrdnanceEffects();

    /**
     * Returns the set of explosive ordnance effects.
     * 
     * @return the set of explosive effects.
     */
    public List<ExplosiveOrdnanceEffect> getExplosiveOrdnanceEffects();
}