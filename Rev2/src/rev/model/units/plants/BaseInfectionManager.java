package rev.model.units.plants;

import java.util.List;

import rev.model.core.BaseModelDelegate;
import rev.model.data.TileOccupiedException;
import rev.model.units.core.BaseUnitsManager;

/**
 * The infection manager. Manages plant monsters and spores.
 * 
 *
 * 
 */
public class BaseInfectionManager implements InfectionManager {

    /**
     * The apse model delegate.
     * 
     */
    private BaseModelDelegate model;

    /**
     * The units manager.
     * 
     */
    private BaseUnitsManager manager;

    /**
     * Initializes the manager.
     * 
     * @param model
     *            the model.
     */
    public BaseInfectionManager(BaseModelDelegate model) {
        this.model = model;
        this.manager = (BaseUnitsManager) model.getUnitsManager();
    }

    @Override
    public void placeSpore(Integer x, Integer y, Integer potency) {
        this.model.insertActor(new BaseSpore(x, y, potency));
    }

    @Override
    public void placePlantMonster(Integer x, Integer y, Integer health)
            throws TileOccupiedException {
        BasePlantMonster monster = new BasePlantMonster(x, y, 100, this.manager);
        if (this.manager.moveUnitToPosition(monster, x, y)) {
            this.model.insertActor(monster);
        } else {
            throw new TileOccupiedException("Unit already exists at tile.");
        }
    }

    @Override
    public List<PlantMonster> getPlantMonsters() {
        return this.model.getActorsOfType(BasePlantMonster.ACTOR_TYPE,
                PlantMonster.class);
    }
}