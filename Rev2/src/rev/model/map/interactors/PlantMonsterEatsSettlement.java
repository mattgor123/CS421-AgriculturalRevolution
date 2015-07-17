package rev.model.map.interactors;

import rev.model.core.BaseModelDelegate;
import rev.model.settlement.core.Settlement;
import rev.model.units.plants.BasePlantMonster;
import rev.model.units.plants.PlantMonster;
import apse.core.APSEActionLayer;
import apse.core.APSEActor;
import apse.core.APSEModelDelegate;

/**
 * The layer on which plant monsters consume settlements.
 * 
 *
 * 
 */
public class PlantMonsterEatsSettlement extends APSEActionLayer {

    @Override
    public void update(APSEModelDelegate delegate) {
        BaseModelDelegate model = (BaseModelDelegate) delegate;
        for (PlantMonster monster : model.getActorsOfType(
                BasePlantMonster.ACTOR_TYPE, PlantMonster.class)) {
            int x = monster.getX();
            int y = monster.getY();
            Settlement settlement = model.getSettlementManager().getSettlement(
                    x, y);
            if (settlement != null) {
                settlement.removePopulation(1);
                settlement.getStorageOperation().withdrawQuantity(1);
            }
        }

    }

    @Override
    public void insertActor(APSEActor actor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeFlaggedActors() {
        // TODO Auto-generated method stub

    }
}