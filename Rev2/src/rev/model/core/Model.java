package rev.model.core;

import rev.model.event.ModelListener;

/**
 * The generic game model.
 * 
 *
 * 
 */
public interface Model extends ModelDelegate {

    /**
     * Updates the model.
     * 
     */
    public void update();

    /**
     * Returns the model delegate.
     * 
     * @return the model delegate.
     */
    public ModelDelegate getModelDelegate();

    /**
     * Adds a listener to the model.
     * 
     * @param listener
     *            the listener to add.
     */
    public void addModelListener(ModelListener listener);

    /**
     * Removes a listener from the model.
     * 
     * @param listener
     *            the listener to remove.
     */
    public void removeModelListener(ModelListener listener);
}