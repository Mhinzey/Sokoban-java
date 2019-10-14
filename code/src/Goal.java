import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Classe qui definit les informations relatives aux objectifs
 * 
 * @author Deryck Olivier, De Visch Justine
 * 
 */
public class Goal  {
	int x, y;
	Image Goal;
	public String State = "NORMAL";
	 NewSkin ns;

	/**
	 * Construit l'objectifavec ses coordonnees et son image
	 * 
	 * @param Startx
	 *            valeur d'abcisse de depart
	 * @param Starty
	 *            valeur d'ordonnee de depart
	 */
	public Goal(int Startx, int Starty) {
		x = Startx;
		y = Starty;
		
		
		if(s()==1)
        {
        ImageIcon sGoal = new ImageIcon("code/Pictures/goal.png");
        Goal = sGoal.getImage();      
        }
        else if(s()==2)
        {
        ImageIcon sGoal = new ImageIcon("code/Pictures/angel/angel_goal.png");
        Goal = sGoal.getImage();
      
        }
        else if(s()==3)
        {
        ImageIcon sGoal = new ImageIcon("code/Pictures/demon/demon_goal.png");
        Goal = sGoal.getImage();
      
        }
        else if(s()==4)
        {
        ImageIcon sGoal = new ImageIcon("code/Pictures/kitchen/kitchen_goal.png");
        Goal = sGoal.getImage();
      
        }
        else if(s()==5)
        {
        ImageIcon sGoal = new ImageIcon("code/Pictures/garden/garden_goal.png");
        Goal = sGoal.getImage();
      
        }
        else{
        ImageIcon sGoal = new ImageIcon("code/Pictures/goal.png");	
        Goal = sGoal.getImage();
        }
	
	
	}

	/**
	 * defini un rectangle de coordonnee x,y et de largeur/hauteur 48
	 * 
	 * @return le rectangle correspondant a l'objectif
	 */
	public Rectangle getBounds() {
		Rectangle Goal = new Rectangle(x, y, 48, 48);
		return Goal;
	}

	/**
	 * Recupere la valeur d'abcisse de l'objectif
	 * 
	 * @return la valeur d'abcisse
	 */
	public int getX() {
		return x;
	}

	/**
	 * Recupere la valeur d'ordonnee de l'objectif
	 * 
	 * @return la valeur d'ordonnee
	 */
	public int getY() {
		return y;
	}

	/**
	 * Recupere l'image correspondante a l'objectif
	 * 
	 * @return l'image de l'objectif
	 */
	public Image getImage() {
		return Goal;
	}

	/**
	 * Donne un etat a l'objectif
	 * 
	 * @param newState
	 *            le nouvel etat qu'on donne a l'objectif
	 */
	public void setState(String newState) {
		this.State = newState;
	}

	/**
	 * Recupere l'etat de l'objectif
	 * 
	 * @return l'etat de l'objectif
	 */
	public String getState() {
		return State;
	}
	/**
	 * Recupere l'indice pour le choix d'apparences.
	 * @return l'indice d'apparence
	 */
	private int s(){
    	return ns.getN();
    }
}