import java.awt.Color;
import java.awt.Graphics;


public class FormKryg implements IForm {

	@Override
	public void FrmDoor(Graphics g, Color color, int posX, int posY) {
		g.setColor(color);
        g.fillRect(posX, posY + 27, 11, 18);
        g.fillRect(posX + 35, posY + 27, 11, 18);
        g.fillRect(posX + 65, posY + 27, 11, 18);
		
        g.setColor(Color.blue);
        g.fillOval(posX + 2, posY + 29, 7, 7);
        g.fillOval(posX + 36, posY + 29, 7, 7);
        g.fillOval(posX + 66, posY + 29, 7, 7);
	}
    @Override
	public void VerxDoor(Enum.door en, Graphics g, Color color, int posX, int posY) {
		if (en != Enum.door.three) {
			g.setColor(color);
			g.fillRect(posX, posY + 2, 11, 18);
			g.setColor(Color.blue);
			g.fillOval(posX + 1,posY + 4, 7, 7);
		}
		if (en == Enum.door.five) {
			g.setColor(color);
			g.fillRect(posX + 56, posY + 2, 11, 18);
			g.setColor(Color.blue);
			g.fillOval(posX + 57,posY + 4, 7, 7);
		}
	}
    @Override
	public String toString() {
		return this.getClass().getName();
	}
}
