package rev.z.view.core;

import javax.swing.JPanel;

import rev.z.view.command.CommandView;
import rev.z.view.strategic.StrategicView;

/**
 * Contains the game view items.
 * 
 *
 * 
 */
public class GameView extends JPanel {

    /**
     * The serial version unique identifier.
     * 
     */
    private static final long serialVersionUID = 2679495935830019557L;

    /**
     * The view controller.
     * 
     */
    private final GameViewController viewController;

    /**
     * Initializes the model.
     * 
     * @param model
     */
    public GameView(GameViewController model) {
        this.viewController = model;
        this.setSize(ViewStandard.WINDOW_WIDTH, ViewStandard.WINDOW_HEIGHT);
        this.setLayout(null);
        StrategicView strategicView = new StrategicView(this.viewController);
        strategicView.setLocation(0, 0);
        this.add(strategicView);
        CommandView commandView = new CommandView(this.viewController);
        commandView.setLocation(600, 0);
        this.add(commandView);
        this.setFocusable(true);
    }
}