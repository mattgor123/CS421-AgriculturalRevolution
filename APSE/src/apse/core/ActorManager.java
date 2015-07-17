package apse.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Facilitates internal actor updates.
 * 
 *
 * 
 */
class ActorManager implements Dynamic {

    /**
     * The set of actors.
     * 
     */
    private Map<String, List<APSEActor>> actors = new HashMap<String, List<APSEActor>>();

    /**
     * The set of actor listeners.
     * 
     */
    private Map<String, List<APSEActorListener>> listeners = new HashMap<String, List<APSEActorListener>>();

    /**
     * The set of actor types.
     * 
     */
    private List<String> declarations = new ArrayList<String>();

    /**
     * Declares an actor type.
     * 
     * @param type
     *            the actor type.
     */
    public void declareActorType(String type) {
        this.declarations.add(type);
        this.actors.put(type, new ArrayList<APSEActor>());
        this.listeners.put(type, new ArrayList<APSEActorListener>());
    }

    /**
     * Inserts an actor into the manager.
     * 
     * @param actor
     *            the actor to insert.
     */
    public void insertActor(APSEActor actor) {
        this.actors.get(actor.getType()).add(actor);
    }

    @Override
    public void update(APSEModelDelegate delegate) {
        for (String update : this.declarations) {
            List<APSEActor> actors = this.actors.get(update);
            for (APSEActor actor : actors) {
                actor.update(delegate);
            }
        }
    }

    /**
     * Removes and releases flagged actors from the manager.
     * 
     */
    public void removeFlaggedActors() {
        for (String declaration : this.declarations) {
            List<APSEActor> actors = this.actors.get(declaration);
            for (int i = 0; i < actors.size(); i++) {
                APSEActor actor = actors.get(i);
                if (actor.isFlagged()) {
                    for (APSEActorListener listener : this.listeners.get(actor.getType())) {
                        listener.actorRemoved(actor);
                    }
                    actors.remove(i);
                    i--;
                    actor.release();
                }
            }
        }
    }

    /**
     * Returns all actors of the specified type.
     * 
     * @param type
     *            the actor type.
     * @return the set of actors.
     */
    public List<APSEActor> getActorsOfType(String type) {
        return this.actors.get(type);
    }

    /**
     * Adds a listener to the actor manager.
     * 
     * @param listener
     *            the listener to add.
     */
    public void addActorListener(APSEActorListener listener) {
        this.listeners.get(listener.getObservedActorType()).add(listener);
    }
}