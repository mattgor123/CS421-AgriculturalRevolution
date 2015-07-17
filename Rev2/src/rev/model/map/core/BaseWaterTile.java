package rev.model.map.core;

import rev.model.core.ModelDelegate;

/**
 * A water tile.
 * 
 *
 * 
 */
public class BaseWaterTile extends BaseTile {

    /**
     * Initializes the water tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     */
    public BaseWaterTile(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(ModelDelegate model) {

        /*
         * Water tiles do not update independently.
         */
    }

    @Override
    public TileDomain getDomain() {
        return TileDomain.AQUA;
    }

    @Override
    public void contaminate(Integer severity) {

        /*
         * Water cannot be contaminated.
         */
    }

    @Override
    public Integer getElevation() {
        return 0;
    }

    @Override
    public Integer getBaseFertility() {
        return 0;
    }

    @Override
    public Integer getAdjustedFertility() {
        return 0;
    }

    @Override
    public Integer getContamination() {
        return 0;
    }

    @Override
    public Integer getMaximumContamination() {
        return 0;
    }
}