/**
 * Contains the list of ships for one player.
 */

public class ShipList {
	private Ship aircraft_carrier;
	private Ship battleship;
	private Ship destroyer;
	private Ship submarine;
	private Ship patrol_boat;
	private boolean lost = false;
	
	public ShipList() 
	/**
	 * Instantiates a new ShipList.
	 * 
	 * Creates five new ships: an aircraft carrier, a battleship,
	 * a destroyer, a submarine, and a patrol boat.
	 */
	{
		aircraft_carrier = new Ship(5);
		battleship = new Ship(4);
		destroyer = new Ship(3);
		submarine = new Ship(3);
		patrol_boat = new Ship(2);
	}
	
	public void shipHit(int a)
	/**
	 * Adds one hit to a designated ship.
	 * 
	 * @param a An integer designating which ship has been hit.
	 */
	{
		if (a == 1)
		{
			aircraft_carrier.hits();
		}
		if (a == 2)
		{
			battleship.hits();
		}
		if (a == 3)
		{
			destroyer.hits();
		}
		if (a == 4)
		{
			submarine.hits();
		}
		if (a == 5)
		{
			patrol_boat.hits();
		}
	}
	
	public boolean isLost()
	/**
	 * Detects when the player has lost all ships, and therefore
	 * when the player has lost the game.
	 * 
	 * @return lost Returns true when the player has lost.
	 */
	{
		if (aircraft_carrier.isSunk() && battleship.isSunk() && destroyer.isSunk() && submarine.isSunk() && patrol_boat.isSunk())
		{
			lost = true;
		}
		return lost;
	}

}
