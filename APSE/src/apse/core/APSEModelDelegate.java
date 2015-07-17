package apse.core;

import java.util.List;

/**
 * A delegate to a simulation model. Used by entities, contexts, or components
 * within the model to modify the model.
 * 
 * 
 *
 * 
 */
public class APSEModelDelegate {

    /**
     * The simulation model.
     * 
     */
    private APSESimulationModel model;

    /**
     * Assigns the the delegate to the specified model.
     * 
     * @param model
     *            the model.
     */
    void assignToModel(APSESimulationModel model) {
        this.model = model;
    }

    /**
     * Inserts an actor into the model.
     * 
     * @param actor
     *            the actor to insert.
     */
    public void insertActor(APSEActor actor) {
        this.model.insertActor(actor);
    }

    /**
     * Returns the set of active actors of the specified type.
     * 
     * @param type
     *            the type.
     * @return the set of actors.
     */
    public List<APSEActor> getActorsOfType(String type) {
        return this.model.getActorsOfType(type);
    }

    /**
     * Returns the set of active actors of the specified type, cast to a
     * subclass.
     * 
     * @param type
     *            the type.
     * @param cast
     *            the cast class.
     * @return the set of actors.
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getActorsOfType(String type, Class<T> cast) {
        return (List<T>) this.getActorsOfType(type);
    }
}