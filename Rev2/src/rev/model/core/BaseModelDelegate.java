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
import apse.core.APSEModelDelegate;

/**
 * The customized model delegate that provides non-APSE model access to apse
 * actor-implemented model items.
 * 
 *
 * 
 */
public class BaseModelDelegate extends APSEModelDelegate implements ModelDelegate {

    /**
     * The model.
     * 
     */
    public Model model;

    /**
     * Initializes the delegate.
     * 
     * @param model
     */
    public BaseModelDelegate(Model model) {
        this.model = model;
    }

    @Override
    public TileMap getTileMap() {
        return this.model.getTileMap();
    }

    @Override
    public SettlementManager getSettlementManager() {
        return this.model.getSettlementManager();
    }

    @Override
    public TreasuryManager getTreasuryManager() {
        return this.model.getTreasuryManager();
    }

    @Override
    public WeatherManager getWeatherManager() {
        return this.model.getWeatherManager();
    }

    @Override
    public RoadNetworkManager getRoadNetworkManager() {
        return this.model.getRoadNetworkManager();
    }

    @Override
    public UnitsManager getUnitsManager() {
        return this.model.getUnitsManager();
    }

    @Override
    public InfectionManager getInfectionManager() {
        return this.model.getInfectionManager();
    }

    @Override
    public Long getElapsedIterations() {
        return this.model.getElapsedIterations();
    }

    @Override
    public PathDeveloper getPathDeveloper() {
        return this.model.getPathDeveloper();
    }

    @Override
    public OrdnanceManager getOrdnanceManager() {
        return this.model.getOrdnanceManager();
    }

    @Override
    public void ackEvent(ModelEvent event) {
        this.model.ackEvent(event);
    }

    @Override
    public StatsManager getStatsManager() {
        return this.model.getStatsManager();
    }
}