import java.awt.Color;
import java.awt.Graphics;

public class FormDvoinaya implements IForm {

    @Override
	public void FrmDoor(Graphics g, Color color, int posX, int posY) {
		g.setColor(color);
        g.fillRect(posX, posY + 27, 13, 25);
        g.fillRect(posX + 35, posY + 27, 13, 25);
        g.fillRect(posX + 65, posY + 27, 13, 25);
		
        g.setColor(Color.RED);
        g.fillRect(posX + 6, posY + 27, 1, 25);
        g.fillRect(posX + 41, posY + 27, 1, 25);
        g.fillRect(posX + 71, posY + 27, 1, 25);
        
        g.setColor(Color.blue);
        g.fillRect(posX + 1, posY + 29, 4, 13);
        g.fillRect(posX + 7, posY + 29, 4, 13);
        g.fillRect(posX + 36, posY + 29, 4, 13);
        g.fillRect(posX + 42, posY + 29, 4, 13);
        g.fillRect(posX + 66, posY + 29, 4, 13);
        g.fillRect(posX + 72, posY + 29, 4, 13);
	}
    @Override
	public void VerxDoor(Enum.door en, Graphics g, Color color, int posX, int posY) {
		if (en != Enum.door.three) {
			g.setColor(color);
			g.fillRect(posX, posY + 2, 13, 20);
			g.setColor(Color.blue);
			g.fillRect(posX + 1,posY + 2, 4, 13);
			g.fillRect(posX + 7,posY + 2, 4, 13);
			g.setColor(Color.RED);
		    g.fillRect(posX + 6, posY + 2, 1, 20);
		}
		if (en == Enum.door.five) {
			g.setColor(color);
			g.fillRect(posX+ 56, posY + 2, 12, 20);
			g.setColor(Color.blue);
			g.fillRect(posX + 57, posY + 2, 4, 13);
			g.fillRect(posX + 63, posY + 2, 4, 13);
			g.setColor(Color.RED);
		    g.fillRect(posX + 62, posY + 2, 1, 20);
		}
	}
    @Override
	public String toString() {
		return this.getClass().getName();
	}
}
