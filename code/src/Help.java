
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
 /**
  * Classe qui gère la fenêtre d'aide en fonction du type de partie.
  * La fenêtre est en fait un JDialog modal à la fenêtre principale.
  * @author Deryck Olivier, De Visch Justine
  */
public class Help extends JDialog{
	
    
    Lvlinfo lv;
    /**
     * Constructeur de la classe
     * @param gf	La fenêtre principale dont dépend le JDialog
     * @param title	Le Titre du JDialog
     * @param modal	Boolean qui définit si le JDialog est modal ou non
     */
    public Help(GameFrame gf,String title,boolean modal){
        super(gf,title,modal);
        this.setSize(650, 350);
        this.setResizable(false);
        JPanel panel=new JPanel();
        if(n()==1|| n()==3 || n()==6 || n()==4){
        	ImageIcon ground = new ImageIcon("code/Pictures/helpnewgame.png");
        	JLabel image = new JLabel(ground);
        	panel.add(image);
        }
        else if (n()==2){
        	ImageIcon ground = new ImageIcon("code/Pictures/helpalea.png");
        	JLabel image = new JLabel(ground);
        	panel.add(image);
        }
        else{
        	ImageIcon ground = new ImageIcon("code/Pictures/helpedit.png");
        	JLabel image = new JLabel(ground);
        	panel.add(image);
        }
        
        
        this.setFocusableWindowState(false);
        this.setFocusable(false);
        this.getContentPane().add(panel);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setAlwaysOnTop(true);
        this.toFront();        
        this.setVisible(true);


    }
    /**
     * Récupère l'indice du type de partie
     * @return l'indice de type de partie
     */
    private int n(){
    	return lv.getN();
    }
    
}
