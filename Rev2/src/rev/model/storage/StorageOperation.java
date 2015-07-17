package rev.model.storage;

import rev.model.settlement.core.Settlement;

/**
 * An operation for storing grain.
 * 
 *
 * 
 */
public interface StorageOperation {

    /**
     * Returns the settlement of the operation.
     * 
     * @return the settlement.
     */
    public Settlement getSettlement();

    /**
     * Returns the current stored quantity of grain, in bushels.
     * 
     * @return the current stored quantity.
     */
    public Integer getQuantity();

    /**
     * Deposits grain.
     * 
     * @param quantity
     *            the quantity to deposit, in bushels.
     * @return the quantity that could be deposited.
     */
    public Integer depositQuantity(Integer quantity);

    /**
     * Withdraws grain.
     * 
     * @param quantity
     *            the quantity to withdraw, in bushels.
     * @return the quantity that could be withdrawn.
     */
    public Integer withdrawQuantity(Integer quantity);
}