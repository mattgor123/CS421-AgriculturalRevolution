package apse.core;

/**
 * Listener for apse actor events.
 * 
 *
 * 
 */
public interface APSEActorListener {
    
    /**
     * Called when an actor is removed.
     * 
     * @param actor
     *            the removed actor.
     */
    public void actorRemoved(APSEActor actor);

    /**
     * Returns the observed type of actor.
     * 
     * @return the actor type.
     */
    public String getObservedActorType();
}