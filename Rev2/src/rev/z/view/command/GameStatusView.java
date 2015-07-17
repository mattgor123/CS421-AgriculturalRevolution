package rev.z.view.command;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import rev.z.view.core.GameViewController;

public class GameStatusView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3857039321425468294L;
    
    private long counter = 0;

    private final GameViewController controller;

    public GameStatusView(GameViewController controller) {
        this.controller = controller;
        this.setSize(200, 50);
        this.setBackground(Color.DARK_GRAY);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Menlo", Font.PLAIN, 10));
        g.setColor(Color.WHITE);
        g.drawString("" + this.convertDate(this.counter), 4,
                20);
        long research = controller.getModel().getStatsManager().getResearch();
        if (research > 1000000) {
            g.setColor(Color.GREEN);
        }
        g.drawString("RESEARCH PROGRESS = " + (research), 4, 40);
    }

    public String convertDate(long counter) {
    	this.counter = counter;
        Long iterations = controller.getModel().getElapsedIterations();
        return ("Day: " + iterations/1200 + ", Hour: " + ((iterations/50)%24));
    }
}