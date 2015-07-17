package rev.model.player;

/**
 * The financial assets available to the player.
 * 
 *
 * 
 */
public interface TreasuryManager {

    /**
     * Returns the current balance.
     * 
     * @return the current balance, in gold pieces.
     */
    public Integer getBalance();

    /**
     * Deposits an amount into the treasury.
     * 
     * @param amount
     *            the amount to deposit, in gold pieces.
     */
    public void depositAmount(Integer amount);

    /**
     * Withdraws an amount from the treasury.
     * 
     * @param amount
     *            the amount to withdraw, in gold pieces.
     * @return the amount that could be withdrawn.
     */
    public Integer withdrawAmount(Integer amount);
}