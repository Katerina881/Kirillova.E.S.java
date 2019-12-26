import java.awt.Color;
import java.awt.Graphics;

public class Bus extends CommonBus {
	 
     private Color DopColor;
     private boolean Roof;
     private boolean SideImg;
     
     public int getX() {
       	 return _startPosX;
     }
     public int getY() {
       	 return _startPosY;
     }
     
     public enum Direction {
    	 Up, Down, Left, Right
     }
     
     public Bus(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean roof, boolean sideImg) {
     super (maxSpeed, weight, mainColor);
    	 MaxSpeed = maxSpeed;
         Weight = weight;
         MainColor = mainColor;
    	 DopColor = dopColor;
    	 Roof = roof;
    	 SideImg = sideImg;
     }
     
     @Override
     public void DrawBus(Graphics g) {
    	 
    	 g.setColor(MainColor);
         g.fillRect( _startPosX, _startPosY, 85, 54);
         
         super.DrawBus(g);
         
         g.setColor(Color.BLACK);
         g.drawRect(_startPosX, _startPosY + 24, 83, 1);
         
         g.setColor(Color.blue);
         g.fillRect(_startPosX + 15, _startPosY + 3, 10, 10);
         g.fillRect(_startPosX + 35, _startPosY + 3, 10, 10);
         g.fillRect(_startPosX + 73, _startPosY + 3, 12, 10);

         if (SideImg) {
        	 g.setColor(DopColor);
             g.fillRect(_startPosX + 13, _startPosY + 14, 40, 8);
         }

         if (!Roof)  {
             g.setColor(Color.LIGHT_GRAY);
             g.fillRect(_startPosX, _startPosY, 85, 3);
         }
         frm.VerxDoor(Enum.CountDoor, g, Color.black);
     }
}
