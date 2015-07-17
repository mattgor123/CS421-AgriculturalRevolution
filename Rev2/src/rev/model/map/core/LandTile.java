package rev.model.map.core;

import rev.model.ordnance.AffectedByCobaltOrdnance;

/**
 * A land tile.
 * 
 *
 * 
 */
public interface LandTile extends Tile, AffectedByCobaltOrdnance {

    /**
     * The maximum elevation of a tile.
     * 
     */
    public static final int MAXIMUM_ELEVATION = 100;

    /**
     * The maximum contamination of a tile.
     * 
     */
    public static final int MAXIMUM_CONTAMINATION = 100;

    /**
     * The minimum contamination required to produce spores.
     * 
     */
    public static final int MIN_SPREADABLE_CONTAMINATION = 20;

    /**
     * The minimum contamination required to produce plant monsters.
     * 
     */
    public static final int MIN_MONSTER_GENERATING_CONTAMINATION = 50;
}