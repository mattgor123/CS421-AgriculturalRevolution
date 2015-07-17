package rev.model.settlement.specializations;

import rev.model.settlement.core.SettlementOption;

/**
 * A settlement option provided by a settlement.
 * 
 *
 * 
 */
public interface SpecializationOption extends SettlementOption {

    /**
     * An option available to research settlements to spend a large amount of
     * grain on a large sudden increase in research.
     * 
 *
     * 
     */
    public static interface ConductResearchProjectOption extends
            SpecializationOption {

    }
}