package rev.z.view.overlays;

import java.awt.Color;
import java.awt.Graphics;

import rev.model.core.ModelDelegate;
import rev.model.ordnance.Shell;
import rev.z.view.core.GameControllerStatus;

public class ShellFireOverlay extends MapOverlayMode {

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawOverlay(Graphics g, ModelDelegate model,
            GameControllerStatus status) {
        for (Shell shell : model.getOrdnanceManager().getShells()) {
            int x = (int) (shell.getX() * this.getTileSize());
            int y = (int) (shell.getY() * this.getTileSize());
            int color = shell.getTimeToTarget() * 10;
            if (color > 100) {
                color = 100;
            } else if (color < 1) {
                color = 0;
            }
            g.setColor(new Color(255, 0, 0, color + 100));
            g.fillOval(x, y, 4, 4);
        }
    }
}