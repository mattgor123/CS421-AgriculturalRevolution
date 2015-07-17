package apse.core;

/**
 * A context in which actors interact.
 * 
 *
 * 
 */
public abstract class APSEActionLayer implements Dynamic {

	/**
	 * Inserts an actor into the layer.
	 * 
	 * @param actor
	 *            the actor to insert.
	 */
	public abstract void insertActor(APSEActor actor);

	/**
	 * Removes flagged actors from the layer.
	 * 
	 */
	public abstract void removeFlaggedActors();
}