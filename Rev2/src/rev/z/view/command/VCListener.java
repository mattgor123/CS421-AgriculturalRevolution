package rev.z.view.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rev.z.view.core.GameViewController;

/**
 * An action listener that can be connected to the view controller.
 */
public class VCListener implements ActionListener {

    private GameViewController controller;

    /**
     * Initializes the listener.
     * 
     * @param controller
     *            the view controller.
     */
    public VCListener(GameViewController controller) {
        this.controller = controller;
    }

    /**
     * Returns the view controller of the listener.
     * 
     * @return the view controller.
     */
    public GameViewController getController() {
        return this.controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}