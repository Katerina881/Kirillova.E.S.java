import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public void SaveData(String filename) throws IOException, ParkingNotFoundException {
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
    }
	
	public void SaveLvl(String filename, int numLvl) throws Exception {
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
        catch (Exception ex) {
        	throw ex;
		}
    }
	
	public void LoadLvl(String filename, int numLvl) throws Exception {
    	File file = new File(filename);
        if (!file.exists()) {
        	throw new FileNotFoundException();
        }
        try (BufferedReader fs = new BufferedReader(new FileReader(file))){
        	parkLevels.set(numLvl, new ParkingBus<IBus, IForm>(countPlaces, pictureWidth, pictureHeight));
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
        catch (ParkingOccupiedPlaceException ex) {
			throw ex;
		} 
        catch (Exception ex) {
			throw ex;
		}
    }
	
	public void LoadData(String filename) throws Exception {
    	File file = new File(filename);
        if (!file.exists()) {
        	throw new FileNotFoundException();
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
            	throw new Exception("Неверный формат файла");
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
    }
}
