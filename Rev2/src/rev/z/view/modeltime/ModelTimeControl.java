package rev.z.view.modeltime;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import rev.model.core.Model;

/**
 * Controls the model update rate.
 * 
 *
 * 
 */
public class ModelTimeControl implements ActionListener {

    /**
     * The standard play speed.
     * 
     */
    public static final int STANDARD_SPEED = 100;

    /**
     * The accelerated play speed.
     * 
     */
    public static final int FAST_SPEED = 20;

    /**
     * The model to control.
     * 
     */
    private Model model;

    /**
     * The timer.
     * 
     */
    private Timer timer;

    /**
     * Initializes the timer.
     * 
     * @param model
     */
    public ModelTimeControl(Model model) {
        this.model = model;
        this.timer = new Timer(STANDARD_SPEED, this);
    }

    /**
     * Pauses the model.
     * 
     */
    public void pause() {
        this.timer.stop();
    }

    /**
     * Resume the model.
     * 
     */
    public void resume() {
        this.timer.start();
    }

    /**
     * Sets the update frequency.
     * 
     * @param frequency
     *            the update frequency.
     */
    public void setSpeed(int frequency) {
        this.timer.stop();
        this.timer = new Timer(frequency, this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.model.update();
    }
}