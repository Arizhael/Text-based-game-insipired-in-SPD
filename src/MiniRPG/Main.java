package MiniRPG;

public class Main {

	public static void main(String[] args) {
		
		Character hero = new Character("Ástagos", 6, 2, 80);
		
		MainWindow Game = new MainWindow(hero);
		
		Game.StartGame();
	}

}
