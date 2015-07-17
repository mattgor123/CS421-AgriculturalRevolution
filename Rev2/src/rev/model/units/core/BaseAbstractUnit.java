package rev.model.units.core;

import apse.core.APSEActor;
import apse.core.APSEModelDelegate;
import rev.model.attributes.MutablePosition;
import rev.model.core.ModelDelegate;

/**
 * An abstract apse-actor. The unit "checks in" its position whenever it moves.
 * 
 *
 * 
 */
public abstract class BaseAbstractUnit extends APSEActor implements Unit {

    /**
     * The position of the unit.
     * 
     */
    private final MutablePosition position;

    /**
     * The units manager. The base unit registers its position with the unit
     * manager whenver it tries to move.
     * 
     */
    private final BaseUnitsManager manager;

    /**
     * The behavior of the unit.
     * 
     */
    private UnitBehavior behavior;

    public static final int BASE_MOVEMENT_POINTS = 100;

    private int movementPoints = BASE_MOVEMENT_POINTS;

    /**
     * Initializes the unit.
     * 
     * @param x
     *            the x position of the unit.
     * @param y
     *            the y position of the unit.
     * @param initialBehavior
     *            the initial behavior of the unit.
     */
    public BaseAbstractUnit(Integer x, Integer y, UnitBehavior initialBehavior,
            BaseUnitsManager manager) {
        this.position = new MutablePosition(x, y);
        this.behavior = initialBehavior;
        this.manager = manager;
    }

    @Override
    public void setBehavior(UnitBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void update(ModelDelegate model) {
        this.customUpdate(model);
        this.behavior.execute(this, model);
    }

    /**
     * A customizable addition to the update series.
     * 
     * @param model
     *            the model.
     */
    public void customUpdate(ModelDelegate model) {

    }

    @Override
    public void update(APSEModelDelegate delegate) {
        this.update((ModelDelegate) delegate);
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
    public Boolean moveTo(Integer x, Integer y) {
        return this.manager.moveUnitToPosition(this, x, y);
    }

    /**
     * This move operation is called by the units manager to update the unit's
     * position on the collision grid.
     * 
     * @param x
     * @param y
     */
    void registeredMoveTo(Integer x, Integer y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    @Override
    public Integer getMovementPoints() {
        return this.movementPoints;
    }

    @Override
    public void setMovementPoints(Integer points) {
        this.movementPoints = points;
    }
}