package models;

import java.awt.Color;

public class Constants {

	public static int GRID_SIZE = 50;
	public static int CELL_SIZE = 10;
	
	public static Color yellow = Color.YELLOW;
	public static Color blue = new Color(58,82,170);

	public static int CELL_STAT = 1;
	public static int EVO_STAT = 2;
	public static int HORIZONTAL = 1;
	public static int VERTICAL = 2;	
	public static int SEGMENT_UNIT_SIZE = 3;
	
	public static String rules = "Conway's Game of Life is a cellular automation devised by the "
			+ "British mathematician John Horton Carter in 1970."
			+ "\n\nIt is a \'Zero-Player\' simulation, i.e., its evolution is determined "
			+ "determined by its initial state, requiring no further input. One "
			+ "interacts by creating an initial configuration and observing its "
			+ "evolution pattern."
			+ "\n\nThe universe of Game of Life is an infinite 2D grid of square cells, "
			+ "each of which has 2 possible states, dead or alive. Each cell "
			+ "interacts with its 8 neighbour cells, according to the following "
			+ "rules:-"
			+ "\n   1. Any cell with <2 neighbours dies."
			+ "\n   2. Any cell with 2 or 3 neighbours lives on."
			+ "\n   3. Any cell with >3 neighbours dies."
			+ "\n   4. Any cell with exactly 3 neighbours becomes alive.";

}
