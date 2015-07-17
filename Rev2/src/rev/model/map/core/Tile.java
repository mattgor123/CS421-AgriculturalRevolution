package rev.model.map.core;

import rev.model.properties.Positioned;

/**
 * A tile.
 * 
 *
 * 
 */
public interface Tile extends Positioned {

    /**
     * Returns the domain of the tile.
     * 
     * @return the tile domain.
     */
    public TileDomain getDomain();

    /**
     * Returns the elevation of the tile.
     * 
     * @return the elevation.
     */
    public Integer getElevation();

    /**
     * Returns the base fertility (i.e. potential output) of the tile. This is
     * the initial fertility of the land tile, computed as a function of
     * elevation and proximity to fresh water, that remains constant.
     * 
     * @return the base fertility, in bushels per turn.
     */
    public Integer getBaseFertility();

    /**
     * Returns the adjusted fertility (i.e. potential output) of the tile. This
     * is the current fertility of the land tile, which may be lower than the
     * base fertility as a result of cobalt bombing or other effect.
     * 
     * @return the adjusted fertility, in bushels per turn.
     */
    public Integer getAdjustedFertility();

    /**
     * Returns the amount of contamination on the tile.
     * 
     * @return the amount of contamination.
     */
    public Integer getContamination();

    /**
     * Returns the maximum amount to which the tile can be contaminated.
     * 
     * @return the maximum amount.
     */
    public Integer getMaximumContamination();
    
    /**
     * Contaminates the tile.
     * 
     * @param severity
     *            the severity of contamination.
     */
    public void contaminate(Integer severity);
}