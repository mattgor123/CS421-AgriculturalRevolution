package rev.model.settlement.specializations;

import java.util.ArrayList;
import java.util.List;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;
import rev.model.settlement.specializations.Specializations.DefaultSpecialization;

/**
 * The default specialization.
 * 
 *
 * 
 */
public class BaseDefaultSpecialization extends BaseSpecialization implements
        DefaultSpecialization {

    /**
     * Initializes the specialization.
     * 
     * @param settlement
     *            the settlement of the specialization.
     */
    public BaseDefaultSpecialization(Settlement settlement) {
        super(settlement);
    }

    @Override
    public String getName() {
        return "none";
    }

    @Override
    public void operate(ModelDelegate model) {
        /*
         * The default specialization does not do anything.
         */
    }

    @Override
    public List<SpecializationOption> getSpecializationOptions() {
        return new ArrayList<SpecializationOption>(0);
    }

    @Override
    public SpecializationType getType() {
        return SpecializationType.DEFAULT;
    }

    @Override
    public Integer getMinimumPopulationRequired() {
        return 0;
    }
}