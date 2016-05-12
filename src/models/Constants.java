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
	
//	public static String rules = "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. The ‘game’ is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves. \nThe universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:\n\t1.	Any live cell with fewer than two live neighbours dies, as if caused by under-population.\n\t2.	Any live cell with two or three live neighbours lives on to the next generation.\n\t3.	Any live cell with more than three live neighbours dies, as if by overcrowding.\n\t4.	Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.\nThe initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.";
	
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
