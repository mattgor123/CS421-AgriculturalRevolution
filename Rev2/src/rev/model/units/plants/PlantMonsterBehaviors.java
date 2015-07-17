package rev.model.units.plants;

import rev.model.core.ModelDelegate;
import rev.model.map.core.Tile;
import rev.model.settlement.core.Settlement;
import rev.model.units.core.LandTravelBehavior;
import rev.model.units.core.Unit;
import rev.model.units.core.UnitBehavior;

/**
 * The plant-specific monster behaviors.
 * 
 *
 * 
 */
public class PlantMonsterBehaviors {

    public static class PlantMonsterFindCity implements UnitBehavior {

        private static final long BASE_TIME_FOR_SEARCH = 100;

        private long timeToNextSearch = BASE_TIME_FOR_SEARCH;

        @Override
        public void execute(Unit unit, ModelDelegate model) {
            this.timeToNextSearch--;
            if (this.timeToNextSearch > 0) {
                return;
            }
            this.timeToNextSearch = BASE_TIME_FOR_SEARCH;
            double x = unit.getX();
            double y = unit.getY();
            Settlement target = null;
            double closest = 2000;
            for (Settlement settlement : model.getSettlementManager()
                    .getSettlements()) {
                double dx = Math.abs(settlement.getX() - x);
                double dy = Math.abs(settlement.getY() - y);
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < closest && settlement.getPopulation() > 0) {
                    target = settlement;
                    closest = distance;
                }
            }
            if (target != null) {
                Tile tile = model.getTileMap().getTile(target.getX(),
                        target.getY());
                ((PlantMonster) unit).setBehavior(new LandTravelBehavior(unit,
                        tile, model, new PlantMonsterFindCity()));
            }
        }

    }
}