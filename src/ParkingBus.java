import java.awt.Color;
import java.awt.Graphics;

public class ParkingBus <T extends IBus, U extends IForm> {
	private T[] places;
	int randform = 0;
    U frm;
    
    private int WidthWindow;
    private int HeightWindow;

    private final int widthSizePlace = 210;
    private final int heightSizePlace = 80;
    
    public void randForm() {
  		randform = 0 + (int) (Math.random() * 3);
  	}
    
    @SuppressWarnings("unchecked")
	public ParkingBus(int size, int widthWindow, int heightWindow)
    {
        places = (T[])new IBus[size];
        WidthWindow = widthWindow;
        HeightWindow = heightWindow;
        for (int i = 0; i < places.length; i++) {
            places[i] = null;
        }
        
    }
    private boolean CheckFreePlace(int indexPlace)
    {
        return places[indexPlace] == null;
    }     
    
    @SuppressWarnings("unchecked")
	public int Add(T bus, U frm)
    {
    	randForm();
        for (int i = 0; i < places.length; i++)
        {
            if (CheckFreePlace(i))
            {
                bus.SetPosition(5 + i / 5 * widthSizePlace + 5 , i % 5 * heightSizePlace + 15, WidthWindow, HeightWindow);
                switch (randform) {
          		case 0:
          			frm = (U) new FormCommon();
          			break;
          		case 1:
          			frm = (U) new FormDvoinaya();
          			break;
          		case 2:
          			frm = (U) new FormKryg();
          			break;
          		}
                ((CommonBus)bus).setFrm(frm);
                places[i] = bus;
                return i;
            }
        }
        return -1;
    }
    
    public boolean BolsheRavno(ParkingBus<IBus, IForm> term) {
    	return places.length >= term.places.length;
    }
    
    public boolean MensheRavno(ParkingBus<IBus, IForm> term) {
    	return places.length <= term.places.length;
    }
    
    public T Remove(int index)
    {
        if (index < 0 || index > places.length)
            return null;
        if (!CheckFreePlace(index))
        {
            T bus = places[index];
            places[index] = null;
            return bus;
        }
        return null;
    }
    
    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < places.length; i++)
        {
            if (!CheckFreePlace(i))
            {
                places[i].DrawBus(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, (places.length / 5) * widthSizePlace - 65, 400);
        for (int i = 0; i < places.length / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
                g.drawLine(i * widthSizePlace, j * heightSizePlace, i * widthSizePlace + 110, j * heightSizePlace);
            }
            g.drawLine(i * widthSizePlace, 0, i * widthSizePlace, 400);
        }
    }


}
