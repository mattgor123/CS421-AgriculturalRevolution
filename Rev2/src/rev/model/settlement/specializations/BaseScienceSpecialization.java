package rev.model.settlement.specializations;

import java.util.ArrayList;
import java.util.List;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;
import rev.model.settlement.specializations.Specializations.ScienceSpecialization;

/**
 * The base science specialization implementation.
 * 
 *
 * 
 */
public class BaseScienceSpecialization extends BaseSpecialization implements
        ScienceSpecialization {

    private static final int DELAY = 2;
    
    private int counter = DELAY;
    
    /**
     * Initializes the specialization.
     * 
     * @param settlement
     *            the settlement of the specialization.
     */
    public BaseScienceSpecialization(Settlement settlement) {
        super(settlement);
    }

    @Override
    public String getName() {
        return "science";
    }

    @Override
    public void operate(ModelDelegate model) {
        if (this.counter > 0) {
            this.counter--;
            return;
        }
        this.counter = DELAY;
        int expense = this.getSettlement().getPopulation() / 1000;
        model.getStatsManager().addResearch(expense + 1);
        this.getSettlement().getStorageOperation().withdrawQuantity(expense);
    }

    @Override
    public List<SpecializationOption> getSpecializationOptions() {
        List<SpecializationOption> options = new ArrayList<SpecializationOption>();
        options.add(new BaseConductResearchProjectOption());
        return options;
    }

    @Override
    public SpecializationType getType() {
        return SpecializationType.SCIENCE;
    }

    @Override
    public Integer getMinimumPopulationRequired() {
        return 1000;
    }
}