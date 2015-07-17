package rev.model.civic;

import rev.model.settlement.core.Settlement;

/**
 * An operation associated with a settlement.
 * 
 *
 * 
 */
public interface CivicOperation {

    /**
     * Returns the settlement.
     * 
     * @return the settlement.
     */
    public Settlement getSettlement();
}