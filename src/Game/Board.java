package Game;

import java.util.ArrayList;
import java.util.Map;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	int numRows, numColumns;
	
	public void loadConfigFiles() {
		
	}
	
	public int calcIndex(int row, int col) {
		return row*numColumns + col;
	}
	public BoardCell GetRoomCellAt(int row, int col) {
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
