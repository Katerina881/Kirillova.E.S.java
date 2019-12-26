import java.awt.Color;
import java.awt.Graphics;

public class FormCommon implements IForm {

	@Override
	public void FrmDoor(Graphics g, Color color, int posX, int posY) {
		g.setColor(color);
        g.fillRect(posX, posY + 27, 11, 25);
        g.fillRect(posX + 35, posY + 27, 11, 25);
        g.fillRect(posX + 65, posY + 27, 11, 25);
		
        g.setColor(Color.blue);
        g.fillRect(posX + 2, posY + 29, 7, 13);
        g.fillRect(posX + 36, posY + 29, 7, 13);
        g.fillRect(posX + 66, posY + 29, 7, 13);
	}
	@Override
	public void VerxDoor(Enum.door en, Graphics g, Color color, int posX, int posY) {
		if (en != Enum.door.three) {
			g.setColor(color);
			g.fillRect(posX, posY + 2, 11, 20);
			g.setColor(Color.blue);
			g.fillRect(posX + 2,posY + 2, 7, 13);
		}
		if (en == Enum.door.five) {
			g.setColor(color);
			g.fillRect(posX+ 56, posY + 2, 11, 20);
			g.setColor(Color.blue);
			g.fillRect(posX + 57, posY + 2, 7, 13);
		}
	}
}
