import java.util.ArrayList;

public class BigParkingBus {
	ArrayList<ParkingBus<IBus, IForm>> parkLevels;
	
	private final int countPlaces = 15;
	
	public BigParkingBus(int countLevels, int pictureWidth, int pictureHeight) {
		parkLevels = new ArrayList<ParkingBus<IBus, IForm>>();
        for (int i = 0; i < countLevels; i++){
        	parkLevels.add(new ParkingBus<IBus, IForm>(countPlaces, pictureWidth, pictureHeight));
        }
    }
	
	public ParkingBus<IBus, IForm> getParkingBus(int ind){
    	if (ind > -1 && ind < parkLevels.size()) {
            return parkLevels.get(ind);
        }
        return null;
    }
	
	public IBus getBus(int indLevel, int indVehicle){
    	if (indLevel > -1 && indLevel < parkLevels.size()) {
			return parkLevels.get(indLevel).getBus(indVehicle);
        }
        return null;
    }
}
