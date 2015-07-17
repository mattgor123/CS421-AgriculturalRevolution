package rev.z.view.strategic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import rev.z.view.core.GameViewController;
import rev.z.view.core.ViewStandard;
import rev.z.view.effects.ViewEffect;
import rev.z.view.overlays.MapOverlayMode;

/**
 * The strategic view.
 * 
 *
 * 
 */
public class StrategicView extends JPanel implements MouseListener {

    /**
     * The serial version unique identifier.
     * 
     */
    private static final long serialVersionUID = 4582778267231168274L;

    /**
     * The view controller.
     * 
     */
    private final GameViewController controller;

    /**
     * Initializes the strategic view.
     * 
     * @param controller
     *            the view controller.
     */
    public StrategicView(GameViewController controller) {
        this.controller = controller;
        this.setBackground(Color.DARK_GRAY);
        this.setSize(ViewStandard.WINDOW_HEIGHT, ViewStandard.WINDOW_HEIGHT);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics map = g.create(0, 0, 600, 600);
        this.controller.getStatus().getMapDisplayMode()
                .drawMap(map, this.controller.getModel());
        for (MapOverlayMode overlay : this.controller.getStatus().getOverlays()) {
            overlay.drawOverlay(map, this.controller.getModel(),
                    this.controller.getStatus());
        }
        for (ViewEffect effect : this.controller.getEffects()) {
            effect.draw(map, this.controller);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        this.controller.getStatus().getClickMode()
                .execute(this.controller, event);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // TODO Auto-generated method stub

    }
}