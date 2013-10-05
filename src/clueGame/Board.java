// Will Hayne and Anthony Nguyen

package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	int numRows, numColumns;
	String layout, legend;
	
	public Board(String layout, String legend)
	{
		this.layout = layout;
		this.legend = legend;
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
	}
	
	public void loadConfigFiles() {
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (BadConfigFormatException e) {
			System.out.println(e);
		}
	}
	
	//helper method one
	public void loadRoomConfig() throws BadConfigFormatException
	{
		Scanner input = null;
		try {
			FileReader r = new FileReader(legend);
			input = new Scanner(r);
			String[] line = new String[0];
			int count = 0;
			while(input.hasNextLine()) {
				line = input.nextLine().split(", ");
				count++;
				if (line.length != 2)
					throw new BadConfigFormatException("Bad column length in legend line " + count + ", length = " + line.length);
				rooms.put(line[0].charAt(0), line[1]);
			}
			} catch (FileNotFoundException e)
			{
				System.out.println(e);
			}
		input.close();
	}
	
	//helper method two	
	public void loadBoardConfig() throws BadConfigFormatException
	{
		Scanner input = null;
		try {
		FileReader r = new FileReader(layout);
		input = new Scanner(r);
		
		int j = 0;
		String[] line = new String[0];
		int columnTest = 0;
		
		while (input.hasNextLine()) {
			line = input.nextLine().split(",");
			for (int i = 0; i < line.length; i++) {
				
				// Throw an exception if the character does not match up with any of the characters in the legend
				boolean test = false;
				for (char k : rooms.keySet()) {
					if (line[0].charAt(0) == k)
						test = true;
				}
				if (!test)
					throw new BadConfigFormatException("Key: " + line[0].charAt(0) + " does not match with any keys in legend");
				
				// Room and Walkway Cells are stored in cells
				if (line[i].equalsIgnoreCase("W"))
					cells.add(new WalkwayCell());
				else if (line[i].length() == 2) {
					//System.out.println(line[i].charAt(0) + " " + line[i].charAt(1));
					cells.add(new RoomCell(line[i]));
				}
				else
					cells.add(new RoomCell(line[i]));
			}
			
			// Throw an exception if the number of columns is not the same as in the previous row
			if (j > 0 && columnTest != line.length)
				throw new BadConfigFormatException("In row " + j + " the number of columns does not match the number of columns in the previous row");
			j++;
			columnTest = line.length;
		}
		//System.out.println(cells);
		numRows = j;
		numColumns = line.length;
		input.close();
		} catch (FileNotFoundException e)
		{
			System.out.println(e);
		}
	}
	
	public int calcIndex(int row, int col) {
		return row*numColumns + col;
	}
	public RoomCell getRoomCellAt(int row, int col) {
		int index = calcIndex(row, col);
		if (cells.get(index).isRoom())
			return (RoomCell) cells.get(index);
		else
			return null;
	}
	
	public BoardCell getCellAt(int index)
	{
		return cells.get(index);
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
	
	//had in the AdjTargetTests class, not sure if it was supposed to be linked here or IntBoard
	//made here just in case
	public void calcAdjacencies()
	{
		
	}
	
	//same with this
	public LinkedList<Integer> getAdjList(int index)
	{
		LinkedList<Integer> temp = new LinkedList<Integer>();
		temp.add(1);
		return temp;
	}
	
	//and this
	public void calcTargets(int row, int col, int steps)
	{
		
	}
	
	//naturally this as well
	public Set<BoardCell> getTargets() {
		Set<BoardCell> targets = new HashSet<BoardCell>();
		return targets;
	}
	public static void main(String [] args) {
		//Board board = new Board("ClueMansion.csv", "Legend.txt");
		//board.loadRoomConfig();
	}
	
}
