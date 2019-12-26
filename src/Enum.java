
public class Enum {
	
	public static door CountDoor;
	
	public enum door {
		three, four, five
	}
	
	public static void rand() {
  		int count = 3 + (int) (Math.random() * 5);
  		switch (count) {
  		case 3:
  			CountDoor = door.three;
  			break;
  		case 4:
  			CountDoor = door.four;
  			break;
  		case 5:
  			CountDoor = door.five;
  			break;
  		}
  	}
}
