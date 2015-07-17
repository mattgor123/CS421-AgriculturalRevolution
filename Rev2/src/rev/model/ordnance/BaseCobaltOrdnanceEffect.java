package rev.model.ordnance;

import apse.core.APSEActor;
import apse.core.APSEModelDelegate;
import rev.model.attributes.Position;
import rev.model.core.ModelDelegate;
import rev.utility.RandomUtility;

/**
 * A basic apse-driven cobalt effect.
 * 
 *
 * 
 */
public class BaseCobaltOrdnanceEffect extends APSEActor implements
        CobaltOrdnanceEffect {

    /**
     * The apse actor type.
     * 
     */
    public static final String ACTOR_TYPE = "cobalt";

    /**
     * The position of the effect.
     * 
     */
    private final Position position;

    /**
     * The potency of the effect.
     * 
     */
    private int potency;

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
    public BaseCobaltOrdnanceEffect(int x, int y, int potency) {
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
        this.potency /= 2;
        if (this.potency < 20) {
            this.setFlag(true);
        }
        if (RandomUtility.nextBoolean(this.getReplicationProbability())) {
            int collateralPotency = this.potency;
            model.getOrdnanceManager().createCobaltOrdnanceEffect(
                    this.getX() + 1, this.getY(), collateralPotency);
            model.getOrdnanceManager().createCobaltOrdnanceEffect(
                    this.getX() - 1, this.getY(), collateralPotency);
            model.getOrdnanceManager().createCobaltOrdnanceEffect(
                    this.getX(), this.getY() + 1, collateralPotency);
            model.getOrdnanceManager().createCobaltOrdnanceEffect(
                    this.getX(), this.getY() - 1, collateralPotency);
        }
    }

    @Override
    public Double getReplicationProbability() {
        return (double) (this.potency / (double) OrdnanceEffect.MAXIMUM_POTENCY * 2.0);
    }
}