package rev.z.view.command;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import rev.model.settlement.core.Settlement;
import rev.model.settlement.specializations.BaseCultivationSpecialization;
import rev.model.settlement.specializations.BaseDefaultSpecialization;
import rev.model.settlement.specializations.BaseScienceSpecialization;
import rev.z.clickmodes.FireShellMode;
import rev.z.clickmodes.PlaceRoadMode;
import rev.z.clickmodes.PlaceSettlementMode;
import rev.z.clickmodes.SendGrainMode;
import rev.z.clickmodes.SendPeopleMode;
import rev.z.view.core.GameViewController;

/**
 * Displays information about the active settlement.
 * 
 *
 * 
 */
public class ActiveSettlementView extends JPanel {

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
    public ActiveSettlementView(GameViewController controller) {
        this.controller = controller;
        this.setSize(200, 300);
        this.setBackground(Color.DARK_GRAY);

        this.setLayout(null);

        JButton specializeInAgricultureButton = CommandView.getButton(
                "SET MODE: AGRICULTURE", 180);
        specializeInAgricultureButton.setLocation(10, 100);
        specializeInAgricultureButton.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                Settlement settlement = this.getController().getStatus()
                        .getActiveSettlement();
                settlement.setSpecialization(new BaseCultivationSpecialization(
                        settlement));
            }
        });
        this.add(specializeInAgricultureButton);

        JButton specializeInScienceButton = CommandView.getButton(
                "SET MODE: SCIENCE", 180);
        specializeInScienceButton.setLocation(10, 120);
        specializeInScienceButton.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                Settlement settlement = this.getController().getStatus()
                        .getActiveSettlement();
                settlement.setSpecialization(new BaseScienceSpecialization(
                        settlement));
            }
        });
        this.add(specializeInScienceButton);

        JButton specializeInNothingButton = CommandView.getButton(
                "SET MODE: NONE", 180);
        specializeInNothingButton.setLocation(10, 140);
        specializeInNothingButton.addActionListener(new VCListener(
                this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                Settlement settlement = this.getController().getStatus()
                        .getActiveSettlement();
                settlement.setSpecialization(new BaseDefaultSpecialization(
                        settlement));
            }
        });
        this.add(specializeInNothingButton);

        JButton placeRoadMode = CommandView.getButton("BUY ROADS", 180);
        placeRoadMode.setLocation(10, 160);
        placeRoadMode.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus()
                        .setClickMode(new PlaceRoadMode());
            }
        });
        this.add(placeRoadMode);

        JButton placeSettlementMode = CommandView.getButton("BUY SETTLEMENTS",
                180);
        placeSettlementMode.setLocation(10, 180);
        placeSettlementMode.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus()
                        .setClickMode(new PlaceSettlementMode());
            }
        });
        this.add(placeSettlementMode);

        JButton fireShellMode = CommandView.getButton("DEFENSES", 180);
        fireShellMode.setLocation(10, 200);
        fireShellMode.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus()
                        .setClickMode(new FireShellMode());
            }
        });
        this.add(fireShellMode);

        JButton sendGrain = CommandView.getButton("EXPORT 500 GRAIN", 180);
        sendGrain.setLocation(10, 220);
        sendGrain.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus()
                        .setClickMode(new SendGrainMode());
            }
        });
        this.add(sendGrain);

        JButton sendPeople = CommandView.getButton("SEND 500 PEOPLE", 180);
        sendPeople.setLocation(10, 240);
        sendPeople.addActionListener(new VCListener(this.controller) {

            @Override
            public void actionPerformed(ActionEvent e) {
                this.getController().getStatus()
                        .setClickMode(new SendPeopleMode());
            }
        });
        this.add(sendPeople);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Settlement settlement = controller.getStatus().getActiveSettlement();
        g.setFont(new Font("Menlo", Font.PLAIN, 18));
        g.setColor(Color.WHITE);
        if (settlement.isStarving()) {
            g.setColor(Color.ORANGE);
        }
        if (settlement.getPopulation() == 0) {
            g.setColor(Color.RED);
        }
        g.drawString(settlement.getName().toUpperCase(), 4, 20);
        g.setFont(new Font("Menlo", Font.PLAIN, 10));
        g.drawString("POPULATION:" + settlement.getPopulation(), 4, 40);
        g.drawString("GRAIN:" + settlement.getStorageOperation().getQuantity(), 4, 60);
        g.drawString("SPECIALIZATION:"
                + settlement.getSpecialization().getName().toUpperCase(), 4, 80);
    }
}