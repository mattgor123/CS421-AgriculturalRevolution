package rev.z.view.overlays;

import java.awt.Color;
import java.awt.Graphics;

import rev.model.core.ModelDelegate;
import rev.model.units.plants.PlantMonster;
import rev.z.view.core.GameControllerStatus;

/**
 * Draws settlements onto the map.
 * 
 *
 * 
 */
public class PlantMonsterOverlay extends MapOverlayMode {
    
    @Override
    public void update() {
        // TODO
    }

    @Override
    public void drawOverlay(Graphics g, ModelDelegate model,
            GameControllerStatus status) {
        for (PlantMonster monster : model.getInfectionManager()
                .getPlantMonsters()) {
            int x = (int) (monster.getX() * this.getTileSize());
            int y = (int) (monster.getY() * this.getTileSize());
            g.setColor(new Color(255, 0, 255));
            g.fillOval(x, y, 10, 10);
        }
    }
}