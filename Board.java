/**
 * @author Cecilia Watt
 * Holds the information for a player's board in the game of Battleship.
 */

import java.lang.ArrayIndexOutOfBoundsException;

public class Board {
	private int[][] board;
	
	public Board()
	{
		/**
		 * instantiates a new 10x10 board.
		 */
		board = new int[10][10];
	}
	
	public void displaytoMe()
	{
		/**
		 * Displays the board to the player.
		 * O denotes parts of ships that have not yet been hit.
		 * X denotes where ships have been hit.
		 * - records enemy misses.
		 */
		System.out.println();
		System.out.println("This is your board. O: your ships / X: where ships are hit / -: enemy misses");
		System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
		System.out.println("--+---+---+---+---+---+---+---+---+---+---|");
		for(int x=0; x<board.length; x++)
		{
			System.out.print(x + " |");
			
			for(int y = 0; y<board.length; y++)
			{
				System.out.print(" ");
				
				if(board[x][y] == 1 || board[x][y] == 2 || board[x][y] == 3 || board[x][y] == 4 | board[x][y] == 5)
				{
					System.out.print("O");
				}
				
				if(board[x][y] == 6)
				{
					System.out.print("X");
				}
				
				if(board[x][y] == 11)
				{
					System.out.print("-");
				}
				
				if(board[x][y] == 0)
				{
					System.out.print(" ");
				}
				
				System.out.print(" ");
				System.out.print("|");
			}
			System.out.println();
			System.out.println("--+---+---+---+---+---+---+---+---+---+---|");		
		}
	}
	
	public void displaytoEnemy()
	{
		/**
		 * Displays the board to the enemy.
		 * X denotes where the enemy has managed to hit a ship.
		 * - denotes where the enemy has hit and missed.
		 */
		System.out.println("This is the enemy board. X: hits / -: misses");
		System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
		System.out.println("--+---+---+---+---+---+---+---+---+---+---|");
		
		for(int x=0; x<board.length; x++)
		{
			System.out.print(x + " |");
			
			for(int y = 0; y<board.length; y++)
			{
				System.out.print(" ");
				
				if(board[x][y] == 1 || board[x][y] == 2 || board[x][y] == 3 || board[x][y] ==4 | board[x][y] == 5)
				{
					System.out.print(" ");
				}
				
				if(board[x][y] == 6)
				{
					System.out.print("X");
				}
				
				if(board[x][y] == 11)
				{
					System.out.print("-");
				}
				
				if(board[x][y] == 0)
				{
					System.out.print(" ");
				}
				
				System.out.print(" ");
				System.out.print("|");
			}
			System.out.println();
			System.out.println("--+---+---+---+---+---+---+---+---+---+---|");	
		}
	}
	
	public boolean checkAvailibility(boolean horizontal, int length, int startx, int starty)
	/**
	 * Checks whether a ship can be placed at specific coordinates.
	 * 
	 * @param horizontal Whether the user wants the ship to be horizontal/vertical.
	 * @param length Length of the ship.
	 * @param startx Starting location for the ship on the vertical axis.
	 * @param starty Starting location for the ship on the horizontal axis.
	 * 
	 * @return valid Returns true when the ship can be placed, given these specifications.
	 */
	{
		boolean valid = false;
		try
		{
			if (horizontal == true)
			{
				for (int n=0; n<length; n++)
				{
					if (board[startx][starty+n] == 0)
					{
						valid = true;
					}
					else
					{
						valid = false;
						break;
					}
				}
			}
			if (horizontal == false)
			{
				for (int n=0; n<length; n++)
				{
					if (board[startx+n][starty] == 0)
					{
						valid = true;
					}
					else
					{
						valid = false;
						break;
					}
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			valid = false;
		}
			
		return valid;
	}
	
	public void addShip(boolean horizontal, int length, int startx, int starty, int type)
	/**
	 * Adds a ship to the grid at a specified location.
	 * 
	 * @param horizontal Whether the user wants the ship to be horizontal/vertical.
	 * @param length Length of the ship.
	 * @param startx Starting location for the ship on the vertical axis.
	 * @param starty Starting location for the ship on the horizontal axis.
	 * @param type The type of ship. 
	 * 
	 */
	{
		if (horizontal == true)
		{
			for(int n = 0; n<length; n++)
			{
				board[startx][starty+n] = type;
			}
		}
		if (horizontal == false)
			for(int n = 0; n<length; n++)
			{
				board[startx+n][starty] = type;
			}
	}
	
	public int checkHit(Coordinates c)
	/**
	 * Checks whether there is a ship at given coordinates.
	 * 
	 * @param c Coordinates at which the enemy player has chosen to attack.
	 * @return int 0 - miss, 1-5 - hit ship
	 */
	{
		int x = c.getX();
		int y = c.getY();
		int status = 0;

		if (board[x][y] == 0)
		{
			board[x][y] = 7;
			status = 0;
		}
		
		if (board[x][y] > 0 && board[x][y] < 6)
		{
			status = board[x][y];
		}

		return status;
	}
	
	public void receiveHit(Coordinates c)
	/**
	 * Deals with when the player's ship has been hit.
	 * 
	 * @param c - Coordinates that have been attacked.
	 */
	{
		board[c.getX()][c.getY()] = 6;
	}	
	
	public void receiveMiss(Coordinates c)
	/**
	 * Deals with when the player's board has been hit, but the
	 * attack has missed a ship.
	 * 
	 * @param c - Coordinates that have been attacked.
	 */
	{
		board[c.getX()][c.getY()] = 11;
	}
}

