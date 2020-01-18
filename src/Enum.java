
public class Enum {
	
	private door Door;
	
	private void setDoor(door Door) {
		this.Door = Door;
	}
	public door getDoor() {
    	 return Door;
    }
	public enum door {
		three, four, five
	}
		
	public void rand() {
		int count = 3 + (int) (Math.random() * 5);
		switch (count) {
		case 3:
			setDoor(door.three);
			break;
		case 4:
			setDoor(door.four);
			break;
		case 5:
			setDoor(door.five);
			break;
		}
	}
}


