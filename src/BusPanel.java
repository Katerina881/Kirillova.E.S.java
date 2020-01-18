import javax.swing.JPanel;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class BusPanel extends JPanel {
	IBus bus;
	
    public void position(IBus bus) {
    	this.bus = bus;
    } 
    public void paint(Graphics g) {
        bus.DrawBus(g);
    }
}
