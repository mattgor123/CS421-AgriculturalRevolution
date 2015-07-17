package rev.z.view.command;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import rev.z.clickmodes.ClickMode;
import rev.z.clickmodes.SetActiveSettlementMode;
import rev.z.view.core.GameViewController;
import rev.z.view.maps.BlankMapMode;
import rev.z.view.maps.ContaminationDisplayMode;
import rev.z.view.maps.FertilityDisplayMode;
import rev.z.view.maps.TerrainDisplayMode;

public class InteractionModeView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3857039321425468294L;

    /**
     * The game view controller.
     * 
     */
    private final GameViewController controller;

    /**
     * Initializes the view.
     * 
     * @param controller
     */
    public InteractionModeView(GameViewController controller) {
        this.controller = controller;
        this.setSize(200, 120);
        this.setBackground(Color.DARK_GRAY);

        this.setLayout(null);
        
        JButton selectSettlementMode  = CommandView.getButton("SELECT SETTLEMENT", 180);
        selectSettlementMode.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus().setClickMode(new SetActiveSettlementMode());
            }
        });
        selectSettlementMode.setLocation(10, 40);
        this.add(selectSettlementMode);
        
        JButton elevationViewMode  = CommandView.getButton("ELEVATION", 60);
        elevationViewMode.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus().setMapDisplayMode(new TerrainDisplayMode());;
            }
        });
        elevationViewMode.setLocation(10, 60);
        this.add(elevationViewMode);
        
        JButton fertilityViewMode  = CommandView.getButton("FERTILITY", 60);
        fertilityViewMode.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus().setMapDisplayMode(new FertilityDisplayMode());;
            }
        });
        fertilityViewMode.setLocation(70, 60);
        this.add(fertilityViewMode);
        
        JButton blankViewMode  = CommandView.getButton("BLANK", 60);
        blankViewMode.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus().setMapDisplayMode(new BlankMapMode());
            }
        });
        blankViewMode.setLocation(130, 60);
        this.add(blankViewMode);
        
        JButton contaminationMapView  = CommandView.getButton("CONTAMINATION", 180);
        contaminationMapView.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus().setMapDisplayMode(new ContaminationDisplayMode());
            }
        });
        contaminationMapView.setLocation(10, 80);
        this.add(contaminationMapView);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Menlo", Font.PLAIN, 12));
        g.setColor(Color.WHITE);
        ClickMode current = this.controller.getStatus().getClickMode();
        g.drawString(current.getName(), 4, 20);
        g.setFont(new Font("Menlo", Font.PLAIN, 10));
        g.drawString(current.getDescription(), 4, 34);
    }
}