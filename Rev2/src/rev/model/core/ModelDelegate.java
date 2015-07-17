package rev.model.core;

import rev.model.event.ModelEvent;
import rev.model.map.core.TileMap;
import rev.model.ordnance.OrdnanceManager;
import rev.model.paths.PathDeveloper;
import rev.model.player.StatsManager;
import rev.model.player.TreasuryManager;
import rev.model.roads.RoadNetworkManager;
import rev.model.settlement.core.SettlementManager;
import rev.model.units.core.UnitsManager;
import rev.model.units.plants.InfectionManager;
import rev.model.weather.WeatherManager;

/**
 * Provides restricted access to the model, for use by simulated entities within
 * the model.
 * 
 *
 * 
 */
public interface ModelDelegate {

    /**
     * Returns the tile map of the model.
     * 
     * @return the tile map.
     */
    public TileMap getTileMap();

    /**
     * Returns the settlement manager.
     * 
     * @return the settlement manager.
     */
    public SettlementManager getSettlementManager();

    /**
     * Returns the treasury manager.
     * 
     * @return the treasury manager.
     */
    public TreasuryManager getTreasuryManager();

    /**
     * Returns the weather manager.
     * 
     * @return the weather manager.
     */
    public WeatherManager getWeatherManager();

    /**
     * Returns the road network manager.
     * 
     * @return the road network manager.
     */
    public RoadNetworkManager getRoadNetworkManager();

    /**
     * Returns the units manager.
     * 
     * @return the units manager.
     */
    public UnitsManager getUnitsManager();

    /**
     * Returns the infection manager.
     * 
     * @return the infection manager.
     */
    public InfectionManager getInfectionManager();

    /**
     * Returns the ordnance manager.
     * 
     * @return the ordnance manager.
     */
    public OrdnanceManager getOrdnanceManager();

    /**
     * Returns the path developer.
     * 
     * @return the path developer.
     */
    public PathDeveloper getPathDeveloper();

    /**
     * Returns the number of elapsed iterations.
     * 
     * @return the number of elapsed iterations.
     */
    public Long getElapsedIterations();

    /**
     * Returns the model stats manager.
     * 
     * @return the stats manager.
     */
    public StatsManager getStatsManager();

    /**
     * Publishes a listenable model event.
     * 
     * @param event
     *            the event.
     */
    public void ackEvent(ModelEvent event);
}