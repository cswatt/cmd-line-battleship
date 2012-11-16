/**
 * Contains one game of Battleship.
 */

public class Game {
	Player Human;
	Player Computer;
	
	public Game()
	/**
	 * Instantiates a new game. Creates two objects of the Player class.
	 * 
	 * Note that "false" will result in an automated computer player.
	 * "True" will cause that player object to ask for user input 
	 * during the game.
	 */
	{
		Human = new Player("Player 1", true);
		Computer = new Player("Player 2", false);
	}
	
	public void play()
	/**
	 * The two players will begin to play.
	 * 
	 * They will place their ships, and then begin to attack.
	 */
	{
		Human.placeShip(5, 1);
		Human.displaytoMe();
		Human.placeShip(4, 2);
		Human.displaytoMe();
		Human.placeShip(3, 3);
		Human.displaytoMe();
		Human.placeShip(3, 4);
		Human.displaytoMe();
		Human.placeShip(2, 5);
		Human.displaytoMe();
		Computer.placeShip(5, 1);
		Computer.placeShip(4, 2);
		Computer.placeShip(3, 3);
		Computer.placeShip(3, 4);
		Computer.placeShip(2, 5);
		
		Computer.displaytoEnemy();
		
		while (Human.hasLost() == false && Computer.hasLost() == false)
		{
			Computer.receiveAttack(Human.attack());
			Computer.displaytoEnemy();
			if (Computer.hasLost())
			{
				break;
			}
			Human.receiveAttack(Computer.attack());
			Human.displaytoMe();
		}
		
		if (Computer.hasLost())
		{
			System.out.println("Computer has lost.");
		}
		
		if (Human.hasLost())
		{
			System.out.println("Human has lost.");
		}	
	}
}
