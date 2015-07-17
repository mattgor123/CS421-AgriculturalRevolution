package rev.model.settlement.core;

import rev.model.core.ModelDelegate;

/**
 * A user-fired mutation on a settlement.
 * 
 *
 * 
 */
public interface SettlementOption {

    /**
     * Returns the title of the option.
     * 
     * @return the title.
     */
    public String getTitle();

    /**
     * Executes the option on the specified settlement.
     * 
     * @param model
     *            the model.
     * @param settlement
     *            the settlement.
     */
    public void execute(ModelDelegate model, Settlement settlement);

    /**
     * Returns a description of the option.
     * 
     * @return the description.
     */
    public String getDescription();

    /**
     * Returns whether the option is available to the specified settlement.
     * 
     * @param model
     *            the model.
     * @param settlement
     *            the settlement.
     * @return true if available, false otherwise.
     */
    public Boolean isAvailabeToSettlement(ModelDelegate model,
            Settlement settlement);

    /**
     * Returns a statement about the availability of the option for the
     * specified settlement.
     * 
     * @param model
     *            the model.
     * @param settlement
     *            the settlement.
     * @return the availability statement.
     */
    public String getAvailabilityStatement(ModelDelegate model,
            Settlement settlement);

    /**
     * Returns whether the option is visible to the specified settlement.
     * 
     * @param model
     *            the model.
     * @param settlement
     *            the settlement.
     * @return true if visible, false otherwise.
     */
    public Boolean isVisibleToSettlement(ModelDelegate model,
            Settlement settlement);
}