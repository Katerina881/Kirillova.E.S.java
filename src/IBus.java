import java.awt.Graphics;

public interface IBus {
	
	void SetPosition(int x, int y, int width, int height);
    void MoveTransport(Bus.Direction direction);
    void DrawBus(Graphics g);
}
