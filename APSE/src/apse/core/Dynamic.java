package apse.core;

/**
 * A dynamic entity, context, or component of a simulation model.
 * 
 *
 * 
 */
interface Dynamic {

	/**
	 * Updates the entity or context.
	 * 
	 * @param delegate
	 *            a delegate through which to interact with the model.
	 */
	public void update(APSEModelDelegate delegate);
}