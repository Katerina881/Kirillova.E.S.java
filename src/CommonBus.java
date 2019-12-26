import java.awt.Color;
import java.awt.Graphics;


public class CommonBus extends Vehicle {

	protected final int carWidth = 100;
    protected final int carHeight = 60;
    IForm frm;
    int randform = 0;
  	
  	public void randForm() {
  		randform = 0 + (int) (Math.random() * 3);
  	}
  	
    public CommonBus(int maxSpeed, float weight, Color mainColor) {
    	Enum.rand();
    	randForm();
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }
    
	@Override
	public void DrawBus(Graphics g) {
		
		g.setColor(MainColor);
        g.fillRect( _startPosX, _startPosY + 24, 85, 26);

        g.setColor(Color.blue);
        g.fillRect( _startPosX + 78, _startPosY + 26, 7, 15);

        g.fillRect(_startPosX + 15, _startPosY + 28, 17, 10);
        g.fillRect(_startPosX + 47, _startPosY + 28, 16, 10);
        
        g.setColor(Color.DARK_GRAY);
        g.fillOval(_startPosX + 13, _startPosY + 40, 17, 17);
        g.fillOval(_startPosX + 55, _startPosY + 40, 17, 17);
        
        switch (randform) {
  		case 0:
  			frm = new FormCommon(_startPosX,_startPosY );
  			break;
  		case 1:
  			frm = new FormDvoinaya(_startPosX,_startPosY );
  			break;
  		case 2:
  			frm = new FormKryg(_startPosX,_startPosY );
  			break;
  		}
        frm.FrmDoor(g, Color.BLACK);
	}

	@Override
	public void MoveTransport(Bus.Direction direction) {
		float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            case Right:
                if (_startPosX + step < _pictureWidth - carWidth)
                {
                    _startPosX += step;
                }
                break;
            case Left:
                if (_startPosX - step > 0)
                {
                    _startPosX -= step;
                }
                break;
            case Up:
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;
            case Down:
                if (_startPosY + step < _pictureHeight - carHeight)
                {
                    _startPosY += step;
                }
                break;
        }
	}
}
