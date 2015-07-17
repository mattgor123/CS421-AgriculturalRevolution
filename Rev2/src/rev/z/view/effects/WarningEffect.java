package rev.z.view.effects;

import java.awt.Color;
import java.awt.Graphics;

import rev.utility.Coordinate;
import rev.z.view.core.GameViewController;
import apse.core.APSEModelDelegate;

/**
 * A concentric circle that brings attention to something on the map.
 * 
 *
 * 
 */
public class WarningEffect extends ViewEffect {

    /**
     * The size of the circle.
     * 
     */
    private int size = 80;

    /**
     * The circle center.
     * 
     */
    private Coordinate<Integer> center;

    /**
     * Initializes the effect.
     * 
     * @param center
     *            the effect center.
     */
    public WarningEffect(Coordinate<Integer> center) {
        this.center = center;
    }

    @Override
    public void update(APSEModelDelegate delegate) {
        size--;
        if (size < 20) {
            this.setFlag(true);
        }
    }

    @Override
    public void draw(Graphics g, GameViewController controller) {
        int x = center.getX() - this.size / 2;
        int y = center.getY() - this.size / 2;
        g.setColor(new Color(255, 120, 0, size));
        g.drawOval(x, y, size, size);
    }

}