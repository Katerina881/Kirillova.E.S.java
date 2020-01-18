import java.awt.Color;
import java.awt.Graphics;

public class FormDvoinaya implements IForm {
	public int _startPosX;
    public int _startPosY;

    public FormDvoinaya(int posX, int posY) {
    	_startPosX = posX;
    	_startPosY = posY;
    }
 	
    @Override
	public void FrmDoor(Graphics g, Color color) {
		g.setColor(color);
        g.fillRect(_startPosX, _startPosY + 27, 13, 25);
        g.fillRect(_startPosX + 35, _startPosY + 27, 13, 25);
        g.fillRect(_startPosX + 65, _startPosY + 27, 13, 25);
		
        g.setColor(Color.RED);
        g.fillRect(_startPosX + 6, _startPosY + 27, 1, 25);
        g.fillRect(_startPosX + 41, _startPosY + 27, 1, 25);
        g.fillRect(_startPosX + 71, _startPosY + 27, 1, 25);
        
        g.setColor(Color.blue);
        g.fillRect(_startPosX + 1, _startPosY + 29, 4, 13);
        g.fillRect(_startPosX + 7, _startPosY + 29, 4, 13);
        g.fillRect(_startPosX + 36, _startPosY + 29, 4, 13);
        g.fillRect(_startPosX + 42, _startPosY + 29, 4, 13);
        g.fillRect(_startPosX + 66, _startPosY + 29, 4, 13);
        g.fillRect(_startPosX + 72, _startPosY + 29, 4, 13);
	}
    @Override
	public void VerxDoor(Enum.door en, Graphics g, Color color) {
		if (en != Enum.door.three) {
			g.setColor(color);
			g.fillRect(_startPosX, _startPosY + 2, 13, 20);
			g.setColor(Color.blue);
			g.fillRect(_startPosX + 1,_startPosY + 2, 4, 13);
			g.fillRect(_startPosX + 7,_startPosY + 2, 4, 13);
			g.setColor(Color.RED);
		    g.fillRect(_startPosX + 6, _startPosY + 2, 1, 20);
		}
		if (en == Enum.door.five) {
			g.setColor(color);
			g.fillRect(_startPosX+ 56, _startPosY + 2, 12, 20);
			g.setColor(Color.blue);
			g.fillRect(_startPosX + 57, _startPosY + 2, 4, 13);
			g.fillRect(_startPosX + 63, _startPosY + 2, 4, 13);
			g.setColor(Color.RED);
		    g.fillRect(_startPosX + 62, _startPosY + 2, 1, 20);
		}
	}
}
