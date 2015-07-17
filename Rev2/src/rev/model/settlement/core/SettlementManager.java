package rev.model.settlement.core;

import java.util.List;

import rev.model.data.TileOccupiedException;

/**
 * Manages external manipulation of settlements in the model.
 * 
 *
 * 
 */
public interface SettlementManager {

    public static final int NEW_SETTLEMENT_PEOPLE_COST = 500;

    public static final int NEW_SETTLEMENT_GRAIN_COST = 2000;
    
    /**
     * The cost to transfer grain.
     * 
     */
    public static final int GRAIN_TRANSFER_FEE = 100;

    /**
     * The cost to transfer people.
     * 
     */
    public static final int PEOPLE_TRANSFER_FEE = 500;

    /**
     * Returns the set of settlements.
     * 
     * @return the set of settlements.
     */
    public List<Settlement> getSettlements();

    /**
     * Places a settlement at the specified tile.
     * 
     * @param population
     *            the initial population of the settlement.
     * @param settlement
     *            the settlement.
     * @throws TileOccupiedException
     *             called when trying to place a settlement on a tile that
     *             already contains one.
     */
    public void placeSettlement(Settlement settlement)
            throws TileOccupiedException;

    /**
     * Places a settlement at the specified tile, paid for by another
     * settlement.
     * 
     * @param newSettlement
     *            the new settlement.
     * @param payer
     *            the payer for the settlement.
     */
    public void placePaidSettlement(Settlement newSettlement, Settlement payer);

    /**
     * Returns the settlement at the specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return the settlement at the tile, if one exists.
     */
    public Settlement getSettlement(Integer x, Integer y);

    /**
     * Returns whether a settlement exists at the specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if a settlement exists at the tile, false otherwise.
     */
    public Boolean hasSettlement(Integer x, Integer y);

    /**
     * Removes the settlement at the specified tile. Cannot remove a settlement
     * that is populated.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if the settlement was removed, false otherwise.
     */
    public Boolean removeSettlement(Integer x, Integer y);

    /**
     * Transfers a quantity of grain between settlements, and applies a transfer
     * free to the sending settlement.
     * 
     * @param sender
     *            the sender.
     * @param receiver
     *            the receiver.
     * @param quantity
     *            the transfer quantity.
     * @return
     */
    public Boolean transferGrain(Settlement sender, Settlement receiver,
            int quantity);

    /**
     * Transfers a people between settlements, and applies a transfer fee.
     * 
     * @param sender
     *            the sender.
     * @param receiver
     *            the receiver.
     * @param quantity
     *            the transfer quantity.
     * @return
     */
    public Boolean transferPeople(Settlement sender, Settlement receiver,
            int quantity);
}