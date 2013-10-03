// Will Hayne and Anthony Nguyen

package Game;

public class RoomCell extends BoardCell {
	
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE}
	
	private DoorDirection doorDirection;
	
	private char initial;
	
	@Override
	public boolean isRoom()
	{
		return true;
	}
	
	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}
	
	public char getInitial()
	{
		initial = 'a';
		return initial;
	}
	
	

}
