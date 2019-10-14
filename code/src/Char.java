import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Classe qui definit les informations relatives au hero
 * 
 * @author Deryck Olivier, De Visch Justine
 */
public class Char {
	int x, y;
	public String State = "NORMAL";
	String CharDir = "DOWN";
	Image Char;
	NewSkin ns;
	ImageIcon sCharDown ;
	ImageIcon sCharRight;
	ImageIcon sCharLeft;
	ImageIcon sCharUp;

	/**
	 * Constructeur de la classe qui donne les coordonne de depart du hero
	 * Change aussi l'apparence du personnage en fonction du choix de l'utilisateur
	 * @param Startx
	 *            coordonnee d'abcisse de depart
	 * @param Starty
	 *            coordonnee d'ordonnee de depart
	 */
	public Char(int Startx, int Starty) {
		x = Startx;
		y = Starty;
		
		
		
		
		if(s()==1)
        {
		sCharDown = new ImageIcon("code/Pictures/hero_face.png");
		sCharRight = new ImageIcon("code/Pictures/hero_right.png");
		sCharLeft = new ImageIcon("code/Pictures/hero_left.png");
		sCharUp = new ImageIcon("code/Pictures/hero_bottom.png");
		if (CharDir == "DOWN") {
			Char = sCharDown.getImage();
		} else if (CharDir == "RIGHT") {
			Char = sCharRight.getImage();
		} else if (CharDir == "LEFT") {
			Char = sCharLeft.getImage();
		} else if (CharDir == "UP") {
			Char = sCharUp.getImage();
		}
        }
        else if(s()==2)
        {
        	sCharDown = new ImageIcon("code/Pictures/angel/angel_face.png");
    		sCharRight = new ImageIcon("code/Pictures/angel/angel_right.png");
    		sCharLeft = new ImageIcon("code/Pictures/angel/angel_left.png");
    		sCharUp = new ImageIcon("code/Pictures/angel/angel_bottom.png");
    		if (CharDir == "DOWN") {
    			Char = sCharDown.getImage();
    		} else if (CharDir == "RIGHT") {
    			Char = sCharRight.getImage();
    		} else if (CharDir == "LEFT") {
    			Char = sCharLeft.getImage();
    		} else if (CharDir == "UP") {
    			Char = sCharUp.getImage();
    		}
        }
        else if(s()==3)
        {
        	sCharDown = new ImageIcon("code/Pictures/demon/demon_face.png");
    		sCharRight = new ImageIcon("code/Pictures/demon/demon_right.png");
    		sCharLeft = new ImageIcon("code/Pictures/demon/demon_left.png");
    		sCharUp = new ImageIcon("code/Pictures/demon/demon_bottom.png");
    		if (CharDir == "DOWN") {
    			Char = sCharDown.getImage();
    		} else if (CharDir == "RIGHT") {
    			Char = sCharRight.getImage();
    		} else if (CharDir == "LEFT") {
    			Char = sCharLeft.getImage();
    		} else if (CharDir == "UP") {
    			Char = sCharUp.getImage();
    		}
        }
        else if(s()==4)
        {
        	sCharDown = new ImageIcon("code/Pictures/kitchen/kitchen_face.png");
    		sCharRight = new ImageIcon("code/Pictures/kitchen/kitchen_right.png");
    		sCharLeft = new ImageIcon("code/Pictures/kitchen/kitchen_left.png");
    		sCharUp = new ImageIcon("code/Pictures/kitchen/kitchen_bottom.png");
    		if (CharDir == "DOWN") {
    			Char = sCharDown.getImage();
    		} else if (CharDir == "RIGHT") {
    			Char = sCharRight.getImage();
    		} else if (CharDir == "LEFT") {
    			Char = sCharLeft.getImage();
    		} else if (CharDir == "UP") {
    			Char = sCharUp.getImage();
    		}
        }
        else if(s()==5)
        {
        	sCharDown = new ImageIcon("code/Pictures/garden/garden_face.png");
    		sCharRight = new ImageIcon("code/Pictures/garden/garden_right.png");
    		sCharLeft = new ImageIcon("code/Pictures/garden/garden_left.png");
    		sCharUp = new ImageIcon("code/Pictures/garden/garden_bottom.png");
    		if (CharDir == "DOWN") {
    			Char = sCharDown.getImage();
    		} else if (CharDir == "RIGHT") {
    			Char = sCharRight.getImage();
    		} else if (CharDir == "LEFT") {
    			Char = sCharLeft.getImage();
    		} else if (CharDir == "UP") {
    			Char = sCharUp.getImage();
    		}
      
        }
        else{
        	sCharDown = new ImageIcon("code/Pictures/hero_face.png");
    		sCharRight = new ImageIcon("code/Pictures/hero_right.png");
    		sCharLeft = new ImageIcon("code/Pictures/hero_left.png");
    		sCharUp = new ImageIcon("code/Pictures/hero_bottom.png");
    		if (CharDir == "DOWN") {
    			Char = sCharDown.getImage();
    		} else if (CharDir == "RIGHT") {
    			Char = sCharRight.getImage();
    		} else if (CharDir == "LEFT") {
    			Char = sCharLeft.getImage();
    		} else if (CharDir == "UP") {
    			Char = sCharUp.getImage();
    		}
        }
	}

	/**
	 * defini un rectangle de coordonnee x,y et de largeur/hauteur 48
	 * 
	 * @return le rectangle correspondant au hero
	 */
	public Rectangle getBounds() {
		Rectangle Char = new Rectangle(x, y, 48, 48);
		return Char;
	}

	/**
	 * Recupere la coordonnee d'abcisse du hero
	 * 
	 * @return la valeur de l'abcisse
	 */
	public int getX() {
		return x;
	}

	/**
	 * Recupere la coordonne d'ordonnee du hero
	 * 
	 * @return la valeur d'ordonnee
	 */
	public int getY() {
		return y;
	}

	/**
	 * Recupere la direction de hero
	 * 
	 * @return la direction du hero
	 */
	public String getDir() {
		return CharDir;
	}

	/**
	 * Remplace la valeur d'abcisse par une nouvelle
	 * 
	 * @param newX
	 *            la nouvelle valeur d'abcisse
	 */
	public void setX(int newX) {
		this.x = newX;
	}

	/**
	 * Remplace la valeur d'ordonnee par une nouvelle
	 * 
	 * @param newY
	 *            la nouvelle valeur d'ordonnee
	 */
	public void setY(int newY) {
		this.y = newY;
	}

	/**
	 * Donne une nouvelle direction au hero
	 * 
	 * @param newDir
	 *            nouvelle direction du hero
	 */
	public void setDir(String newDir) {
		this.CharDir = newDir;
	}

	/**
	 * Obtient l'image du hero en fonction de sa direction
	 * 
	 * @return l'image du hero
	 */
	public Image getImage() {
		if (CharDir == "DOWN") {
			Char = sCharDown.getImage();
		} else if (CharDir == "RIGHT") {
			Char = sCharRight.getImage();
		} else if (CharDir == "LEFT") {
			Char = sCharLeft.getImage();
		} else if (CharDir == "UP") {
			Char = sCharUp.getImage();
		}

		return Char;
	}

	/**
	 * Definit les deplacements du hero 
	 * 
	 */
	public void Move() {
		if (CharDir == "DOWN") {
			this.y += 48;
		} else if (CharDir == "RIGHT") {
			this.x += 48;
		} else if (CharDir == "LEFT") {
			this.x -= 48;
		} else if (CharDir == "UP") {
			this.y -= 48;
		}

	}
	/**
	 * Definit un nouvel état pour le personnage 
	 * @param newState Le nouvel état du personnage
	 */
	public void setState(String newState) {
		this.State = newState;
	}

	/**
	 * Recupère l'état du personnage
	 * @return	L'état du personnage
	 */
	public String getState() {
		return State;
	}
	/**
	 * Recupère l'indice pour l'apparence des skins
	 * @return l'indice d'apparence
	 */
	private int s(){
    	return ns.getN();
    }
}
