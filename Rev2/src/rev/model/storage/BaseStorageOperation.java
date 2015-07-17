package rev.model.storage;

import rev.model.settlement.core.Settlement;
import rev.utility.FiniteQuantity;

/**
 * A basic storage operation.
 * 
 *
 * 
 */
public class BaseStorageOperation implements StorageOperation {

    /**
     * The settlement operating the operation.
     * 
     */
    private final Settlement settlement;

    /**
     * The quantity stored by the operation.
     * 
     */
    private final FiniteQuantity stored = new FiniteQuantity(0);

    /**
     * Initializes the storage operation.
     * 
     * @param settlement
     *            the settlement from which the operation is based.
     */
    public BaseStorageOperation(Settlement settlement) {
        this.settlement = settlement;
    }

    @Override
    public Integer getQuantity() {
        return this.stored.getQuantity();
    }

    @Override
    public Integer depositQuantity(Integer quantity) {
        this.stored.depositAmount(quantity);
        return quantity;
    }

    @Override
    public Integer withdrawQuantity(Integer quantity) {
        return this.stored.withdrawAmount(quantity);
    }

    @Override
    public Settlement getSettlement() {
        return this.settlement;
    }
}