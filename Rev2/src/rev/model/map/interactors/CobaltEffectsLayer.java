package rev.model.map.interactors;

import rev.model.core.BaseModelDelegate;
import rev.model.map.core.Tile;
import rev.model.map.core.LandTile;
import rev.model.map.core.TileDomain;
import rev.model.ordnance.BaseCobaltOrdnanceEffect;
import apse.core.APSEActionLayer;
import apse.core.APSEActor;
import apse.core.APSEModelDelegate;

/**
 * Facilitates interactions between cobalt ordnance effects and entities
 * affected by them.
 * 
 *
 * 
 */
public class CobaltEffectsLayer extends APSEActionLayer {

    @Override
    public void update(APSEModelDelegate delegate) {
        BaseModelDelegate model = (BaseModelDelegate) delegate;
        for (BaseCobaltOrdnanceEffect effect : delegate.getActorsOfType(
                BaseCobaltOrdnanceEffect.ACTOR_TYPE,
                BaseCobaltOrdnanceEffect.class)) {
            Tile tile = model.getTileMap()
                    .getTile(effect.getX(), effect.getY());
            if (tile.getDomain() == TileDomain.TERRA) {
                ((LandTile) tile).respondToEffect(effect);
            }
            if (model.getSettlementManager().hasSettlement(effect.getX(),
                    effect.getY())) {
                model.getSettlementManager()
                        .getSettlement(effect.getX(), effect.getY())
                        .respondToEffect(effect);
            }
            if (model.getUnitsManager()
                    .isOccupied(effect.getX(), effect.getY())) {
                model.getUnitsManager().getUnit(effect.getX(), effect.getY())
                        .respondToEffect(effect);
            }
        }
    }

    @Override
    public void insertActor(APSEActor actor) {

        /*
         * No need.
         */
    }

    @Override
    public void removeFlaggedActors() {

        /*
         * No need.
         */
    }

}