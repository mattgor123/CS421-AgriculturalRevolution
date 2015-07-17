package rev.model.map.core;

import rev.model.core.BaseModelDelegate;
import rev.model.core.ModelDelegate;
import apse.core.APSEActionLayer;
import apse.core.APSEActor;
import apse.core.APSEModelDelegate;

/**
 * TileMap represented as an action layer to prevent it from being flagged. This
 * particular layer simply updates the tile map.
 * 
 *
 * 
 */
public class BaseTileMapUpdateLayer extends APSEActionLayer {

    @Override
    public void update(APSEModelDelegate delegate) {
        BaseModelDelegate model = (BaseModelDelegate) delegate;
        for (Tile tile : model.getTileMap()) {
            BaseTile baseTile = (BaseTile) tile;
            baseTile.update((ModelDelegate) delegate);
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