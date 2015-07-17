package rev.z.view.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

import apse.core.APSESimulationModel;
import rev.model.core.Model;
import rev.model.core.ModelDelegate;
import rev.z.view.effects.ViewEffect;
import rev.z.view.modeltime.ModelTimeControl;

public class GameViewController extends JFrame implements ActionListener {

    /**
     * The serial version unique identifier.
     * 
     */
    private static final long serialVersionUID = 9083083714388958261L;

    /**
     * The game model.
     * 
     */
    private final Model model;

    /**
     * The view controller animation rate.
     * 
     */
    private final Timer frameTimer = new Timer(20, this);

    /**
     * The model update rate.
     * 
     */
    private final ModelTimeControl modelTimer;

    /**
     * The view controller state.
     * 
     */
    private final GameControllerStatus state = new GameControllerStatus();

    /**
     * The view.
     * 
     */
    private final GameView view;

    /**
     * An simulation model to manage game visual effects.
     * 
     */
    private final APSESimulationModel effects = new APSESimulationModel();

    /**
     * Creates a view controller for the specified model.
     * 
     * @param model
     *            the model.
     */
    public GameViewController(Model model) {
        this.model = model;
        this.modelTimer = new ModelTimeControl(this.model);
        this.effects.declareActorType(ViewEffect.ACTOR_TYPE);
        this.setResizable(false);
        this.setSize(ViewStandard.WINDOW_WIDTH, ViewStandard.WINDOW_HEIGHT);
        this.setLayout(null);
        this.view = new GameView(this);
        this.add(this.view);
    }

    /**
     * Returns a delegate to the model of the view controller.
     * 
     * @return the model delegate.
     */
    public ModelDelegate getModel() {
        return this.model.getModelDelegate();
    }

    /**
     * Returns the model time control.
     * 
     * @return the model time control.
     */
    public ModelTimeControl getTimeControl() {
        return this.modelTimer;
    }

    /**
     * Returns the view controller state.
     * 
     * @return the view controller state.
     */
    public GameControllerStatus getStatus() {
        return this.state;
    }

    /**
     * Initializes the view controller.
     * 
     */
    public void initialize() {
        this.frameTimer.start();
    }

    /**
     * Updates the view.
     * 
     */
    private void update() {
        this.repaint();
        this.getStatus().update();
        this.effects.update();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }

    /**
     * Adds a visual effect.
     * 
     * @param effect
     */
    public void addEffect(ViewEffect effect) {
        this.effects.insertActor(effect);
    }

    /**
     * Returns the current visual effects.
     * 
     * @return the current visual effects.
     */
    public List<ViewEffect> getEffects() {
        return this.effects.getModelDelegate().getActorsOfType(
                ViewEffect.ACTOR_TYPE, ViewEffect.class);
    }
}