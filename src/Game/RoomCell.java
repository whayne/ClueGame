package Game;

public class RoomCell extends BoardCell {
	
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE}
	
	private DoorDirection doorDirection;
	
	private char initial;
	
	public boolean isRoom()
	{
		return true;
	}
	
	

}
