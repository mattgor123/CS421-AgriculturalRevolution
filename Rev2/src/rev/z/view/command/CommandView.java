package rev.z.view.command;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import rev.z.view.core.GameViewController;
import rev.z.view.core.ViewStandard;

public class CommandView extends JPanel {

    /**
     * The serial version unique identifier.
     * 
     */
    private static final long serialVersionUID = 4582778267231168274L;

    private final GameViewController controller;

    /**
     * Initializes the command view.
     * 
     * @param controller
     *            the controller.
     */
    public CommandView(GameViewController controller) {
        this.controller = controller;
        this.setBackground(Color.BLACK);
        this.setSize(200, ViewStandard.WINDOW_HEIGHT);
        this.setLayout(null);
        GameSpeedView gameSpeedView = new GameSpeedView(this.controller);
        gameSpeedView.setLocation(0, 0);
        this.add(gameSpeedView);
        
        
        GameStatusView gameTimeView = new GameStatusView(this.controller);
        gameTimeView.setLocation(0, 15);
        this.add(gameTimeView);

        TimeControlView timeControlView = new TimeControlView(this.controller);
        timeControlView.setLocation(0,
                gameTimeView.getY() + gameTimeView.getHeight() + 12);
        this.add(timeControlView);

        ActiveSettlementView activeSettlementView = new ActiveSettlementView(
                this.controller);
        activeSettlementView.setLocation(0, timeControlView.getY()
                + timeControlView.getHeight() + 10);
        this.add(activeSettlementView);

        InteractionModeView interactionModeView = new InteractionModeView(
                this.controller);
        interactionModeView.setLocation(0, activeSettlementView.getY()
                + activeSettlementView.getHeight() + 10);
        this.add(interactionModeView);
    }

    /**
     * Returns a j button formatted for the command view.
     * 
     * @param text
     *            the button text.
     * @param width
     *            the button width.
     * @return the button object.
     */
    public static JButton getButton(String text, int width) {
        JButton button = new JButton();
        button.setSize(width, 20);
        button.setText(text);
        button.setFont(new Font("Menlo", Font.PLAIN, 10));
        return button;
    }
}