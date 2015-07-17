package rev.model.settlement.specializations;

import java.util.List;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;

/**
 * A settlement behavior.
 * 
 *
 * 
 */
public interface SettlementSpecialization {

    /**
     * Returns the type of specialization.
     * 
     * @return the type of specialization.
     */
    public SpecializationType getType();

    /**
     * Returns the name of the specialization.
     * 
     * @return the name of the specialization.
     */
    public String getName();

    /**
     * Returns the minimum population required for a settlement to adopt the
     * specialization.
     * 
     * @return the minimum population.
     */
    public Integer getMinimumPopulationRequired();

    /**
     * Returns the settlement of the specialization.
     * 
     * @return the settlement.
     */
    public Settlement getSettlement();

    /**
     * Updates according the specialization.
     * 
     * @param model
     *            the model.
     */
    public void operate(ModelDelegate model);

    /**
     * Returns the set of options associated with the specialization.
     * 
     * @return the set of specialization options.
     */
    public List<SpecializationOption> getSpecializationOptions();

    /**
     * Returns whether the settlement specialization has the necessary
     * population to function.
     * 
     * @return true if the settlement has the necessary population, false otherwise.
     */
    public Boolean isStaffed();
}