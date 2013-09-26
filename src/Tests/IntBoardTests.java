package Tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Clue.IntBoard;

public class IntBoardTests {
	private IntBoard board;
	
	@Before
	public static void setUp() {
		System.out.println("In @Before");
		board = new IntBoard();
	}
	@Test
	public void testCalcIndex() {
		int index = board.CalcIndex(1,1);
		Assert.assertEquals(5, index);
	}
	
	@Test
	public void testAdjacency0() {
		LinkedList testList = board.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency15() {
		LinkedList testList = board.getAdjList(15);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(2, testList.size());
	}

	@Test
	public void testAdjacency7() {
		LinkedList testList = board.getAdjList(7);
		Assert.assertTrue(testList.contains(3));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency8() {
		LinkedList testList = board.getAdjList(8);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(12));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency5() {
		LinkedList testList = board.getAdjList(5);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(1));
		Assert.assertTrue(testList.contains(6));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency10() {
		LinkedList testList = board.getAdjList(10);
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(4, testList.size());
	}
}
