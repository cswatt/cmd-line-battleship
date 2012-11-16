/**
 * Player class for the game of battleship; holds information for one player.
 * Holds the player's board, fleet, and record of attacks.
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class Player {
	private String playerName;
	private Board board;
	public boolean loss;
	private ShipList fleet;
	private AttackRecord record;
	private boolean human;

	
	public Player(String aname, boolean h)
/**
 * Instantiates a new player.
 */
	{
		playerName = aname;
		board = new Board();
		fleet = new ShipList();
		record = new AttackRecord();
		human = h;

	}
	
	public void placeShip(int m, int n)
	/**
	 * Places a new ship on the board.
	 * If the player is human, placement input will be checked against
	 * the current board to see whether the ship can be placed without
	 * running into other ships or off the board.
	 * 
	 * If the player is not human, input will be generated randomly
	 * and checked in the same way.
	 * 
	 * Once proper placement for a ship has been obtained, the ship
	 * will be added to the board and to the player's ShipList.
	 * 
	 * @param m ship length
	 * @param n ship type: 1- aircraft carrier; 2- battleship;
	 * 3- destroyer; 4- submarine; 5- patrol boat
	 */
	{
		int x = 0;
		int y = 0;
		boolean valid = false;
		boolean alignment = false;
		while(valid == false)
		{
			if(human)
			{
				String ship = "";
				if(n==1)
				{
					ship = "aircraft carrier";
				}
				if(n==2)
				{
					ship = "battleship";
				}
				if(n==3)
				{
					ship = "destroyer";
				}
				if(n==4)
				{
					ship = "submarine";
				}
				if(n==5)
				{
					ship = "patrolboat";
				}
				try
				{
					System.out.println("Please place your " + ship + ". This gamepiece is " + m + " squares long.");
					System.out.println("Should the orientation be: 1 - horizontal, or 2 - vertical?");
					Scanner orientation = new Scanner(System.in);
					int a = orientation.nextInt();
					if(a != 1 && a != 2)
					{
						InputMismatchException e = new InputMismatchException();
						throw e;
					}
					else if(a == 1)
					{
						alignment = true;
					}
					else if(a == 2)
					{
						alignment = false;
					}
					System.out.println("Please name a starting point on the vertical axis.");
					Scanner vertical = new Scanner(System.in);
					x = vertical.nextInt();
					System.out.println("Please name a starting point on the horizontal axis.");
					Scanner horizontal = new Scanner(System.in);
					y = horizontal.nextInt();
					valid = board.checkAvailibility(alignment, m, x, y);
					if (valid == false)
					{
						System.out.println("There's no place on the board for this ship. Please put it somewhere else.");
					}
				}
				catch (InputMismatchException e)
				{
					System.out.println("The information you have input is not valid. Please try again.");
					valid = false;
				}
				
			}
			else
			{
				Random random = new Random();
				alignment = random.nextBoolean();
			    x = (int) (Math.random()*10);
				y = (int) (Math.random()*10);
				valid = board.checkAvailibility(alignment, m, x, y);
			}
		}
		board.addShip(alignment, m, x, y, n);
	}
	
	public void displaytoMe()
	/**
	 * Prints the board to the player.
	 */
	{
		board.displaytoMe();
	}
	
	public void displaytoEnemy()
	/**
	 * Prints the board to the player's enemy.
	 */
	{
		board.displaytoEnemy();
	}
	
	public Coordinates attack()
	/**
	 * Generates coordinates for an attack.
	 * 
	 * If the player is human, input will be checked to make sure those same
	 * coordinates have not been attacked already, and if those coordinates are
	 * in range of the board.
	 * 
	 * If the player is not human, coordinates will be generated randomly
	 * and those will also be checked.
	 * 
	 * Once appropriate coordinates have been found, the player will attack,
	 * and record this attack in the AttackRecord.
	 * 
	 * @return attackCoordinates An object of the Coordinates class
	 * that contains coordinates for an attack.
	 */
	{
		System.out.println("It is " + playerName + "'s turn to attack.");
		int x = 0;
		int y = 0;
		boolean redundant = true;

		
		if(human)
		{
			while(redundant)
			{
				try
				{
					System.out.println("Please select a coordinate on the vertical axis to target.");
					Scanner targetx = new Scanner(System.in);
					x = targetx.nextInt();
					if (x < 0 || x > 9)
					{
						InputMismatchException f = new InputMismatchException();
						throw f;
					}
					System.out.println("Please select a coordinate on the horizontal axis to target.");
					Scanner targety = new Scanner(System.in);
					y = targety.nextInt();
					if (y < 0 || y > 9)
					{
						InputMismatchException f = new InputMismatchException();
						throw f;
					}
					if(record.isRedundant(x, y))
					{
						System.out.println("You've already hit there. Please select different coordinates.");
					}
					else
					{
						redundant = false;
					}
				}
				catch (InputMismatchException f)
				{
					System.out.println("This is not a valid coordinate. Coordinates must be integers between 0 and 9.");
					redundant = true;
				}
			}
			
		}
		else
		{
			while (redundant)
			{
				x = (int) (Math.random()*10);
				y = (int) (Math.random()*10);
				if(record.isRedundant(x, y) == false)
				{
					redundant = false;
				}
			}
		}
		Coordinates attackCoordinates = new Coordinates(x, y);
		record.addAttack(attackCoordinates);
		return attackCoordinates;
	}
	
	public void receiveAttack(Coordinates c)
	/**
	 * Deals with an attack from the enemy player.
	 * Checks coordinates to see whether there was
	 * a ship at those coordinates.
	 * 
	 * @param c coordinates for an attack initiated
	 * by the enemy.
	 */
	{
		int status = board.checkHit(c);
		if (status == 0)
		{
			System.out.println("The attack has missed.");
			board.receiveMiss(c);
		}
		else
		{
			System.out.println(playerName + "'s ship has been hit.");
			board.receiveHit(c);
			fleet.shipHit(status);
		}
	}
	
	public boolean hasLost()
	/**
	 * Checks whether the player has lost.
	 * @return loss Returns true when this player has lost.
	 */
	{
		if (fleet.isLost())
		{
			loss = true;
		}
		return loss;
		
	}
}
