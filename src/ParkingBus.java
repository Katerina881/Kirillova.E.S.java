import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class ParkingBus <T extends IBus, U extends IForm> {
	private HashMap<Integer, T> places;
	int randform = 0;
    U frm;
    
    private int WidthWindow;
    private int HeightWindow;

    private final int widthSizePlace = 210;
    private final int heightSizePlace = 80;
    private int maxCount;
    
    public void randForm() {
  		randform = 0 + (int) (Math.random() * 3);
  	}
    
	public ParkingBus(int size, int widthWindow, int heightWindow) {
        places = new HashMap<Integer, T>();
        WidthWindow = widthWindow;
        HeightWindow = heightWindow;
        maxCount = size;
    }
	
    private boolean CheckFreePlace(int indexPlace){
    	return places.get(indexPlace) == null;
    } 
    
    public IBus getBus(int ind) {
		return places.get(ind);
    }
    
    @SuppressWarnings("unchecked")
	public int Add(T bus, U frm)
    {
    	randForm();
    	if (places.size() == maxCount) {
			return -1;
	    }
        for (int i = 0; i < maxCount; i++) {
            if (CheckFreePlace(i)) {
                bus.SetPosition(5 + i / 5 * widthSizePlace + 5 , i % 5 * heightSizePlace + 15, WidthWindow, HeightWindow);
                places.put(i, bus);
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
                places.replace(i, bus);
                return i;
            }
        }
        return -1;
    }
    
    public boolean BolsheRavno(ParkingBus<IBus, IForm> term) {
    	if (places.size() >= term.places.size())
    		return true;
    	return false;
    }
    
    public boolean MensheRavno(ParkingBus<IBus, IForm> term) {
    	if (places.size() <= term.places.size())
    		return true;
    	return false;
    }
    
    public T Remove(int index){
        if (!CheckFreePlace(index)){
            T bus = places.get(index);
            places.replace(index, null);
            return bus;
        }
        return null;
    }
    
    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < places.size(); i++)
        {
            if (!CheckFreePlace(i))
            {
            	places.get(i).DrawBus(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, (maxCount / 5) * widthSizePlace - 65, 400);
        for (int i = 0; i < maxCount / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
                g.drawLine(i * widthSizePlace, j * heightSizePlace, i * widthSizePlace + 110, j * heightSizePlace);
            }
            g.drawLine(i * widthSizePlace, 0, i * widthSizePlace, 400);
        }
    }


}
