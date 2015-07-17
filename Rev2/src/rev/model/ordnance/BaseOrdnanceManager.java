package rev.model.ordnance;

import java.util.List;

import rev.model.core.BaseModelDelegate;
import apse.core.APSEActor;

/**
 * The apse ordnance manager.
 * 
 *
 * 
 */
public class BaseOrdnanceManager implements OrdnanceManager {

    /**
     * The underlying apse architecture.
     * 
     */
    private BaseModelDelegate model;

    /**
     * Initializes the manager.
     * 
     * @param model
     *            the model.
     */
    public BaseOrdnanceManager(BaseModelDelegate model) {
        this.model = model;
    }

    @Override
    public void createShell(Shell shell) {
        BaseShell apseShell = (BaseShell) shell;
        this.model.insertActor(apseShell);
    }

    @Override
    public void createOrdnanceEffect(OrdnanceEffect effect) {
        this.model.insertActor((APSEActor) effect);
    }

    @Override
    public List<Shell> getShells() {
        return this.model.getActorsOfType(BaseShell.ACTOR_TYPE, Shell.class);
    }

    @Override
    public List<CobaltOrdnanceEffect> getCobaltOrdnanceEffects() {
        return this.model.getActorsOfType(BaseCobaltOrdnanceEffect.ACTOR_TYPE,
                CobaltOrdnanceEffect.class);
    }

    @Override
    public List<ExplosiveOrdnanceEffect> getExplosiveOrdnanceEffects() {
        return this.model.getActorsOfType(
                BaseExplosiveOrdnanceEffect.ACTOR_TYPE,
                ExplosiveOrdnanceEffect.class);
    }

    @Override
    public void createCobaltOrdnanceEffect(Integer x, Integer y, Integer potency) {
        this.createOrdnanceEffect(new BaseCobaltOrdnanceEffect(x, y, potency));
    }

    @Override
    public void createExplosiveOrdnanceEffect(Integer x, Integer y,
            Integer potency) {
        this.createOrdnanceEffect(new BaseExplosiveOrdnanceEffect(x, y, potency));
    }
}