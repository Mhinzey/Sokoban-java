import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Classe qui definit les informations relatives aux caisses
 * 
 * @author Deryck Olivier, De Visch Justine
 * 
 */
public class Box {
	int x, y;
	public String State = "NORMAL";
	Image Box;
	NewSkin ns;
	ImageIcon sBoxNormal;
	ImageIcon sBoxGoal;

	/**
	 * constructeur de la caisse dans lequel sont pris en paramaètre les coordonnees de depart
	 * L'apparence des caisses est changé dans le constructeur de la classe.
	 * 
	 * @param Startx
	 *            coordonnee x de depart
	 * @param Starty
	 *            coordonnee y de depart
	 */
	public Box(int Startx, int Starty) {
		x = Startx;
		y = Starty;
		NewSkin ns;

		if (s() == 1) {
			sBoxNormal = new ImageIcon("code/Pictures/box.jpg");
			sBoxGoal = new ImageIcon("code/Pictures/box_ok.jpg");
			if (State == "NORMAL") {
				Box = sBoxNormal.getImage();
			} else if (State == "OK") {
				Box = sBoxGoal.getImage();
			}
		} else if (s() == 2) {
			sBoxNormal = new ImageIcon("code/Pictures/angel/angel_box.png");
			sBoxGoal = new ImageIcon("code/Pictures/angel/angel_box_ok.png");
			if (State == "NORMAL") {
				Box = sBoxNormal.getImage();
			} else if (State == "OK") {
				Box = sBoxGoal.getImage();
			}

		} else if (s() == 3) {
			sBoxNormal = new ImageIcon("code/Pictures/demon/demon_box.png");
			sBoxGoal = new ImageIcon("code/Pictures/demon/demon_box_ok.png");
			if (State == "NORMAL") {
				Box = sBoxNormal.getImage();
			} else if (State == "OK") {
				Box = sBoxGoal.getImage();
			}

		} else if (s() == 4) {
			sBoxNormal = new ImageIcon("code/Pictures/kitchen/kitchen_box.png");
			sBoxGoal = new ImageIcon("code/Pictures/kitchen/kitchen_box_ok.png");
			if (State == "NORMAL") {
				Box = sBoxNormal.getImage();
			} else if (State == "OK") {
				Box = sBoxGoal.getImage();
			}

		} else if (s() == 5) {
			sBoxNormal = new ImageIcon("code/Pictures/garden/garden_box.jpg");
			sBoxGoal = new ImageIcon("code/Pictures/garden/garden_box_ok.png");
			if (State == "NORMAL") {
				Box = sBoxNormal.getImage();
			} else if (State == "OK") {
				Box = sBoxGoal.getImage();
			}

		} else {
			sBoxNormal = new ImageIcon("code/Pictures/box.jpg");
			sBoxGoal = new ImageIcon("code/Pictures/box_ok.jpg");
			if (State == "NORMAL") {
				Box = sBoxNormal.getImage();
			} else if (State == "OK") {
				Box = sBoxGoal.getImage();
			}
		}
	}

	/**
	 * defini un rectangle de coordonnee x,y et de largeur/hauteur 48
	 * 
	 * @return le rectangle correspondant a la caisse
	 */
	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(x, y, 48, 48);
		return Box;
	}

	/**
	 * Recupere la coordonnee d'abcisse d'une caisse
	 * 
	 * @return la valeur de l'abcisse
	 */
	public int getX() {
		return x;
	}

	/**
	 * Recupere la coordonnee d'ordonn�e d'une caisse
	 * 
	 * @return la valeur d'ordonnee
	 */
	public int getY() {
		return y;
	}

	/**
	 * Remplace la valeur d'abcisse par une nouvelle valeur
	 * 
	 * @param newX
	 *            nouvelle valeur de l'axe des abcisses
	 */
	public void setX(int newX) {
		this.x = newX;
	}

	/**
	 * Remplace la valeur d'ordonn�e par une nouvelle valeur
	 * 
	 * @param newY
	 *            nouvelle valeur de l'axe des ordonnees
	 */
	public void setY(int newY) {
		this.y = newY;
	}

	/**
	 * Change l'etat d'une caisse
	 * 
	 * @param newState
	 *            nouvel etat de la caisse
	 */
	public void setState(String newState) {
		this.State = newState;
	}

	/**
	 * Recupere l'etat d'une caisse
	 * 
	 * @return l'etat actuel de la caisse
	 */
	public String getState() {
		return State;
	}

	/**
	 * Recupere l'image de la caisse en fonction de son etat
	 * 
	 * @return l'image de la caisse
	 */
	public Image getImage() {
		if (State == "NORMAL") {
			Box = sBoxNormal.getImage();
		} else if (State == "OK") {
			Box = sBoxGoal.getImage();
		}
		return Box;
	}
	/**
	 * Méthode qui récupère l'info sur le type d'apparence
	 * @return le type d'apparence
	 */
	private int s() {
		return ns.getN();
	}
}