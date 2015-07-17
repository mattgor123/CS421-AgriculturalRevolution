package rev.model.map.core;

import rev.model.core.BaseModelDelegate;
import rev.model.core.ModelDelegate;
import rev.model.data.TileOccupiedException;
import rev.model.ordnance.CobaltOrdnanceEffect;
import rev.utility.RandomUtility;

/**
 * A land tile.
 * 
 *
 * @author scottmauceri
 * 
 */
public class BaseLandTile extends BaseTile implements LandTile {

    /**
     * Constant tile properties.
     * 
     */
    private final int elevation, baseFertility;

    /**
     * Dynamic tile properties.
     * 
     */
    private int adjustedFertility, contamination;

    /**
     * Initializes the land tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @param elevation
     *            the elevation of the tile.
     * @param baseFertility
     *            the base fertility of the tile.
     */
    public BaseLandTile(int x, int y, int elevation, int baseFertility) {
        super(x, y);
        this.elevation = elevation;
        this.baseFertility = baseFertility;
        this.adjustedFertility = this.baseFertility;
        this.contamination = 0;
    }

    @Override
    public TileDomain getDomain() {
        return TileDomain.TERRA;
    }

    @Override
    public void update(ModelDelegate model) {
        BaseModelDelegate base = (BaseModelDelegate) model;
        if (this.contamination < LandTile.MIN_SPREADABLE_CONTAMINATION) {
            return;
        }
        double prob = this.contamination / (100000.0);
        if (RandomUtility.nextBoolean(prob)) {
            base.getInfectionManager().placeSpore(this.getX() + 1, this.getY(),
                    this.getContamination() / 2);
            base.getInfectionManager().placeSpore(this.getX(), this.getY() + 1,
                    this.getContamination() / 2);
            base.getInfectionManager().placeSpore(this.getX() - 1, this.getY(),
                    this.getContamination() / 2);
            base.getInfectionManager().placeSpore(this.getX(), this.getY() - 1,
                    this.getContamination() / 2);
        }
        if (this.contamination > LandTile.MIN_MONSTER_GENERATING_CONTAMINATION) {
            if (RandomUtility.nextBoolean(0.001)) {
                try {
                    base.getInfectionManager().placePlantMonster(this.getX(),
                            this.getY(), 100);
                } catch (TileOccupiedException e) {

                }

            }
        }
    }

    @Override
    public Integer getElevation() {
        return this.elevation;
    }

    @Override
    public Integer getBaseFertility() {
        return this.baseFertility;
    }

    @Override
    public Integer getAdjustedFertility() {
        return this.adjustedFertility;
    }

    @Override
    public Integer getContamination() {
        return this.contamination;
    }

    @Override
    public void contaminate(Integer severity) {
        this.contamination = Math.min(this.contamination + severity,
                this.getMaximumContamination());
    }

    @Override
    public Integer getMaximumContamination() {
        return Math.min(this.adjustedFertility,
                LandTile.MAXIMUM_CONTAMINATION);
    }

    @Override
    public void respondToEffect(CobaltOrdnanceEffect effect) {
        this.contamination = 0;
        this.adjustedFertility = 0;
    }
}