import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ParkingBusPanel extends JPanel{
	
	ParkingBus<IBus, IForm> parkingBus;
	
	public void setParkingBus(ParkingBus<IBus, IForm> parkingbus) {
		parkingBus = parkingbus;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (parkingBus != null)
			parkingBus.Draw(g);
	}

}
