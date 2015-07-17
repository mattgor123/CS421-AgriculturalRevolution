package apse.core;

import java.util.LinkedList;
import java.util.List;

/**
 * A simulation model.
 * 
 *
 * 
 */
public class APSESimulationModel {

    /**
     * A set of actors to insert into the model.
     * 
     */
    private LinkedList<APSEActor> actorInsertionBuffer = new LinkedList<APSEActor>();

    /**
     * The primary actor manager, which facilitates all internal actor updates.
     * 
     */
    private ActorManager actorManager = new ActorManager();

    /**
     * The layer manager, which facilitates interactions between actors within
     * contexts.
     * 
     */
    private LayerManager layerManager = new LayerManager();

    /**
     * The APSE model delegate.
     * 
     */
    private APSEModelDelegate modelDelegate;

    /**
     * Initializes the model with the specified delegate.
     * 
     * @param modelDelegate
     *            the model delegate.
     */
    public APSESimulationModel(APSEModelDelegate modelDelegate) {
        this.modelDelegate = modelDelegate;
        this.modelDelegate.assignToModel(this);
    }

    /**
     * Initializes the model with the default model delegate.
     * 
     */
    public APSESimulationModel() {
        this.setModelDelegate(new APSEModelDelegate());
    }

    /**
     * Sets the delegate of the model.
     * 
     * @param modelDelegate
     *            the new model delegate.
     */
    public void setModelDelegate(APSEModelDelegate modelDelegate) {
        this.modelDelegate = modelDelegate;
        this.modelDelegate.assignToModel(this);
    }

    /**
     * Prepares the model to manage actors of the specified type.
     * 
     * @param type
     *            the actor type.
     */
    public void declareActorType(String type) {
        this.actorManager.declareActorType(type);
    }

    /**
     * Adds an action layer to the model.
     * 
     * @param layer
     *            the layer to add.
     * @param identifier
     *            a unique identifier for the layer.
     */
    public void addActionLayer(APSEActionLayer layer, String identifier) {
        this.layerManager.addLayer(layer, identifier);
    }

    /**
     * Adds a default layer for a specified actor type.
     * 
     * @param type
     *            the actor type.
     * @param layer
     *            the layer identifier.
     */
    public void addDefaultLayerForActorType(String type, String layer) {
        this.layerManager.addDefaultLayerForActorType(type, layer);
    }

    /**
     * Inserts an actor into the model. Actors are not inserted until the next
     * update iteration.
     * 
     * @param actor
     *            the actor to insert.
     */
    public void insertActor(APSEActor actor) {
        this.actorInsertionBuffer.add(actor);
    }

    /**
     * Loads an inserted actor into the model.
     * 
     * @param actor
     *            the actor to load.
     */
    private void loadActorIntoModel(APSEActor actor) {
        this.actorManager.insertActor(actor);
        this.layerManager.insertActor(actor);
    }

    /**
     * Returns all actors of a specified type.
     * 
     * @param type
     *            the actor type.
     * @return the set of actors.
     */
    public List<APSEActor> getActorsOfType(String type) {
        return this.actorManager.getActorsOfType(type);
    }

    /**
     * Returns the delegate to the model.
     * 
     * @return the model delegate.
     */
    public APSEModelDelegate getModelDelegate() {
        return this.modelDelegate;
    }

    /**
     * Updates the simulation model. (1) First, flagged actors are removed. (2)
     * Next, inserted actors are loaded into the model. (3) Next, interactions
     * between actors are facilitated. (4) Finally, the actors are updated.
     */
    public void update() {
        this.layerManager.removeFlaggedActors();
        this.actorManager.removeFlaggedActors();
        while (!this.actorInsertionBuffer.isEmpty()) {
            this.loadActorIntoModel(this.actorInsertionBuffer.poll());
        }
        this.layerManager.update(this.getModelDelegate());
        this.actorManager.update(this.getModelDelegate());
    }

    /**
     * Adds an actor listener to the model.
     * 
     * @param listener
     *            the listener.
     */
    public void addActorListener(APSEActorListener listener) {
        this.actorManager.addActorListener(listener);
    }
}