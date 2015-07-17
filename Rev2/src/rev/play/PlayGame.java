package rev.play;

import rev.model.core.BaseModel;
import rev.model.core.Model;
import rev.model.map.core.TileDomain;
import rev.model.map.core.TileMap;
import rev.model.map.generation.TileMapGenerators;
import rev.model.settlement.core.BaseSettlement;
import rev.utility.RandomUtility;
import rev.z.view.core.GameViewController;
import rev.z.view.overlays.ContaminationOverlay;
import rev.z.view.overlays.MapOverlayMode;
import rev.z.view.overlays.OrdnanceEffectOverlay;
import rev.z.view.overlays.PlantMonsterOverlay;
import rev.z.view.overlays.RoadOverlay;
import rev.z.view.overlays.SettlementOverlay;
import rev.z.view.overlays.ShellFireOverlay;

/**
 * Main driver program.
 * 
 *
 * 
 */
public class PlayGame {

    /**
     * Runs the game.
     * 
     * @param args
     *            the program arguments (not applicable).
     */
    public static void main(String[] args) {
        TileMap map = TileMapGenerators.getBaseTileMapGenerator()
                .generateTileMap(60, 60);
        Model model = new BaseModel(map);
        GameViewController viewController = new GameViewController(model);
        boolean flag = false;
        while (!flag) {
            int x = RandomUtility.randomInteger(20, 41);
            int y = RandomUtility.randomInteger(20, 41);
            if (map.getTile(x, y).getDomain() == TileDomain.TERRA) {
                flag = true;
                BaseSettlement settlement = new BaseSettlement("Trinity", x, y,
                        2500);
                model.getSettlementManager().placeSettlement(settlement);
                settlement.getStorageOperation().depositQuantity(8000);
                viewController.getStatus().setActiveSettlement(settlement);
            }
        }
        model.update();
        viewController.initialize();
        viewController.getStatus().setOverlays(
                new MapOverlayMode[] { new ContaminationOverlay(),
                        new OrdnanceEffectOverlay(), new RoadOverlay(),
                        new SettlementOverlay(), new PlantMonsterOverlay(),
                        new ShellFireOverlay() });
        viewController.setVisible(true);
        viewController.getTimeControl().resume();
    }
}