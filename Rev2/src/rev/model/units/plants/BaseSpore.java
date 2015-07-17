package rev.model.units.plants;

import apse.core.APSEActor;
import apse.core.APSEModelDelegate;
import rev.model.attributes.Position;
import rev.model.core.ModelDelegate;

/**
 * A spore that contaminates land.
 * 
 *
 * 
 */
public class BaseSpore extends APSEActor implements Spore {

    /**
     * The apse actor type.
     * 
     */
    public static final String ACTOR_TYPE = "spore";

    /**
     * The position of the spore.
     * 
     */
    private Position position;

    /**
     * The potency of the spore.
     * 
     */
    private int potency;

    /**
     * Initializes the spore.
     * 
     * @param x
     *            the x position of the spore.
     * @param y
     *            the y position of the spore.
     * @param potency
     *            the potency of the spore.
     */
    public BaseSpore(int x, int y, int potency) {
        this.position = new Position(x, y);
        this.potency = potency;
    }

    @Override
    public void update(ModelDelegate model) {

        /*
         * The spore does not update on its own.
         */
    }

    @Override
    public Integer getPotency() {
        return this.potency;
    }

    @Override
    public void eliminate() {
        this.setFlag(true);
    }

    @Override
    public Integer getX() {
        return this.position.getX();
    }

    @Override
    public Integer getY() {
        return this.position.getY();
    }

    @Override
    public void update(APSEModelDelegate delegate) {
        this.update((ModelDelegate) delegate);
    }

    @Override
    public String getType() {
        return ACTOR_TYPE;
    }

}