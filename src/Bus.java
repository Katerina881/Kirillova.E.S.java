import java.awt.Color;
import java.awt.Graphics;

public class Bus {
	 private int _startPosX;
     private int _startPosY;
     private int _pictureWidth;
     private int _pictureHeight;
     private final int carWidht = 100;
     private final int carHeight = 60;
     private int MaxSpeed;
     private float Weight;
     private Color MainColor;
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
     
     public Bus(int maxSpeed, float weight, Color mainColor, Color dopColor,
boolean roof, boolean sideImg){
    	 MaxSpeed = maxSpeed;
    	 Weight = weight;
    	 MainColor = mainColor;
    	 DopColor = dopColor;
    	 Roof = roof;
    	 SideImg = sideImg;
     }
     
     public void SetPosition(int x, int y, int width, int height) {
         _startPosX = x;
         _startPosY = y;
         _pictureWidth = width;
         _pictureHeight = height;
     }
     
     public void MoveTransport(Direction direction){
         float step = MaxSpeed * 180 / Weight;
         switch (direction) {
             case Right:
                 if (_startPosX + step < _pictureWidth - carWidht) {
                     _startPosX += step;
                 }
                 break;
             case Left:
                 if (_startPosX - step > 0){
                     _startPosX -= step;
                 }
                 break;
             case Up:
                 if (_startPosY - step > 0){
                     _startPosY -= step;
                 }
                 break;
             case Down:
                 if (_startPosY + step < _pictureHeight - carHeight) {
                     _startPosY += step;
                 }
                 break;
          }
     }
     
     public void DrawET(Graphics g) {
         g.setColor(MainColor);
         g.fillRect( _startPosX, _startPosY, 85, 54);

         g.setColor(Color.BLACK);
         g.fillRect(_startPosX, _startPosY + 27, 11, 25);
         g.fillRect(_startPosX + 35, _startPosY + 27, 11, 25);
         g.fillRect(_startPosX + 65, _startPosY + 27, 11, 25);
         g.drawRect(_startPosX, _startPosY + 24, 83, 1);

         g.setColor(Color.blue);
         g.fillRect( _startPosX + 2, _startPosY + 29, 7, 13);
         g.fillRect( _startPosX + 36, _startPosY + 29, 7, 13);
         g.fillRect( _startPosX + 66, _startPosY + 29, 7, 13);
         g.fillRect( _startPosX + 78, _startPosY + 26, 7, 15);

         g.fillRect(_startPosX + 15, _startPosY + 28, 17, 10);
         g.fillRect(_startPosX + 47, _startPosY + 28, 16, 10);

         g.fillRect(_startPosX + 15, _startPosY + 3, 10, 10);
         g.fillRect(_startPosX + 35, _startPosY + 3, 10, 10);
         g.fillRect(_startPosX + 73, _startPosY + 3, 12, 10);
         
         g.setColor(Color.DARK_GRAY);
         g.fillOval(_startPosX + 13, _startPosY + 40, 17, 17);
         g.fillOval(_startPosX + 55, _startPosY + 40, 17, 17);

         if (SideImg) {
        	 g.setColor(DopColor);
             g.fillRect(_startPosX + 13, _startPosY + 14, 40, 8);
         }

         if (!Roof) {
             g.setColor(Color.LIGHT_GRAY);
             g.fillRect(_startPosX, _startPosY, 85, 3);
         }
         
     }

}
