import java.awt.Color;
import java.awt.Graphics;


public class FormKryg implements IForm {
	public int _startPosX;
    public int _startPosY;

    public FormKryg(int posX, int posY){
    	_startPosX = posX;
    	_startPosY = posY;
    }
 	
	@Override
	public void FrmDoor(Graphics g, Color color) {
		g.setColor(color);
        g.fillRect(_startPosX, _startPosY + 27, 11, 18);
        g.fillRect(_startPosX + 35, _startPosY + 27, 11, 18);
        g.fillRect(_startPosX + 65, _startPosY + 27, 11, 18);
		
        g.setColor(Color.blue);
        g.fillOval(_startPosX + 2, _startPosY + 29, 7, 7);
        g.fillOval(_startPosX + 36, _startPosY + 29, 7, 7);
        g.fillOval(_startPosX + 66, _startPosY + 29, 7, 7);
	}
    @Override
	public void VerxDoor(Enum.door en, Graphics g, Color color) {
		if (en != Enum.door.three) {
			g.setColor(color);
			g.fillRect(_startPosX, _startPosY + 2, 11, 18);
			g.setColor(Color.blue);
			g.fillOval(_startPosX + 1,_startPosY + 4, 7, 7);
		}
		if (en == Enum.door.five) {
			g.setColor(color);
			g.fillRect(_startPosX + 56, _startPosY + 2, 11, 18);
			g.setColor(Color.blue);
			g.fillOval(_startPosX + 57,_startPosY + 4, 7, 7);
		}
	}
}
