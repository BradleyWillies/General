package gameversion2;

import java.util.Random;

public class GameMechanics {
	
	// Variables
	boolean running = true;
	int counter = 0;
	GUI gui;
	Enemy enemy;
	int buttonPress;
	
	// Constructor
	public GameMechanics(GUI gui2) {
		gui = gui2;
	}
		
	// Objects
	Random random = new Random();
	Player player = new Player();
		
	public void startGame() {
		gui.appendText("---------------------------------------");
		
		// Spawn enemy
		Enemy newEnemy = new Enemy(gui);
		enemy = newEnemy;
		newEnemy.spawn();
		battle();
	}
	
	public void battle() {
		while(running) {
			// Main game loop
			while(enemy.health > 0){
				mainMenu();
				if(buttonPress == 1) {
					attack();
					if(player.health < 1) {
						die();
						running = false;
						return;
					}
				}
				else if(buttonPress == 2) {
					drinkPotion();
				}
				else if(buttonPress == 3) {
					run();
					startGame();
					return;
				}
			}
			enemyDefeated();			
		}
	}
	
	public void mainMenu() {
		gui.appendText("\tYour HP: " + player.health);
		gui.appendText("\t" + enemy.name + "'s HP: " + enemy.health);
		gui.appendText("\n\tWhat would you like to do?");
		gui.appendText("\t1. Attack");
		gui.appendText("\t2. Drink health potion");
		gui.appendText("\t3. Run!\n");
		buttonPress = gui.getButtonPress();
	}
	
	public void attack() {
		int damageDealt = random.nextInt(player.attackDamage);
		int damageTaken = random.nextInt(enemy.attackDamage);
		enemy.health -= damageDealt;
		player.health -= damageTaken;
		// enemy defeated counter
		if(enemy.health < 1) {
			counter++;
		}
		gui.appendText("\t> You strike the " + enemy.name + " for " + damageDealt + " damage.");
		gui.appendText("\t> You receive " + damageTaken + " in retaliation!\n");
	}
	
	public void die() {
		gui.appendText("\nYou have taken too much damage, you are too weak to go on!");
		gui.appendText("You limp out of the dungeon, weak from battle.");
		gui.appendText("You defeated " + counter + (counter == 1 ? " enemy!" :  " enemies!\n"));
		endGame();
	}
	
	public void drinkPotion() {
		if(player.numHealthPotions > 0) {
			player.health += player.healthPotionHealAmount;
			if(player.health > 100) {
				player.health = 100;
			}
			player.numHealthPotions --;
			gui.appendText("\t> You drink a health potion, healing yourself for " + player.healthPotionHealAmount + "HP."
								+ "\n\t> You now have " + player.health + "HP."
								+ "\n\t> You have " + player.numHealthPotions + " health " + (player.numHealthPotions == 1 ? "potion" : "potions") + " left.\n");
		}
		else {
			gui.appendText("\t> You have no health potions left!\n");
		}
	}
	
	public void run() {
		gui.appendText("\tYou run away from the " + enemy.name + "!");
		if(random.nextInt(10) < 5) {
			gui.appendText("\tOUCH! While sheepishly running from your foe you stub your toe!\n\t-5HP");
			player.health -= 5;
		}
	}
	
	public void enemyDefeated() {
		gui.appendText("---------------------------------------");
		gui.appendText(" # " + enemy.name + " was defeated! #");
		gui.appendText(" # You have " + player.health + " HP left. #");
		if(random.nextInt(100) < player.healthPotionDropChance) {
			player.numHealthPotions++;
			gui.appendText(" # The " + enemy.name + " dropped a health potion! #");
			gui.appendText(" # You now have " + player.numHealthPotions + " health " + (player.numHealthPotions == 1 ? "potion" : "potions") + ". #");
		}
		gui.appendText("---------------------------------------");
		gui.appendText("What would you like to do now?");
		gui.appendText("1. Continue fighting");
		gui.appendText("2. Exit dungeon\n");		
		
		while(!(buttonPress == 4)) {
			buttonPress = gui.getButtonPress();
			if(buttonPress == 1) {
				gui.appendText("---------------------------------------");
				gui.appendText("You continue on your adventure!");
				startGame();
				buttonPress = 4;
			}
			else if(buttonPress == 2) {
				gui.appendText("You exit the dungeon after successfully defeating " + counter + (counter == 1 ? " enemy!\n" :  " enemies!\n"));
				endGame();
				buttonPress = 4;
				running = false;
			}
			else if(buttonPress == 3) {
				gui.appendText("Choose 1 or 2.\n");
			}
		}
	}
	
	public void endGame() {
		gui.appendText("#######################");
		gui.appendText("# THANKS FOR PLAYING! #");
		gui.appendText("#######################");
	}
}
