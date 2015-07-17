package rev.model.settlement.specializations;

import rev.model.settlement.core.Settlement;
import rev.model.settlement.specializations.Specializations.DefaultSpecialization;

/**
 * A base implementation of a settlement specialization.
 * 
 *
 * 
 */
public abstract class BaseSpecialization implements DefaultSpecialization {

    /**
     * The settlement of the specialization.
     * 
     */
    private final Settlement settlement;

    /**
     * Initializes the specialization with the specified settlement.
     * 
     * @param settlement
     *            the settlement.
     */
    public BaseSpecialization(Settlement settlement) {
        this.settlement = settlement;
    }

    @Override
    public Settlement getSettlement() {
        return this.settlement;
    }

    @Override
    public Boolean isStaffed() {
        return this.settlement.getPopulation() >= this
                .getMinimumPopulationRequired();
    }
}