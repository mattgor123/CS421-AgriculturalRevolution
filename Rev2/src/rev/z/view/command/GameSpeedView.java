package rev.z.view.command;
	

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;

	import javax.swing.JPanel;

	import rev.z.view.core.GameViewController;

	public class GameSpeedView extends JPanel {

	    /**
	     * 
	     */
	    private static final long serialVersionUID = -3857039321425468294L;
	    
	    

	    private final GameViewController controller;

	    public GameSpeedView(GameViewController controller) {
	        this.controller = controller;
	        this.setSize(200, 25);
	        this.setBackground(Color.DARK_GRAY);
	    }

	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.setFont(new Font("Menlo", Font.PLAIN, 10));
	        g.setColor(Color.WHITE);
	        g.drawString("I = " + this.convertDate(), 4, 20);
	    }

	    public String convertDate() {
	   
	        Long iterations = controller.getModel().getElapsedIterations();
	        return " " + iterations;
	        
	        
	  
	    }
	}

