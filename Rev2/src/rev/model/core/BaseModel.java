package rev.model.core;

import java.util.ArrayList;
import java.util.List;

import apse.core.APSESimulationModel;
import rev.model.event.ModelEvent;
import rev.model.event.ModelListener;
import rev.model.map.core.TileMap;
import rev.model.map.core.BaseTileMapUpdateLayer;
import rev.model.map.interactors.CobaltEffectsLayer;
import rev.model.map.interactors.PlantMonsterEatsSettlement;
import rev.model.map.interactors.SporeInfectsLandLayer;
import rev.model.ordnance.BaseCobaltOrdnanceEffect;
import rev.model.ordnance.BaseExplosiveOrdnanceEffect;
import rev.model.ordnance.BaseOrdnanceManager;
import rev.model.ordnance.BaseShell;
import rev.model.ordnance.OrdnanceManager;
import rev.model.paths.BasePathDeveloper;
import rev.model.paths.PathDeveloper;
import rev.model.player.BaseStatsManager;
import rev.model.player.BaseTreasuryManager;
import rev.model.player.StatsManager;
import rev.model.player.TreasuryManager;
import rev.model.roads.BaseRoadManager;
import rev.model.roads.RoadNetworkManager;
import rev.model.settlement.core.BaseSettlement;
import rev.model.settlement.core.BaseSettlementManager;
import rev.model.settlement.core.SettlementManager;
import rev.model.units.core.BaseUnitsManager;
import rev.model.units.core.UnitsManager;
import rev.model.units.plants.BaseInfectionManager;
import rev.model.units.plants.BasePlantMonster;
import rev.model.units.plants.BaseSpore;
import rev.model.units.plants.InfectionManager;
import rev.model.weather.BaseWeatherManager;
import rev.model.weather.WeatherManager;

/**
 * The apse game model.
 * 
 *
 * 
 */
public class BaseModel implements Model {

    /**
     * The model delegate.
     * 
     */
    private final ModelDelegate delegate = new BaseModelDelegate(this);

    /**
     * The tile map.
     * 
     */
    private final TileMap map;

    /**
     * The settlement manager.
     * 
     */
    private final SettlementManager settlementManager;

    /**
     * The treasury manager.
     * 
     */
    private final TreasuryManager treasuryManager = new BaseTreasuryManager(
            5000);

    /**
     * The weather manager.
     * 
     */
    private final WeatherManager weatherManager = new BaseWeatherManager();

    /**
     * The road network manager.
     * 
     */
    private final RoadNetworkManager roadNetworkManager;

    /**
     * The units manager.
     * 
     */
    private final UnitsManager unitsManager;

    /**
     * The infection manager.
     * 
     */
    private final InfectionManager infectionManager;

    /**
     * The path developer.
     * 
     */
    private final PathDeveloper pathDeveloper;

    /**
     * The ordnance manager.
     * 
     */
    private final OrdnanceManager ordnanceManager;

    /**
     * The stats manager.
     * 
     */
    private final StatsManager statsManager;

    /**
     * The model listeners.
     * 
     */
    private final List<ModelListener> listeners = new ArrayList<ModelListener>();

    /**
     * The underlying apse architecture.
     * 
     */
    private final APSESimulationModel apse;

    /**
     * The number of elapsed iterations.
     * 
     */
    private long elapsedIterations = 0;

    /**
     * Initializes the model.
     * 
     * @param map
     *            the model tile map.
     */
    public BaseModel(TileMap map) {
        this.map = map;
        this.apse = new APSESimulationModel((BaseModelDelegate) this.delegate);
        this.settlementManager = new BaseSettlementManager(this.map.getWidth(),
                this.map.getHeight(), this.getModelDelegate());
        this.roadNetworkManager = new BaseRoadManager(
                (BaseModelDelegate) this.delegate);
        this.unitsManager = new BaseUnitsManager(this.getModelDelegate());
        this.infectionManager = new BaseInfectionManager(
                (BaseModelDelegate) this.getModelDelegate());
        this.ordnanceManager = new BaseOrdnanceManager(
                (BaseModelDelegate) this.getModelDelegate());
        this.pathDeveloper = new BasePathDeveloper(this.map);
        this.statsManager = new BaseStatsManager(
                (BaseModelDelegate) this.getModelDelegate());
        this.declareActors(this.apse);
        this.addActionLayers(this.apse);
        this.assignActorsToActionLayers(this.apse);
        this.declareListeners(this.apse);
    }

    /**
     * Declares the actors in the apse model.
     * 
     * @param apse
     *            the apse model.
     */
    private void declareActors(APSESimulationModel apse) {
        apse.declareActorType(BaseSettlement.ACTOR_TYPE);
        apse.declareActorType(BaseSpore.ACTOR_TYPE);
        apse.declareActorType(BaseShell.ACTOR_TYPE);
        apse.declareActorType(BaseExplosiveOrdnanceEffect.ACTOR_TYPE);
        apse.declareActorType(BaseCobaltOrdnanceEffect.ACTOR_TYPE);
        apse.declareActorType(BasePlantMonster.ACTOR_TYPE);
    }

    /**
     * Adds the action layers to the apse model.
     * 
     * @param apse
     *            the apse model.
     */
    private void addActionLayers(APSESimulationModel apse) {
        this.apse.addActionLayer(new BaseTileMapUpdateLayer(),
                BaseLayerDeclarations.TILE_MAP_UPDATE_LAYER);
        this.apse.addActionLayer(new SporeInfectsLandLayer(),
                BaseLayerDeclarations.SPORE_INFECTS_LAND);
        this.apse.addActionLayer(new CobaltEffectsLayer(),
                BaseLayerDeclarations.COBALT_EFFECTS);
        this.apse.addActionLayer(new PlantMonsterEatsSettlement(),
                BaseLayerDeclarations.HUNGRY_MONSTERS);
    }

    /**
     * Assigns actors to the apse mode.
     * 
     * @param apse
     *            the apse model.
     */
    private void assignActorsToActionLayers(APSESimulationModel apse) {

    }

    /**
     * Declares listeners of the model.
     * 
     * @param apse
     *            the model.
     */
    private void declareListeners(APSESimulationModel apse) {
        this.apse
                .addActorListener((BaseSettlementManager) this.settlementManager);
    }

    @Override
    public TileMap getTileMap() {
        return this.map;
    }

    @Override
    public void update() {
        this.apse.update();
        this.elapsedIterations++;
    }

    @Override
    public ModelDelegate getModelDelegate() {
        return this.delegate;
    }

    @Override
    public SettlementManager getSettlementManager() {
        return this.settlementManager;
    }

    @Override
    public TreasuryManager getTreasuryManager() {
        return this.treasuryManager;
    }

    @Override
    public WeatherManager getWeatherManager() {
        return this.weatherManager;
    }

    @Override
    public RoadNetworkManager getRoadNetworkManager() {
        return this.roadNetworkManager;
    }

    @Override
    public UnitsManager getUnitsManager() {
        return this.unitsManager;
    }

    @Override
    public InfectionManager getInfectionManager() {
        return this.infectionManager;
    }

    @Override
    public Long getElapsedIterations() {
        return this.elapsedIterations;
    }

    @Override
    public PathDeveloper getPathDeveloper() {
        return this.pathDeveloper;
    }

    @Override
    public OrdnanceManager getOrdnanceManager() {
        return this.ordnanceManager;
    }

    @Override
    public void ackEvent(ModelEvent event) {
        for (ModelListener listener : this.listeners) {
            listener.respondToEvent(event);
        }
    }

    @Override
    public void addModelListener(ModelListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeModelListener(ModelListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public StatsManager getStatsManager() {
        return this.statsManager;
    }
}