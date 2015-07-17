package rev.model.player;

import rev.utility.FiniteQuantity;

/**
 * A treasury manager.
 * 
 *
 * 
 */
public class BaseTreasuryManager implements TreasuryManager {

    /**
     * A finite quantity object to store the current reserves.
     * 
     */
    private final FiniteQuantity treasury;

    /**
     * Initializes the treasury.
     * 
     * @param initial
     *            the initial reserves, in gold pieces.
     */
    public BaseTreasuryManager(Integer initial) {
        this.treasury = new FiniteQuantity(initial);
    }

    @Override
    public Integer getBalance() {
        return treasury.getQuantity();
    }

    @Override
    public void depositAmount(Integer amount) {
        this.treasury.depositAmount(amount);
    }

    @Override
    public Integer withdrawAmount(Integer amount) {
        return this.treasury.withdrawAmount(amount);
    }

}