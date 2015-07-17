package rev.model.settlement.core;

import java.util.ArrayList;
import java.util.List;

import apse.core.APSEActor;
import apse.core.APSEModelDelegate;
import rev.model.attributes.Population;
import rev.model.attributes.Position;
import rev.model.core.ModelDelegate;
import rev.model.defense.BaseDefenseOperation;
import rev.model.defense.DefenseOperation;
import rev.model.ordnance.CobaltOrdnanceEffect;
import rev.model.settlement.specializations.BaseDefaultSpecialization;
import rev.model.settlement.specializations.SettlementSpecialization;
import rev.model.storage.BaseStorageOperation;
import rev.model.storage.StorageOperation;

/**
 * A basic settlement.
 * 
 *
 * 
 */
public class BaseSettlement extends APSEActor implements Settlement {

    /**
     * The apse actor type.
     * 
     */
    public static final String ACTOR_TYPE = "settlement";

    /**
     * The name of the settlement.
     * 
     */
    private final String name;

    /**
     * The population of the settlement.
     * 
     */
    private final Population population;

    /**
     * The position of the settlement.
     * 
     */
    private final Position position;

    /**
     * The settlement specialization.
     * 
     */
    private SettlementSpecialization specialization = new BaseDefaultSpecialization(
            this);

    /**
     * The storage operation of the settlement.
     * 
     */
    private final StorageOperation storage = new BaseStorageOperation(this);

    /**
     * The defenses of the settlement.
     * 
     */
    private final DefenseOperation defenses = new BaseDefenseOperation(this);

    /**
     * The base amount of ticks for the population to update.
     * 
     */
    private static final int BASE_POPULATION_UPDATE_DELAY = 40;

    /**
     * The current amount of time until the next population update.
     * 
     */
    private int populationUpdateDelay = BASE_POPULATION_UPDATE_DELAY;

    /**
     * Initializes the settlement.
     * 
     * @param name
     *            the name of the settlement.
     * @param x
     *            the x index of the tile under the settlement.
     * @param y
     *            the y index of the tile under the settlement.
     * @param initialPopulation
     *            the initial population of the settlement.
     */
    public BaseSettlement(String name, Integer x, Integer y,
            Integer initialPopulation) {
        this.name = name;
        this.population = new Population(initialPopulation);
        this.position = new Position(x, y);
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
    public Integer getPopulation() {
        return this.population.getPopulation();
    }

    @Override
    public Integer addPopulation(Integer population) {
        this.population.addPopulation(population);
        return population;
    }

    @Override
    public Integer removePopulation(Integer population) {
        return this.population.removePopulation(population);
    }

    @Override
    public void update(APSEModelDelegate delegate) {
        this.update((ModelDelegate) delegate);
    }

    @Override
    public void update(ModelDelegate model) {
        this.getSpecialization().operate(model);
        this.updatePopulation();
    }

    private void updatePopulation() {
        if (this.getPopulation() == 0) {
            return;
        }
        this.populationUpdateDelay--;
        if (this.populationUpdateDelay < 0) {
            this.populationUpdateDelay = BASE_POPULATION_UPDATE_DELAY;
            int requiredQuantity = this.getRequiredFoodQuantity();
            int fulfilled = this.getStorageOperation().withdrawQuantity(
                    requiredQuantity);
            int dp = requiredQuantity - fulfilled;
            if (dp > 0) {
                this.removePopulation(1 + dp / 100);
            } else {
                this.addPopulation(this.getStorageOperation().getQuantity()
                        / GRAIN_PER_NEW_CITIZEN);
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return ACTOR_TYPE;
    }

    @Override
    public StorageOperation getStorageOperation() {
        return this.storage;
    }

    @Override
    public void respondToEffect(CobaltOrdnanceEffect effect) {
        this.removePopulation(effect.getPotency());
    }

    @Override
    public DefenseOperation getDefenses() {
        return this.defenses;
    }

    @Override
    public SettlementSpecialization getSpecialization() {
        return this.specialization;
    }

    @Override
    public void setSpecialization(SettlementSpecialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public List<SettlementOption> getOptions() {
        return new ArrayList<SettlementOption>();
    }

    @Override
    public Boolean isStarving() {
        return this.getStorageOperation().getQuantity() < this
                .getRequiredFoodQuantity();
    }

    /**
     * Returns the quantity of food required for the settlement to grow.
     * 
     * @return the quantity of food.
     */
    private Integer getRequiredFoodQuantity() {
        return this.getPopulation() / 100 + 1;
    }

    @Override
    public Integer getFoodConsumedPerTurn() {
        return this.getRequiredFoodQuantity();
    }

    @Override
    public Integer getPopulationGrowthPerTurn() {
        int requiredQuantity = this.getRequiredFoodQuantity();
        int dp = requiredQuantity - this.getStorageOperation().getQuantity();
        if (dp > 0) {
            return -(1 + dp / 100);
        } else {
            return this.getStorageOperation().getQuantity()
                    / GRAIN_PER_NEW_CITIZEN;
        }
    }
}