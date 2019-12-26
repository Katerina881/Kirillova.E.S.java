import javax.swing.JPanel;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class BusPanel extends JPanel {
	IBus bus;
	
    public void position(IBus bus) {
    	this.bus = bus;
    } 
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	if (bus != null) {
    		bus.DrawBus(g);
    	}
    }
}
