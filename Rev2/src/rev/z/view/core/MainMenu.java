package rev.z.view.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rev.model.core.BaseModel;
import rev.model.core.Model;
import rev.model.map.core.TileDomain;
import rev.model.map.core.TileMap;
import rev.model.map.generation.TileMapGenerators;
import rev.model.settlement.core.BaseSettlement;
import rev.utility.RandomUtility;
import rev.z.view.overlays.ContaminationOverlay;
import rev.z.view.overlays.MapOverlayMode;
import rev.z.view.overlays.OrdnanceEffectOverlay;
import rev.z.view.overlays.PlantMonsterOverlay;
import rev.z.view.overlays.RoadOverlay;
import rev.z.view.overlays.SettlementOverlay;
import rev.z.view.overlays.ShellFireOverlay;








public class MainMenu implements MouseListener {

private JButton PlayButton = new JButton("New Game");
private JButton QuitButton = new JButton("Quit");
private JPanel ButtonPanel = new JPanel();
private JFrame f = new JFrame("Meloncholia");
	
	
	MainMenu() {
		f.addMouseListener(this);
		
		PlayButton.setBackground(Color.DARK_GRAY);
		PlayButton.setForeground(Color.RED);
		PlayButton.addMouseListener(this);
		QuitButton.setBackground(Color.DARK_GRAY);
		QuitButton.setForeground(Color.RED);
		QuitButton.addMouseListener(this);
		
		

		
    
    f.setSize(500,500);
    f.getContentPane().setBackground(Color.BLACK);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLayout(new BorderLayout());
    
    ButtonPanel.setBackground(Color.BLACK);
    //ButtonPanel.setLayout(new BorderLayout());
    ButtonPanel.add(PlayButton);
    ButtonPanel.add(QuitButton);
    
    f.add(ButtonPanel, BorderLayout.SOUTH);
    
	try 
	{
	
		Image bg = ImageIO.read(getClass().getResource("/images/MMBG.png"));
		Image newbg = bg.getScaledInstance((int)(f.getSize().width*.9), (int)(f.getSize().height*.9), java.awt.Image.SCALE_SMOOTH);  
		JLabel picLabel = new JLabel(new ImageIcon(newbg));
		f.add(picLabel);
	} catch (IOException ex) {
	}




    f.setVisible(true);

  }
  public static void main(String args[]) {
    new MainMenu();
  }
@Override
public void mouseClicked(MouseEvent e) {


	if (e.getSource().equals(PlayButton)){
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
                        5000);
                model.getSettlementManager().placeSettlement(settlement);
                settlement.getStorageOperation().depositQuantity(5000);
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
	
	if (e.getSource().equals(QuitButton)){
		System.exit(0);
		System.out.println("QUIT");
	}
	
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	
	
}
@Override
public void mouseEntered(MouseEvent e) {
	PlayButton.setBackground(Color.GREEN);
	QuitButton.setBackground(Color.RED);
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	PlayButton.setBackground(Color.DARK_GRAY);
	QuitButton.setBackground(Color.DARK_GRAY);
	// TODO Auto-generated method stub
	
}
}
