package rev.model.ordnance;

import rev.model.core.ModelDelegate;
import rev.utility.Coordinate;
import apse.core.APSEActor;
import apse.core.APSEModelDelegate;

/**
 * A basic shell.
 * 
 *
 * 
 */
public class BaseShell extends APSEActor implements Shell {

    /**
     * The apse actor type.
     * 
     */
    public static final String ACTOR_TYPE = "shell";

    /**
     * The current position of the shell.
     * 
     */
    private final Coordinate<Double> position;

    /**
     * The origin of the shell.
     * 
     */
    private final Coordinate<Double> origin;

    /**
     * The destination of the shell.
     * 
     */
    private final Coordinate<Double> destination;

    private final Coordinate<Double> velocity;

    private int updatesToTarget;

    /**
     * The ordnance of the shell.
     * 
     */
    private OrdnanceEffect ordnance;

    /**
     * Initializes the shell.
     * 
     * @param origin
     *            the origin of the shell.
     * @param target
     *            the target of the shell.
     * @param ordnance
     *            the ordnance of the shell.
     */
    public BaseShell(Coordinate<Double> origin, Coordinate<Double> target,
            Double velocity, OrdnanceEffect ordnance) {
        this.position = new Coordinate<Double>(origin.getX(), origin.getY());
        this.origin = new Coordinate<Double>(origin.getX(), origin.getY());
        this.destination = new Coordinate<Double>(target.getX(), target.getY());
        this.ordnance = ordnance;
        double dx = target.getX() - origin.getX();
        double dy = target.getY() - origin.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        this.updatesToTarget = (int) (distance / velocity);
        this.velocity = new Coordinate<Double>(dx / this.updatesToTarget, dy
                / this.updatesToTarget);
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
        if (this.updatesToTarget < 0) {
            this.setFlag(true);
            model.getOrdnanceManager().createOrdnanceEffect(
                    this.getOrdnanceEffect());
        } else {
            this.position.setX(this.getX() + this.velocity.getX());
            this.position.setY(this.getY() + this.velocity.getY());
            this.updatesToTarget--;
        }
    }

    @Override
    public Double getX() {
        return this.position.getX();
    }

    @Override
    public Double getY() {
        return this.position.getY();
    }

    @Override
    public OrdnanceEffect getOrdnanceEffect() {
        return this.ordnance;
    }

    @Override
    public Coordinate<Double> getVelocity() {
        return this.velocity;
    }

    @Override
    public Coordinate<Double> getOrigin() {
        return this.origin;
    }

    @Override
    public Coordinate<Double> getTarget() {
        return this.destination;
    }

    @Override
    public Integer getTimeToTarget() {
        return this.updatesToTarget;
    }
}