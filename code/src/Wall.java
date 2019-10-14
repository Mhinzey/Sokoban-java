import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Classe qui definit les informations relatives aux murs
 * @author Deryck Olivier, De Visch Justine
 * 
 */

public class Wall {
	int x, y; 
	Image Wall;
	NewSkin ns;

	/**
	 * Construit le mur selon ses coordonnees et son image
	 * 
	 * @param StartX
	 *            abcisse de depart
	 * @param Starty
	 *            ordonnee de depart
	 */
	public Wall(int StartX, int Starty) {
		x = StartX;
		y = Starty;

		if (s() == 1) {
			ImageIcon sWall = new ImageIcon("code/Pictures/wall.jpg");
			Wall = sWall.getImage();

		} else if (s() == 2) {
			ImageIcon sWall = new ImageIcon("code/Pictures/angel/angel_wall.png");
			Wall = sWall.getImage();

		} else if (s() == 3) {
			ImageIcon sWall = new ImageIcon("code/Pictures/demon/demon_wall.png");
			Wall = sWall.getImage();

		} else if (s() == 4) {
			ImageIcon sWall = new ImageIcon("code/Pictures/kitchen/kitchen_wall.png");
			Wall = sWall.getImage();

		} else if (s() == 5) {
			ImageIcon sWall = new ImageIcon("code/Pictures/garden/garden_wall.png");
			Wall = sWall.getImage();

		} else {
			ImageIcon sWall = new ImageIcon("code/Pictures/wall.jpg");
			Wall = sWall.getImage();
		}

	}

	/**
	 * defini un rectangle de coordonnee x,y et de largeur/hauteur 48
	 * 
	 * @return le rectangle correspondant au mur
	 */
	public Rectangle getBounds() {
		Rectangle Wall = new Rectangle(x, y, 48, 48);
		return Wall;
	}

	/**
	 * Recupere la valeur d'abcisse du mur
	 * 
	 * @return la valeur de x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Recupere la valeur d'ordonnee du mur
	 * 
	 * @return la valeur de y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Recupere l'image du mur
	 * 
	 * @return l'image du mur
	 */
	public Image getImage() {
		return Wall;
	}
	/**
	 * Récupère l'indice des apparences
	 * @return	l'indice d'apparences.
	 */
	private int s() {
		return ns.getN();
	}
}