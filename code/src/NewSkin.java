/**
 * Classe qui sert à récupérer les indices d'apparence pour les modifier en jeu.
 * @author Deryck Olivier
 *
 */
public class NewSkin {
	static int n, skin;

	/**
	 * Donne une nouvelle valeur à l'indice d'apparence
	 * @param nskin	La nouvelle valeur voulue.
	 */
	public static void setN(int nskin) {
		n = nskin;
	}
	/**
	 * Récupère l'indice d'apparence.
	 * @return L'indice d'apparence
	 */
	public static int getN() {
		return n;
	}
}
