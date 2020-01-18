import java.awt.Color;
import java.awt.Graphics;

public class FormCommon implements IForm {
	public int _startPosX;
    public int _startPosY;

    public FormCommon(int posX, int posY) {
    	_startPosX = posX;
    	_startPosY = posY;
    }

	@Override
	public void FrmDoor(Graphics g, Color color) {
		g.setColor(color);
        g.fillRect(_startPosX, _startPosY + 27, 11, 25);
        g.fillRect(_startPosX + 35, _startPosY + 27, 11, 25);
        g.fillRect(_startPosX + 65, _startPosY + 27, 11, 25);
		
        g.setColor(Color.blue);
        g.fillRect(_startPosX + 2, _startPosY + 29, 7, 13);
        g.fillRect(_startPosX + 36, _startPosY + 29, 7, 13);
        g.fillRect(_startPosX + 66, _startPosY + 29, 7, 13);
	}
	@Override
	public void VerxDoor(Enum.door en, Graphics g, Color color) {
		if (en != Enum.door.three) {
			g.setColor(color);
			g.fillRect(_startPosX, _startPosY + 2, 11, 20);
			g.setColor(Color.blue);
			g.fillRect(_startPosX + 2,_startPosY + 2, 7, 13);
		}
		if (en == Enum.door.five) {
			g.setColor(color);
			g.fillRect(_startPosX+ 56, _startPosY + 2, 11, 20);
			g.setColor(Color.blue);
			g.fillRect(_startPosX + 57, _startPosY + 2, 7, 13);
		}
	}
}
