/**
 * Holds the information for one ship.
 */

public class Ship {
	private int length;
	private int hits;
	private boolean sunk = false;
	
	public Ship(int initLength)
	/**
	 * Instantiates a new ship.
	 * @param initLength The length of the ship.
	 */
	{
		length = initLength;
		hits = 0;
	}
	
	public void hits()
	/**
	 * Adds one hit to the ship.
	 */
	{
		hits++;
	}
	
	public boolean isSunk()
	/**
	 * Determines when the ship is sunk.
	 * This is defined by when the number of hits
	 * it has sustained is equivalent to its length.
	 * 
	 * @return sunk Returns true if the ship has been sunk.
	 */
	{
		if(hits == length)
		{
			sunk = true;
		}
		
		return sunk;
	}


}
