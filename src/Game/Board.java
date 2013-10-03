// Will Hayne and Anthony Nguyen

package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	int numRows, numColumns;
	
	public Board(String layout, String legend)
	{
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
	}
	
	public void loadConfigFiles() {
		loadRoomConfig();
		loadBoardConfig();
	}
	
	//helper method one
	public void loadRoomConfig()
	{
		
	}
	
	//helper method two	
	public void loadBoardConfig()
	{
		
	}
	
	public int calcIndex(int row, int col) {
		return row*numColumns + col;
	}
	public RoomCell getRoomCellAt(int row, int col) {
		return null;
	}
	
	public BoardCell getCellAt(int index)
	{
		return null;
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	
}
