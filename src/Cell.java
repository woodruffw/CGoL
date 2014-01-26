/* 	Cell.java
	Author: William Woodruff
	-------------

	Represents a single cell in Conway's Game of Life.
	Each cell has a boolean state: dead (false) or alive (true)
*/

public class Cell
{
	private boolean alive;

	/* constructor */
	public Cell(boolean flag)
	{
		alive = flag;
	}

	/*	setAlive
		sets the state of the Cell instance
	*/
	public void setAlive(boolean flag)
	{
		alive = flag;
	}

	/*	isAlive
		returns a boolean indicating the state of the Cell instance
	*/
	public boolean isAlive()
	{
		return alive;
	}

	/*	copy
		returns a new Cell whose state matches its parent
	*/
	public Cell copy()
	{
		return new Cell(this.isAlive());
	}
}