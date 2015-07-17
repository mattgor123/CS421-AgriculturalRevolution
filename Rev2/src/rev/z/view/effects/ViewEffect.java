package rev.z.view.effects;

import java.awt.Graphics;

import rev.z.view.core.GameViewController;
import apse.core.APSEActor;

/**
 * A visual game effect.
 * 
 *
 * 
 */
public abstract class ViewEffect extends APSEActor {

    public static final String ACTOR_TYPE = "fx";

    @Override
    public String getType() {
        return ACTOR_TYPE;
    }

    /**
     * Draws the visual effect to the strategic map.
     * 
     * @param g
     *            the graphics context.
     * @param controller
     *            the view controller.
     */
    public abstract void draw(Graphics g, GameViewController controller);
}