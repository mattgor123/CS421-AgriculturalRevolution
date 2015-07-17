package rev.z.view.overlays;

import java.awt.Color;
import java.awt.Graphics;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;
import rev.z.view.core.GameControllerStatus;

/**
 * Draws settlements onto the map.
 * 
 *
 * 
 */
public class SettlementOverlay extends MapOverlayMode {

    /**
     * This update counter causes the active settlement to glow.
     * 
     */
    private int counter = 150, direction = 5;
    
    private int on = 0;

    @Override
    public void update() {
        this.counter += this.direction;
        if (this.counter > 200) {
            this.on = 1;
        } else {
            this.on = 0;
        }
        if (this.counter > 255 && this.direction > 0) {
            this.counter = 255;
            this.direction = -5;
        } else if (this.counter < 150 && this.direction < 0) {
            this.counter = 150;
            this.direction = 5;
        }
    }

    @Override
    public void drawOverlay(Graphics g, ModelDelegate model,
            GameControllerStatus status) {
        for (Settlement settlement : model.getSettlementManager()
                .getSettlements()) {
            int x = settlement.getX() * this.getTileSize();
            int y = settlement.getY() * this.getTileSize();
            g.setColor(getColorForSpecialization(settlement));
            if (settlement.isStarving()) {
                g.setColor(new Color(255, 0, 0, 155 + 100 * on));
            }
            if (settlement.getPopulation() == 0) {
                g.setColor(Color.RED);
            }
            if (settlement.equals(status.getActiveSettlement())) {
                g.setColor(new Color(255, 255, 255, this.counter));
            }
            g.fillRect(x + 2, y + 2, this.getTileSize() - 3,
                    this.getTileSize() - 3);
        }
    }

    /**
     * Returns the specialization color for the specified settlement.
     * 
     * @param settlement
     *            the settlement.
     * @return the specialization color.
     */
    private static Color getColorForSpecialization(Settlement settlement) {
        switch (settlement.getSpecialization().getType()) {
        case CULTIVATION:
            return new Color(255, 200, 120);
        case DEFAULT:
            return new Color(120, 120, 120);
        case SCIENCE:
            return new Color(120, 120, 255);
        }
        return Color.WHITE;
    }
}