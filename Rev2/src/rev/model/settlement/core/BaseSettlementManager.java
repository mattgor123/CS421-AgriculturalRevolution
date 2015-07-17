package rev.model.settlement.core;

import java.util.List;

import apse.core.APSEActor;
import apse.core.APSEActorListener;
import rev.model.core.BaseModelDelegate;
import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;
import rev.model.map.core.TileDomain;

/**
 * An apse-compatible settlement manager.
 * 
 *
 * 
 */
public class BaseSettlementManager implements SettlementManager,
        APSEActorListener {

    /**
     * The model.
     * 
     */
    private BaseModelDelegate model;

    /**
     * An array of settlements that maps settlements to tiles.
     * 
     */
    private Settlement[][] positionedSettlements;

    /**
     * Initializes the manager.
     * 
     * @param width
     *            the width of the map, in tiles.
     * @param height
     *            the the height of the map, in tiles.
     * @param model
     *            the model.
     */
    public BaseSettlementManager(int width, int height, ModelDelegate model) {
        this.positionedSettlements = new Settlement[width][height];
        this.model = (BaseModelDelegate) model;
    }

    @Override
    public List<Settlement> getSettlements() {
        return this.model.getActorsOfType(BaseSettlement.ACTOR_TYPE,
                Settlement.class);
    }

    @Override
    public void placeSettlement(Settlement settlement) {
        if (this.hasSettlement(settlement.getX(), settlement.getY())) {
            return;
        }
        Tile tile = this.model.getTileMap().getTile(settlement.getX(),
                settlement.getY());
        if (tile.getDomain() == TileDomain.AQUA) {
            return;
        }
        this.positionedSettlements[settlement.getX()][settlement.getY()] = settlement;
        this.model.insertActor((BaseSettlement) settlement);
        this.model.getRoadNetworkManager().placeRoad(tile);
        settlement.getStorageOperation().depositQuantity(
                NEW_SETTLEMENT_GRAIN_COST / 2);
    }

    @Override
    public Settlement getSettlement(Integer x, Integer y) {
        return this.positionedSettlements[x][y];
    }

    @Override
    public Boolean hasSettlement(Integer x, Integer y) {
        return this.getSettlement(x, y) != null;
    }

    @Override
    public Boolean removeSettlement(Integer x, Integer y) {
        if (!this.hasSettlement(x, y)) {
            return false;
        }
        ((BaseSettlement) this.getSettlement(x, y)).setFlag(true);
        return true;
    }

    @Override
    public void actorRemoved(APSEActor actor) {
        Settlement settlement = (BaseSettlement) actor;
        this.positionedSettlements[settlement.getX()][settlement.getY()] = null;
    }

    @Override
    public String getObservedActorType() {
        return BaseSettlement.ACTOR_TYPE;
    }

    @Override
    public void placePaidSettlement(Settlement newSettlement, Settlement payer) {
        Tile tile = this.model.getTileMap().getTile(newSettlement.getX(),
                newSettlement.getY());
        if (payer.getPopulation() >= NEW_SETTLEMENT_PEOPLE_COST
                && payer.getStorageOperation().getQuantity() > NEW_SETTLEMENT_GRAIN_COST
                && this.model.getRoadNetworkManager().hasRoad(tile)) {
            this.placeSettlement(newSettlement);
            payer.getStorageOperation().withdrawQuantity(NEW_SETTLEMENT_GRAIN_COST);
        }
    }

    @Override
    public Boolean transferGrain(Settlement sender, Settlement receiver,
            int quantity) {
        if (sender.getStorageOperation().getQuantity() < GRAIN_TRANSFER_FEE) {
            return false;
        }
        sender.getStorageOperation().withdrawQuantity(GRAIN_TRANSFER_FEE);
        Integer gift = sender.getStorageOperation().withdrawQuantity(quantity);
        receiver.getStorageOperation().depositQuantity(gift);
        return true;
    }

    @Override
    public Boolean transferPeople(Settlement sender, Settlement receiver,
            int quantity) {
        if (sender.getPopulation() < PEOPLE_TRANSFER_FEE) {
            return false;
        }
        sender.getStorageOperation().withdrawQuantity(PEOPLE_TRANSFER_FEE);
        Integer gift = sender.removePopulation(quantity);
        receiver.addPopulation(gift);
        return true;
    }
}