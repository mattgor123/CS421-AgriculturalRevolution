package rev.model.settlement.specializations;

import java.util.ArrayList;
import java.util.List;

import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;
import rev.model.settlement.core.Settlement;
import rev.model.settlement.specializations.Specializations.CultivationSpecialization;
import rev.utility.RandomUtility;

/**
 * The cultivation specialization.
 * 
 *
 * 
 */
public class BaseCultivationSpecialization extends BaseSpecialization implements
        CultivationSpecialization {

    /**
     * The range of cultivation.
     * 
     */
    public static final int BASE_CULTIVATION_RANGE = 2;

    /**
     * The maximum amount of time until the next harvest.
     * 
     */
    public static final long BASE_TIME_TO_HARVEST = 100000;

    /**
     * The amount of time until the next harvest.
     * 
     */
    private long timeToHarvest = BASE_TIME_TO_HARVEST;

    /**
     * Initializes the specialization.
     * 
     * @param settlement
     *            the settlement of the specialization.
     */
    public BaseCultivationSpecialization(Settlement settlement) {
        super(settlement);
    }

    @Override
    public String getName() {
        return "agriculture";
    }

    @Override
    public void operate(ModelDelegate model) {
        if (this.getSettlement().getPopulation() < this
                .getMinimumPopulationRequired()) {
            return;
        }
        this.timeToHarvest -= (model.getWeatherManager()
                .getCurrentTemperature() + this.getSettlement().getPopulation());
        if (this.timeToHarvest < 0) {
            this.timeToHarvest = BASE_TIME_TO_HARVEST;
            this.harvest(model);
        }
    }

    /**
     * Facilitates the harvest.
     * 
     * @param model
     *            the model.
     */
    private void harvest(ModelDelegate model) {
        int x = this.getSettlement().getX();
        int y = this.getSettlement().getY();
        int dx = RandomUtility.randomInteger(-BASE_CULTIVATION_RANGE,
                BASE_CULTIVATION_RANGE + 1);
        int num = Math.abs(Math.abs(dx) - BASE_CULTIVATION_RANGE);
        int dy = RandomUtility.randomInteger(-num, num + 1);
        Tile tile = model.getTileMap().getTile(x + dx, y + dy);
        this.getSettlement().getStorageOperation()
                .depositQuantity(tile.getAdjustedFertility() * 2);
        tile.contaminate(1);
    }

    @Override
    public List<SpecializationOption> getSpecializationOptions() {
        return new ArrayList<SpecializationOption>(0);
    }

    @Override
    public SpecializationType getType() {
        return SpecializationType.CULTIVATION;
    }

    @Override
    public Integer getMinimumPopulationRequired() {
        return 100;
    }

}