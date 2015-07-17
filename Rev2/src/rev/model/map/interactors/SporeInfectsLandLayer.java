package rev.model.map.interactors;

import rev.model.core.BaseModelDelegate;
import rev.model.units.plants.BaseSpore;
import rev.model.units.plants.Spore;
import apse.core.APSEActionLayer;
import apse.core.APSEActor;
import apse.core.APSEModelDelegate;

/**
 * The layer on which spores (actors emanated by contaminated tile) infect land.
 * 
 *
 * 
 */
public class SporeInfectsLandLayer extends APSEActionLayer {

    @Override
    public void update(APSEModelDelegate delegate) {
        BaseModelDelegate model = (BaseModelDelegate) delegate;
        for (Spore spore : delegate.getActorsOfType(BaseSpore.ACTOR_TYPE,
                Spore.class)) {
            model.getTileMap().getTile(spore.getX(), spore.getY())
                    .contaminate(spore.getPotency());
            spore.eliminate();
        }
    }

    @Override
    public void insertActor(APSEActor actor) {

        /*
         * No need here.
         */
    }

    @Override
    public void removeFlaggedActors() {

        /*
         * No need here.
         */
    }
}