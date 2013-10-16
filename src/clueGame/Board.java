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
//import java.util.TreeSet;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private Map<Integer, LinkedList<Integer>> adj;
	int numRows, numColumns;
	String layout, legend;
	private boolean[] visited;
	private Set<BoardCell> targets;
	
	public Board(String layout, String legend)
	{
		this.layout = layout;
		this.legend = legend;
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		adj = new HashMap<Integer, LinkedList<Integer>>();
		visited = new boolean[1000];
		targets = new HashSet<BoardCell>();
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
	
	public void calcAdjacencies()
	{
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				
				LinkedList<Integer> temp = new LinkedList<Integer>();
				int index = calcIndex(i, j);
				if(cells.get(index).isDoorway())
				{
					if(((RoomCell) cells.get(index)).getDoorDirection() == RoomCell.DoorDirection.RIGHT)
						temp.add(calcIndex(i,j+1));
					if(((RoomCell) cells.get(index)).getDoorDirection() == RoomCell.DoorDirection.LEFT)
						temp.add(calcIndex(i,j-1));
					if(((RoomCell) cells.get(index)).getDoorDirection() == RoomCell.DoorDirection.UP)
						temp.add(calcIndex(i-1,j));
					if(((RoomCell) cells.get(index)).getDoorDirection() == RoomCell.DoorDirection.DOWN)
						temp.add(calcIndex(i+1,j));
				}
				else if(cells.get(index).isWalkway())
				{
					if ((j - 1) >= 0)
					{
						int tempIndex = calcIndex(i,j-1);
						if(cells.get(tempIndex).isWalkway())
							temp.add(tempIndex);
						else if(cells.get(tempIndex).isDoorway())
						{
							if(((RoomCell) cells.get(tempIndex)).getDoorDirection() == RoomCell.DoorDirection.RIGHT)
								temp.add(tempIndex);
						}
					}
					if ((i + 1) < numRows)
					{
						int tempIndex = calcIndex(i+1,j);
						if(cells.get(tempIndex).isWalkway())
							temp.add(tempIndex);
						else if(cells.get(tempIndex).isDoorway())
						{
							if(((RoomCell) cells.get(tempIndex)).getDoorDirection() == RoomCell.DoorDirection.UP)
								temp.add(tempIndex);
						}
					}
					if ((j + 1) < numColumns)
					{
						int tempIndex = calcIndex(i,j+1);
						if(cells.get(tempIndex).isWalkway())
							temp.add(tempIndex);
						else if(cells.get(tempIndex).isDoorway())
						{
							if(((RoomCell) cells.get(tempIndex)).getDoorDirection() == RoomCell.DoorDirection.LEFT)
								temp.add(tempIndex);
						}
					}
					if ((i - 1) >= 0)
					{
						int tempIndex = calcIndex(i-1,j);
						if(cells.get(tempIndex).isWalkway())
							temp.add(tempIndex);
						else if(cells.get(tempIndex).isDoorway())
						{
							if(((RoomCell) cells.get(tempIndex)).getDoorDirection() == RoomCell.DoorDirection.DOWN)
								temp.add(tempIndex);
						}
					}
				}
				adj.put(index, temp);	
			}
		}
	}

	//same with this
	public LinkedList<Integer> getAdjList(int index)
	{
		return adj.get(index);
	}
	
	public LinkedList<Integer> getAdjList(int row, int col)
	{
		int tempIndex = calcIndex(row, col);
		return adj.get(tempIndex);
	}

	//and this
	
	public void calcTargets(int row, int col, int steps) {
		targets = new HashSet<BoardCell>();
		calculateTargets(row, col, steps);
	}
	
	public void calculateTargets(int row, int col, int steps)
	{
		System.out.println("row" +row+"col"+col);
		int index = calcIndex(row, col);
		visited[index] = true;
		Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
		for (int cell : adj.get(index)) {
			if (!visited[cell])
				adjacentCells.add(cells.get(cell));
		}
		
		//for (BoardCell t : adjacentCells) {
		//		System.out.println(t.getRow() + " col " + t.getCol());
		//}
		
		for (BoardCell adjCell : adjacentCells) {
			int tempIndex = cells.indexOf(adjCell);
			System.out.println("Temp" +tempIndex);
			visited[tempIndex] = true;
			if (steps == 1) {
				targets.add(adjCell);
				System.out.println("\nadjCell " +cells.indexOf(adjCell)+ " size "+targets.size());
			}
			else if (cells.get(tempIndex).isDoorway())
				targets.add(adjCell);
			else {
				steps--;
				//System.out.println("Steps" + steps);
				//System.out.println("temp: "+tempIndex);
				int c = tempIndex % numColumns;
				//System.out.println(c);
				int r = (tempIndex-c)/numRows;
				//System.out.println(r);
				calculateTargets(r, c, steps);
				steps++;
			}
			visited[tempIndex] = false;
		}
	}

	//naturally this as well
	public Set<BoardCell> getTargets() {
		return targets;
	}
	public static void main(String [] args) {
		//Board board = new Board("ClueMansion.csv", "Legend.txt");
		//board.loadRoomConfig();
		
	}	
}
