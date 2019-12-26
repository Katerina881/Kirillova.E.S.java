import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements IBus {
	
	 protected int _startPosX;
     protected int _startPosY;
     protected int _pictureWidth;
     protected int _pictureHeight;
     public int MaxSpeed; 
     public float Weight; 
     public Color MainColor; 
     
     public void SetPosition(int x, int y, int width, int height){
         _startPosX = x;
         _startPosY = y;
         _pictureWidth = width;
         _pictureHeight = height;
     }
     public abstract void DrawBus(Graphics g);
     public abstract void MoveTransport(Bus.Direction direction);

}
