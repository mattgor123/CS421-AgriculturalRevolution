package rev.model.settlement.specializations;

/**
 * The set of settlement specializations.
 * 
 *
 * 
 */
public class Specializations {

    /**
     * The default specialization. Settlement does not produce anything, and
     * consumes food at slow rate.
     * 
     */
    public static interface DefaultSpecialization extends
            SettlementSpecialization {

    }

    /**
     * The cultivation specialization. Produces grain at the expense of
     * contaminating land.
     * 
     */
    public static interface CultivationSpecialization extends
            SettlementSpecialization {

    }

    /**
     * The science specialization. Produces research at the expense of grain
     * consumption.
     * 
     */
    public static interface ScienceSpecialization extends
            SettlementSpecialization {

    }
}