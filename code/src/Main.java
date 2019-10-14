/**
 * Classe principale qui sert au lancement du jeu
 * 
 * @author Deryck Olivier, De Visch Justine
 * 
 */
public class Main {
	static Lvlinfo lv;
	/**
	 * Méthode qui lance le jeu si il n'y a pas d'arguments, ou qui applique les mouvements à un niveau si il y a 3 paramètres
	 * @param args Les arguments
	 */
	public static void main(String[] args) {
		lv.setN(1);
		if (args.length == 0) {
			GameFrame frame = new GameFrame();
		}

		else if (args.length == 3) {
			String input = args[0];
			String moves = args[1];
			String output = args[2];
			Game test = new Game(input, moves, output);
		}
	}

}
