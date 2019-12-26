
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class BusPanel extends JPanel {
	Bus bus;
	Enum dor = new Enum();
	static int flag = 0;
    public void position(Bus bus) {
    	this.bus = bus;
    	
    }
    public void paint(Graphics g) {
    	
        bus.DrawET(g);
        if (flag == 0) {
        	 dor.rand();
        	 flag++;
        }
        if (dor.getDoor() != Enum.door.three) {
            g.setColor(Color.BLACK);
            g.fillRect(bus.getX(), bus.getY() + 2, 11, 20);
        	g.setColor(Color.blue);
            g.fillRect(bus.getX() + 2, bus.getY() + 2, 7, 13);
		}
		if (dor.getDoor() == Enum.door.five) {
	        g.setColor(Color.BLACK);
	        g.fillRect(bus.getX()+ 56, bus.getY() + 2, 11, 20);
	        g.setColor(Color.blue);
	        g.fillRect( bus.getX() + 57, bus.getY() + 2, 7, 13);
		}
    }
    
}
