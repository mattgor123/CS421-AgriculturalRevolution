package rev.model.ordnance;

import apse.core.APSEActor;
import apse.core.APSEModelDelegate;
import rev.model.attributes.Position;
import rev.model.core.ModelDelegate;

/**
 * A basic apse explosive ordnance effect.
 * 
 *
 * 
 */
public class BaseExplosiveOrdnanceEffect extends APSEActor implements
        ExplosiveOrdnanceEffect {

    /**
     * The apse actor type.
     * 
     */
    public static final String ACTOR_TYPE = "explosive";

    /**
     * The position of the effect.
     * 
     */
    private final Position position;

    /**
     * The potency of the effect.
     * 
     */
    private final int potency;

    /**
     * Initializes the effect.
     * 
     * @param x
     *            the x position of the effect.
     * @param y
     *            the y position of the effect,
     * @param potency
     *            the potency of the effect.
     */
    public BaseExplosiveOrdnanceEffect(int x, int y, int potency) {
        this.position = new Position(x, y);
        this.potency = potency;
    }

    @Override
    public Integer getPotency() {
        return this.potency;
    }

    @Override
    public Integer getX() {
        return position.getX();
    }

    @Override
    public Integer getY() {
        return position.getY();
    }

    @Override
    public void update(APSEModelDelegate delegate) {
        this.update((ModelDelegate) delegate);
    }

    @Override
    public String getType() {
        return ACTOR_TYPE;
    }

    @Override
    public void update(ModelDelegate model) {
        this.setFlag(true);
    }

    @Override
    public Double getReplicationProbability() {
        // TODO Auto-generated method stub
        return null;
    }
}