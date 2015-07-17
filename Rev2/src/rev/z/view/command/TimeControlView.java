package rev.z.view.command;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import rev.z.view.core.GameViewController;
import rev.z.view.modeltime.ModelTimeControl;

/**
 * The time control view.
 * 
 *
 * 
 */
public class TimeControlView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3857039321425468294L;

    private final GameViewController controller;
    

    public TimeControlView(GameViewController controller) {
        this.controller = controller;
        this.setSize(200, 20);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);

        JButton pauseButton = CommandView.getButton("0.0x", 60);
        pauseButton.setLocation(10, 0);
        this.add(pauseButton);
        pauseButton.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent event) {
                this.getController().getTimeControl().pause();
            }
        });
        
        JButton playButton = CommandView.getButton("0.5x", 60);
        playButton.setLocation(70, 0);
        playButton.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent event) {
                this.getController().getTimeControl()
                        .setSpeed(ModelTimeControl.STANDARD_SPEED);
                this.getController().getTimeControl().resume();
                ;
            }
        });
        this.add(playButton);

        JButton fastButton = CommandView.getButton("1.0x", 60);
        fastButton.setLocation(130, 0);
        fastButton.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent event) {
                this.getController().getTimeControl()
                        .setSpeed(ModelTimeControl.FAST_SPEED);
                this.getController().getTimeControl().resume();
                ;
            }
        });
        this.add(fastButton);
    }
    

}