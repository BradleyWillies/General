package gameversion2;

import java.util.Random;

public class Enemy {

	// Variables
	String[] type = { "Skeleton", "Zombie", "Warrior", "Assassin" };
	int maxHealth = 76;	// The random.nextInt method includes 0 to 75
	int attackDamage = 35;
	int health;
	String name;
	GUI gui;
	
	// Constructor
	public Enemy(GUI gui2) {
		gui = gui2;
	}
	
	// Objects
	Random random = new Random();
	
	public void spawn() {
		int randomHealth = random.nextInt(maxHealth);
		while(randomHealth == 0) {
			randomHealth = random.nextInt(maxHealth);
		}
		health = randomHealth;
		String enemy = type[random.nextInt(type.length)];
		name = enemy;
		gui.appendText("\t# " + enemy + random.nextInt(20) + " has appeared! #\n");
	}
}
