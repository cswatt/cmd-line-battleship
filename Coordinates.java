/**
 * Holds coordinates for an attack that the player is considering.
 */

public class Coordinates {
	public int x;
	public int y;
	public Coordinates (int initx, int inity)
	/**
	 * Instantiates new Coordinates
	 * @param x Coordinates for the vertical axis.
	 * @param y Coordinates for the horizontal axis.
	 */
	{
		x = initx;
		y = inity;
	}
	
	public int getX()
	/**
	 * @return x Returns coordinates for the vertical axis.
	 */
	{
		return x;
	}
	
	public int getY()
	/**
	 * @return y Returns coordinates for the horizontal axis.
	 */
	{
		return y;
	}

}
