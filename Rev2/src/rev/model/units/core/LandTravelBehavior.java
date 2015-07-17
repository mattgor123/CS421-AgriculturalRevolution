package rev.model.units.core;

import java.util.LinkedList;
import java.util.List;

import rev.model.core.ModelDelegate;
import rev.model.data.MotionOperation;
import rev.model.map.core.Tile;

/**
 * An behavior in which a unit travels to a destination.
 * 
 *
 * 
 */
public class LandTravelBehavior implements UnitBehavior {

    /**
     * The behavior loaded into the unit when the traveling is concluded.
     * 
     */
    private UnitBehavior nextBehavior;

    /**
     * The travel path.
     * 
     */
    private LinkedList<MotionOperation> motions = new LinkedList<MotionOperation>();

    /**
     * Initializes the behavior.
     * 
     * @param unit
     *            the unit.
     * @param end
     *            the destination
     * @param model
     *            the model.
     * @param next
     *            the next behavior once the destination has been reached.
     */
    public LandTravelBehavior(Unit unit, Tile destination, ModelDelegate model,
            UnitBehavior next) {
        this.nextBehavior = next;
        Tile originTile = model.getTileMap().getTile(unit.getX(), unit.getY());
        List<MotionOperation> ops = model.getPathDeveloper()
                .getLandPathBetween(originTile, destination);
        if (ops == null) {
            unit.setBehavior(this.nextBehavior);
            return;
        }
        for (MotionOperation op : ops) {
            this.motions.add(op);
        }
    }

    @Override
    public void execute(Unit unit, ModelDelegate model) {
        unit.setMovementPoints(unit.getMovementPoints() + unit.getSpeed());
    	if (motions.isEmpty()) {
            unit.setBehavior(this.nextBehavior);
            return;
        }
        if (unit.getMovementPoints() > 100) {
            Tile next = this.motions.peek().getDestination();
            if (unit.moveTo(next.getX(), next.getY())) {
                unit.setMovementPoints(0);
                this.motions.poll();
                return;
            } else {
            	unit.setBehavior(this.nextBehavior);
            }
        }
        unit.setBehavior(this.nextBehavior);
    }
}