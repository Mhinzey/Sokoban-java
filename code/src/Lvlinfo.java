
/**
 * Classe qui gère les informations sur le type de partie
 * 
 * @author Deryck Olivier, De Visch Justine
 * 
 */

public class Lvlinfo {
	static int n, lvl;

	/**
	 * donne le numéro du niveau
	 * 
	 * @param newlvl
	 *            le numéro du niveau
	 */
	public static void setlvl(int newlvl) {
		lvl = newlvl;
	}

	/**
	 * Recupere le numéro du niveau
	 * 
	 * @return le numero du niveau
	 */
	public static int getlvl() {
		return lvl;
	}

	/**
	 * Donne le type de partie
	 * 
	 * @param nlvl
	 *            indice de partie
	 */
	public static void setN(int nlvl) {
		n = nlvl;
	}

	/**
	 * Recupere le type de partie
	 * 
	 * @return l'indice du type de partie
	 */
	public static int getN() {
		return n;
	}

}
