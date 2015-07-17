package rev.model.settlement.specializations;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;

/**
 * A base implementation of a specialization option.
 * 
 *
 * 
 */
public abstract class BaseSpecializationOption implements SpecializationOption {

    @Override
    public String getAvailabilityStatement(ModelDelegate model,
            Settlement settlement) {
        if (this.isAvailabeToSettlement(model, settlement)) {
            return "Available.";
        }
        return this.getUnavailabilityStatement(settlement);
    }

    /**
     * Returns a statement about the unavailibity of the option for the
     * specified settlement.
     * 
     * @param settlement
     *            the settlement.
     * @return the statement.
     */
    public abstract String getUnavailabilityStatement(Settlement settlement);
}