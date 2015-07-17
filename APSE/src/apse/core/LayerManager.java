package apse.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Facilitates interactions between actors within contexts.
 * 
 *
 * 
 */
class LayerManager implements Dynamic {

    /**
     * The set of layers.
     * 
     */
    private Map<String, APSEActionLayer> layers = new HashMap<String, APSEActionLayer>();

    /**
     * The mapping of actor types to default layers.
     * 
     */
    private Map<String, List<APSEActionLayer>> actorTypeLayerAssignments = new HashMap<String, List<APSEActionLayer>>();

    /**
     * The set of layer identifiers.
     * 
     */
    private List<String> layerIdentifiers = new ArrayList<String>();

    /**
     * Adds a layer to the manager.
     * 
     * @param layer
     *            the layer to add.
     * @param identifier
     *            a unique identifier for the layer.
     */
    public void addLayer(APSEActionLayer layer, String identifier) {
        this.layers.put(identifier, layer);
        this.layerIdentifiers.add(identifier);
    }

    /**
     * Returns the specified layer.
     * 
     * @param layer
     *            the layer identifier.
     * @return the layer.
     */
    public APSEActionLayer getLayer(String layer) {
        return this.layers.get(layer);
    }

    /**
     * Adds a default layer for a particular actor type.
     * 
     * @param type
     *            the actor type.
     * @param layer
     *            the layer identifier.
     */
    public void addDefaultLayerForActorType(String type, String layer) {
        if (!this.actorTypeLayerAssignments.containsKey(type)) {
            this.actorTypeLayerAssignments.put(type,
                    new ArrayList<APSEActionLayer>());
        }
        this.actorTypeLayerAssignments.get(type).add(this.getLayer(layer));
    }

    @Override
    public void update(APSEModelDelegate delegate) {
        for (String identifier : this.layerIdentifiers) {
            APSEActionLayer layer = this.getLayer(identifier);
            layer.update(delegate);
        }
    }

    /**
     * Returns the set of default layers for the specified actor type.
     * 
     * @param type
     *            the actor type.
     * @return the set of default layers.
     */
    public List<APSEActionLayer> getDefaultLayersForAssetType(String type) {
        if (this.actorTypeLayerAssignments.containsKey(type)) {
            return this.actorTypeLayerAssignments.get(type);
        }
        return new ArrayList<APSEActionLayer>();
    }

    /**
     * Inserts an actor into the appropriate layers.
     * 
     * @param actor
     *            the actor to insert.
     */
    public void insertActor(APSEActor actor) {
        for (APSEActionLayer layer : this.getDefaultLayersForAssetType(actor
                .getType())) {
            layer.insertActor(actor);
        }
    }

    /**
     * Removes flagged actors from the layers.
     * 
     */
    public void removeFlaggedActors() {
        for (String identifier : this.layerIdentifiers) {
            APSEActionLayer layer = this.getLayer(identifier);
            layer.removeFlaggedActors();
        }
    }
}