package rev.z.view.core;

import rev.model.settlement.core.Settlement;
import rev.z.clickmodes.ClickMode;
import rev.z.clickmodes.SetActiveSettlementMode;
import rev.z.view.maps.MapDisplayMode;
import rev.z.view.maps.TerrainDisplayMode;
import rev.z.view.overlays.MapOverlayMode;

/**
 * Maintains data about the game view status.
 * 
 *
 * 
 */
public class GameControllerStatus {

    /**
     * Returns the size of a tile.
     * 
     * @return the size of a tile.
     */
    public Integer getTileSize() {
        return 10;
    }

    /**
     * The current map display mode.
     * 
     */
    private MapDisplayMode mapDisplayMode = new TerrainDisplayMode();

    /**
     * The current map overlays.
     * 
     */
    private MapOverlayMode[] overlays = new MapOverlayMode[0];

    /**
     * The current clickmode.
     * 
     */
    private ClickMode clickMode = new SetActiveSettlementMode();

    /**
     * The active settlement.
     * 
     */
    private Settlement activeSettlement = null;

    /**
     * Sets the map display mode.
     * 
     * @param mode
     *            the map display mode.
     */
    public void setMapDisplayMode(MapDisplayMode mode) {
        this.mapDisplayMode = mode;
    }

    /**
     * Returns the map display mode.
     * 
     * @return the map display mode.
     */
    public MapDisplayMode getMapDisplayMode() {
        return this.mapDisplayMode;
    }

    /**
     * Returns the overlays over the map.
     * 
     * @return the overlays over the map.
     */
    public MapOverlayMode[] getOverlays() {
        return this.overlays;
    }

    /**
     * Sets the overlays over the map.
     * 
     * @param overlays
     *            the overlays.
     */
    public void setOverlays(MapOverlayMode[] overlays) {
        this.overlays = overlays;
    }

    /**
     * Updates the view status components.
     * 
     */
    public void update() {
        this.mapDisplayMode.update();
        for (MapOverlayMode mode : this.getOverlays()) {
            mode.update();
        }
    }

    /**
     * Returns the active settlement.
     * 
     * @return the active settlement.
     */
    public Settlement getActiveSettlement() {
        return this.activeSettlement;
    }

    /**
     * Sets the active settlement.
     * 
     * @param settlement
     *            the new active settlement.
     */
    public void setActiveSettlement(Settlement settlement) {
        this.activeSettlement = settlement;
    }

    /**
     * Returns the current click mode.
     * 
     * @return the click mode.
     */
    public ClickMode getClickMode() {
        return this.clickMode;
    }

    /**
     * Sets the current click mode.
     * 
     * @param mode
     *            the new click mode.
     */
    public void setClickMode(ClickMode mode) {
        this.clickMode = mode;
    }
}