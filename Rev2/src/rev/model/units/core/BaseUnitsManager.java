package rev.model.units.core;

import java.util.ArrayList;
import java.util.List;

import apse.core.APSEActionLayer;
import apse.core.APSEActor;
import apse.core.APSEModelDelegate;
import rev.model.core.ModelDelegate;

/**
 * An apse-compatible units manager.
 * 
 *
 * 
 */
public class BaseUnitsManager extends APSEActionLayer implements UnitsManager {

    /**
     * The set of unit positions.
     * 
     */
    private final Unit[][] unitPositions;

    /**
     * The set of units.
     * 
     */
    private List<BaseAbstractUnit> units = new ArrayList<BaseAbstractUnit>();

    /**
     * Initializes the manager.
     * 
     * @param model
     *            the model delegate.
     */
    public BaseUnitsManager(ModelDelegate model) {
        this.unitPositions = new Unit[model.getTileMap().getWidth()][model
                .getTileMap().getHeight()];
    }

    @Override
    public Unit getUnit(Integer x, Integer y) {
        return this.unitPositions[x][y];
    }

    @Override
    public Boolean isOccupied(Integer x, Integer y) {
        return this.unitPositions[x][y] != null;
    }

    @Override
    public void update(APSEModelDelegate delegate) {

        /*
         * We only implement this as a layer as a convenient (and unorthodox
         * use) of the apse architecture.
         */
    }

    @Override
    public void insertActor(APSEActor actor) {
        BaseAbstractUnit unit = (BaseAbstractUnit) actor;
        this.units.add(unit);
        this.moveUnitToPosition(unit, unit.getX(), unit.getY());
    }

    @Override
    public void removeFlaggedActors() {
        for (int i = 0; i < this.units.size(); i++) {
            if (this.units.get(i).isFlagged()) {
                Unit unit = this.units.remove(i);
                this.unitPositions[unit.getX()][unit.getY()] = null;
                i--;
            }
        }
    }

    /**
     * Attempts to move the specified unit to the specified tile.
     * 
     * @param unit
     *            the unit to move.
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of tile.
     * @return true if the unit was moved, false otherwise.
     */
    public Boolean moveUnitToPosition(BaseAbstractUnit unit, Integer x,
            Integer y) {
        if (this.isOccupied(x, y)) {
            return false;
        }
        this.unitPositions[unit.getX()][unit.getY()] = null; // make the old
                                                             // position
                                                             // available
        this.unitPositions[x][y] = unit; // fill the new position
        unit.registeredMoveTo(x, y);
        return true;
    }
}