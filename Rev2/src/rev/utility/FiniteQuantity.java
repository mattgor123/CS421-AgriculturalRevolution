package rev.utility;

/**
 * A finite quantity.
 * 
 *
 * 
 */
public class FiniteQuantity {

    /**
     * The quantity value.
     * 
     */
    int quantity;

    /**
     * Instantiates the quantity.
     * 
     * @param initialQuantity
     *            the initial quantity value.
     */
    public FiniteQuantity(int initialQuantity) {
        this.quantity = Math.max(initialQuantity, 0);
    }

    /**
     * Returns the quantity value.
     * 
     * @return the quantity value.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Deposits an amount into the quantity.
     * 
     * @param amount
     *            the amount to deposit.
     */
    public void depositAmount(int amount) {
        this.quantity += amount;
    }

    /**
     * Withdraws an amount from the quantity.
     * 
     * @param amount
     *            the amount to withdraw.
     * @return the withdrawn amount.
     */
    public int withdrawAmount(int amount) {
        int adjusted = this.quantity - amount;
        this.quantity = Math.max(0, adjusted);
        return Math.min(amount, amount + adjusted);
    }
}