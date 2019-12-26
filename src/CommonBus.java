import java.awt.Color;
import java.awt.Graphics;


public class CommonBus extends Vehicle {

	protected final int carWidth = 100;
    protected final int carHeight = 60;
    
    public CommonBus(int maxSpeed, float weight, Color mainColor) {
	    MaxSpeed = maxSpeed;
	    Weight = weight;
	    MainColor = mainColor;
    }
    
    public CommonBus(String save) {
    	String[] mas = save.split(";");
    	if (mas.length == 4) {
	        MaxSpeed = Integer.parseInt(mas[0]);
	        Weight = Float.parseFloat(mas[1]);
	        MainColor = toColor(mas[2]);
	        frm = toForm(mas[3]);
        }
    }
    
    @Override
    public String toString() {
        return MaxSpeed + ";" + Weight + ";" + getNameColor(MainColor) + ";" + frm.toString();
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
        
        if (frm != null)
        	frm.FrmDoor(g, Color.BLACK, _startPosX, _startPosY);
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
	
	public Color toColor(String info) {
		if (info.contains("BLACK")) return Color.BLACK;
		if (info.contains("WHITE")) return Color.WHITE;
		if (info.contains("ORANGE")) return Color.ORANGE;
		if (info.contains("YELLOW")) return Color.YELLOW;
		if (info.contains("RED")) return Color.RED;
		if (info.contains("BLUE")) return Color.BLUE;
		if (info.contains("GREEN")) return Color.GREEN;
		if (info.contains("GRAY")) return Color.GRAY;
		
		return Color.BLACK;
	}
    
    public String getNameColor(Color color) {
		if (color.equals(Color.BLACK)) return "BLACK";
		if (color.equals(Color.WHITE)) return "WHITE";
		if (color.equals(Color.ORANGE)) return "ORANGE";
		if (color.equals(Color.YELLOW)) return "YELLOW";
		if (color.equals(Color.RED)) return "RED";
		if (color.equals(Color.BLUE)) return "BLUE";
		if (color.equals(Color.GREEN)) return "GREEN";
		if (color.equals(Color.GRAY)) return "GRAY";

		return "WHITE";
	}
    
    public IForm toForm(String info) {
		if (info.contains("Form1")) return new FormCommon();
		if (info.contains("Form2")) return new FormDvoinaya();
		if (info.contains("Form3")) return new FormKryg();
		
		return new FormCommon();
	}
    
    public String getCountDoor() {
		if (Enum.CountDoor == Enum.door.three) return "three";
		if (Enum.CountDoor == Enum.door.four) return "four";
		if (Enum.CountDoor == Enum.door.five) return "five";
		
		return "three";
	}
	
	public Enum.door toCountDoor(String info) {
		
		if (info.contains("three")) return Enum.door.three;
		if (info.contains("four")) return Enum.door.four;
		if (info.contains("five")) return Enum.door.five;
		
		return Enum.door.three;
		
	}
}
