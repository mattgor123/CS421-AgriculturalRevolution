package apse.core;

/**
 * A dynamic entity of the simulation model.
 * 
 *
 * 
 */
public abstract class APSEActor implements Dynamic {

    /**
     * The actor removal flag. The simulation engine will remove flagged actors
     * from the model.
     * 
     */
    private boolean flag = false;

    /**
     * Returns the type of the actor.
     * 
     * @return the actor type.
     */
    public abstract String getType();

    /**
     * Sets the removal flag of the actor.
     * 
     * @param flag
     *            the new actor removal flag value.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Returns whether or not the actor is flagged for removal.
     * 
     * @return the actor removal flag value.
     */
    public boolean isFlagged() {
        return this.flag;
    }

    /**
     * Releases any references to this actor before deletion.
     * 
     */
    public void release() {

    }
}