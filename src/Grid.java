/* 	Grid.java
	Author: William Woodruff
	-------------

	Represents the entire (finite) 2D plane upon which Cells interact within the Game of Life.
*/

import java.util.ArrayList;

public class Grid
{
	private Cell[][] grid;

	/* constructor - takes a 2D array of Cells */
	public Grid(Cell[][] state)
	{
		grid = state;
	}

	/*	setState
		sets the state of the grid to the given 2D array of Cells 
	*/
	public void setState(Cell[][] grid)
	{
		this.grid = grid;
	}

	/*	getState
		returns the current state of the grid as a 2D array of Cells
	*/
	public Cell[][] getState()
	{
		return grid;
	}

	/*	neighborCount
		returns the number of neighbors for a given point in the grid
	*/
	public int neighborCount(int row, int col)
	{
		int count = 0;

		for (int i = row - 1; i <= row + 1; i++)
		{
			for (int j = col - 1; j <= col + 1; j++)
			{
				if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j].isAlive())
					count++;
			}
		}

		return count;
	}

	/*	copy
		copies the current Grid instance into a new Grid object
	*/
	private Cell[][] copy()
	{
		Cell[][] newGrid = new Cell[grid.length][grid[0].length];

		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid[0].length; j++)
			{
				newGrid[i][j] = grid[i][j].copy();
			}
		}

		return newGrid;
	}

	/*	tick
		iterates the Game of Life by one step.
	*/
	public void tick()
	{
		Cell[][] tempGrid = this.copy();

		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				int nCount = this.neighborCount(i, j);

				if ((nCount < 2 || nCount > 3) && grid[i][j].isAlive())
				{
					tempGrid[i][j].setAlive(false);
				}
				else if (nCount == 3 && !(grid[i][j].isAlive()))
				{
					tempGrid[i][j].setAlive(true);
				}
				else if ((nCount == 2 || nCount == 3) && grid[i][j].isAlive())
				{
					//do nothing, because the cell is already alive
				}
			}
		}

		grid = tempGrid;
	}
}