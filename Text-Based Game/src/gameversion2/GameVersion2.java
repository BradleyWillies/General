package gameversion2;

public class GameVersion2 {

	// Main method
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		GameMechanics gm = new GameMechanics(gui);
		
		gui.startGUI();
		gui.appendText("Welcome to the Dungeon!");
		gm.startGame();
	}
}
