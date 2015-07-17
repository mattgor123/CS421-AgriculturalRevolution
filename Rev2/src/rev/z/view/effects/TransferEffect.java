package rev.z.view.effects;

import java.awt.Color;
import java.awt.Graphics;

import rev.model.settlement.core.Settlement;
import rev.z.view.core.GameViewController;
import apse.core.APSEModelDelegate;

/**
 * An effect representing the transfer of grain or people between settlements.
 * 
 *
 * 
 */
public class TransferEffect extends ViewEffect {

    private int counter = 50;

    /**
     * The sender.
     * 
     */
    private Settlement origin;

    /**
     * The destination.
     * 
     */
    private Settlement destination;

    public TransferEffect(Settlement origin, Settlement destination) {
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void update(APSEModelDelegate delegate) {
        this.counter--;
        if (this.counter < 10) {
            this.setFlag(true);
        }
    }

    @Override
    public void draw(Graphics g, GameViewController controller) {
        int size = controller.getStatus().getTileSize();
        g.setColor(new Color(0, 255, 255, counter * 4));
        g.drawLine(origin.getX() * size, origin.getY() * size,
                destination.getX() * size, destination.getY() * size);
    }

}