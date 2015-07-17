package rev.model.settlement.specializations;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;
import rev.model.settlement.specializations.SpecializationOption.ConductResearchProjectOption;

/**
 * The research operation contributes to research (i.e. research progress) but
 * consumes food at a fast rate.
 * 
 *
 * 
 */
public class BaseConductResearchProjectOption implements
        ConductResearchProjectOption {

    /**
     * The cost of the operation.
     * 
     */
    public static final Integer COST = 500;

    @Override
    public void execute(ModelDelegate model, Settlement settlement) {
        model.getStatsManager().addResearch(settlement.getPopulation());
        settlement.getStorageOperation().withdrawQuantity(COST);
    }

    @Override
    public String getDescription() {
        return "Conduct Research Project (500 grain)";
    }

    @Override
    public Boolean isAvailabeToSettlement(ModelDelegate model,
            Settlement settlement) {
        return settlement.getStorageOperation().getQuantity() >= COST;
    }

    @Override
    public String getAvailabilityStatement(ModelDelegate model,
            Settlement settlement) {
        if (!this.isAvailabeToSettlement(model, settlement)) {
            return "This settlement lacks the sufficient quantity of grain.";
        }
        return "Available.";
    }

    @Override
    public Boolean isVisibleToSettlement(ModelDelegate model,
            Settlement settlement) {
        return true;
    }

    @Override
    public String getTitle() {
        return "conduct research project";
    }
}