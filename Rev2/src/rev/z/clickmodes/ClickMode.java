package rev.z.clickmodes;

import java.awt.event.MouseEvent;

import rev.z.view.core.GameViewController;

/**
 * Defines a mouse event response.
 * 
 *
 * 
 */
public interface ClickMode {

    /**
     * Returns a mode title.
     * 
     * @return the mode name.
     */
    public String getName();

    /**
     * Returns a description of the mode.
     * 
     * @return the mode description.
     */
    public String getDescription();

    /**
     * Executes the response.
     * 
     * @param controller
     *            the view controller.
     * @param event
     *            the mouse event.
     */
    public void execute(GameViewController controller, MouseEvent event);
}