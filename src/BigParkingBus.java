import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BigParkingBus {
	
	ArrayList<ParkingBus<IBus, IForm>> parkLevels;
	
	private final int countPlaces = 15;
	private int pictureWidth;
    private int pictureHeight;
	
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
	
	public boolean SaveData(String filename) throws IOException {
    	File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try (FileWriter fs = new FileWriter(filename, false)) {
		    fs.write("CountLeveles:" + parkLevels.size());
		    fs.write("\r\n");
		    for (ParkingBus<IBus, IForm> level : parkLevels) {
		        fs.write("Level\r\n");
		        for (int i = 0; i < countPlaces; i++) {
		            IBus bus = level.getPlace(i);
		            if (bus != null) {
		                if (bus.getClass().getName() == "CommonBus") {
		                    fs.write(i + ":CommonBus:");
		                }
		                if (bus.getClass().getName() == "Bus") {
		                    fs.write(i + ":Bus:");
		                }
		                fs.write(bus.toString() + "\r\n");   
		            }
		        }
		    }
        }
        return true;
    }
	
	public boolean SaveLvl(String filename, int numLvl) throws IOException {
    	if (numLvl > parkLevels.size() || numLvl < 0)
    		return false;
    	File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try (FileWriter fs = new FileWriter(filename, false)) {
        	ParkingBus<IBus, IForm> level = parkLevels.get(numLvl);
        	 for (int i = 0; i < countPlaces; i++) {
        		 IBus bus = level.getPlace(i);
        		 if (bus != null) {
        			 if (bus.getClass().getName() == "CommonBus") {
        				 fs.write(i + ":CommonBus:");
        			 }
        			 if (bus.getClass().getName() == "Bus") {
        				 fs.write(i + ":Bus:");
        			 }
        			 fs.write(bus.toString() + "\r\n");   
        		 }
		      }
        }
        catch (Exception e) {
        	return false;
		}
        return true;
    }
	
	public boolean LoadLvl(String filename, int numLvl) throws NumberFormatException, IOException {
    	if (numLvl > parkLevels.size() || numLvl < 0)
    		return false;
    	File file = new File(filename);
        if (!file.exists()) {
            return false;
        }
        parkLevels.set(numLvl, new ParkingBus<IBus, IForm>(countPlaces, pictureWidth, pictureHeight));
        try (BufferedReader fs = new BufferedReader(new FileReader(file))){
        	String line = "";
            IBus bus = null;
        	while ((line = fs.readLine()) != null) {
        		if (line == null || line == ""){
		            continue;
		        }
	            String[] mas = line.split(":");
	            if (line.split(":")[1].contains("CommonBus")) {
	                bus = new CommonBus(line.split(":")[2]);
	            }
	            else if (line.split(":")[1].contains("Bus")){
	                bus = new Bus(line.split(":")[2]);
	            }
	            parkLevels.get(numLvl).setPlace(Integer.parseInt(line.split(":")[0]), bus);
	        	}
        }
        catch (Exception e) {
        	return false;
		}
        return true;
    }
	
	public boolean LoadData(String filename) throws NumberFormatException, IOException {
    	File file = new File(filename);
        if (!file.exists()) {
            return false;
        }
        try (BufferedReader fs = new BufferedReader(new FileReader(file))) {
            String line = fs.readLine();
            if (line.contains("CountLeveles")) {
                int count = Integer.parseInt(line.split(":")[1]);
                if (parkLevels != null)  {
                    parkLevels.clear();
                }
                parkLevels = new ArrayList<ParkingBus<IBus, IForm>>(count);
            }
            else   {
                return false;
            }
            int counter = -1;
            IBus bus = null;
            while ((line = fs.readLine()) != null) {
                if (line.contains("Level"))  {
                    counter++;
                    parkLevels.add(new ParkingBus<IBus, IForm>(countPlaces, pictureWidth, pictureHeight));
                    continue;
                }
                if (line == null || line == "") {
                    continue;
                }
                String[] mas = line.split(":");
                if (line.split(":")[1].contains("CommonBus")) {
                    bus = new CommonBus(line.split(":")[2]);
                }
                else if (line.split(":")[1].contains("Bus")) {
                    bus = new Bus(line.split(":")[2]);
                }
                parkLevels.get(counter).setPlace(Integer.parseInt(line.split(":")[0]), bus);
            }
        }
        return true;
    }
}
