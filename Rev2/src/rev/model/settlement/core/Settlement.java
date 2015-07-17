package rev.model.settlement.core;

import java.util.List;

import rev.model.core.ModelDelegate;
import rev.model.core.ModelDynamic;
import rev.model.defense.DefenseOperation;
import rev.model.ordnance.AffectedByCobaltOrdnance;
import rev.model.properties.Populated;
import rev.model.properties.Positioned;
import rev.model.settlement.specializations.SettlementSpecialization;
import rev.model.storage.StorageOperation;

/**
 * A settlement.
 * 
 *
 * 
 */
public interface Settlement extends Positioned, Populated,
        AffectedByCobaltOrdnance, ModelDynamic {

    /**
     * The amount of grain a city need to produce one additonal citizen per
     * update.
     * 
     */
    public static final int GRAIN_PER_NEW_CITIZEN = 2500;

    /**
     * Returns the name of the settlement.
     * 
     * @return the name of the settlement.
     */
    public String getName();

    /**
     * Returns the specialization of the settlement.
     * 
     * @return the specialization.
     */
    public SettlementSpecialization getSpecialization();

    /**
     * Sets the specialization of the settlement.
     * 
     * @param specialization
     *            the new specialization.
     */
    public void setSpecialization(SettlementSpecialization specialization);

    /**
     * Returns the storage operation associated with the settlement.
     * 
     * @return the storage operation.
     */
    public StorageOperation getStorageOperation();

    /**
     * Returns the defenses of the settlement.
     * 
     * @return the defenses of the settlement.
     */
    public DefenseOperation getDefenses();

    /**
     * Updates the settlement according to the specialization.
     * 
     */
    public void update(ModelDelegate model);

    /**
     * Returns the general settlement options.
     * 
     * @return the set of options.
     */
    public List<SettlementOption> getOptions();

    /**
     * Returns whether the settlement is starving (i.e. declining in
     * population).
     * 
     * @return true if starving, false otherwise.
     */
    public Boolean isStarving();

    /**
     * Returns current food consumption.
     * 
     * @return current food consumption.
     */
    public Integer getFoodConsumedPerTurn();

    /**
     * Returns current population growth.
     * 
     * @return current population growth.
     */
    public Integer getPopulationGrowthPerTurn();
}