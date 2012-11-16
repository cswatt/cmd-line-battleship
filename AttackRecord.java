/**
 * Keeps a record of all attacks that a player has made.
 */

public class AttackRecord {
	private boolean[][] record;
	
	public AttackRecord()
	/**
	 * Creates a new AttackRecord.
	 * 
	 * The array is 10x10.
	 */
	{
		record = new boolean[10][10];
	}
	
	public void addAttack(Coordinates c)
	/**
	 * Adds an attack to the array. 
	 * The value is changed from false to true.
	 * 
	 * @param c Coordinates of the attack.
	 */
	{
		record[c.getX()][c.getY()] = true;
	}
	
	public boolean isRedundant(int x, int y)
	/**
	 * Determines whether the attack has already been made.
	 * 
	 * @param x The vertical coordinate of the attack.
	 * @param y The horizontal coordinate of the attack.
	 * 
	 * @return record[x][y] Returns true if this location
	 * on the grid has already been attacked before.
	 */
	{
		return record[x][y];	
	}

}
