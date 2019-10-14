import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe du panel contenant le choix des niveaux de base
 * 
 * @author Deryck Olivier, De Visch justine
 * 
 */
public class BasicLevel extends JPanel {

	JButton b1 = new JButton();
	JButton b2 = new JButton();
	JButton b3 = new JButton();
	JButton b4 = new JButton();
	JButton b5 = new JButton();
	JButton b6 = new JButton();
	JButton b7 = new JButton();
	JButton b8 = new JButton();
	JButton b9 = new JButton();
	JButton b10 = new JButton();
	JButton Exit = new JButton();
	JButton Menu = new JButton();;

	/**
	 * Constructeur de la classe, construit les elements dans le panel
	 */
	public BasicLevel() {
		this.setLayout(null);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);
		this.add(b7);
		this.add(b8);
		this.add(b9);
		this.add(b10);
		this.add(Exit);
		this.add(Menu);
		Exit.setBounds(1110, 10, 150, 60);
		Exit.setIcon(new ImageIcon("code/Pictures/buttongame_exit.png"));
		Menu.setBounds(10, 10, 150, 60);
		Menu.setIcon(new ImageIcon("code/Pictures/buttongame_Menu.png"));
		b1.setBounds(88, 170, 150, 150);
		b1.setIcon(new ImageIcon("code/Pictures/basiclevel1.png"));
		b2.setBounds(326, 170, 150, 150);
		b2.setIcon(new ImageIcon("code/Pictures/basiclevel2.png"));
		b3.setBounds(564, 170, 150, 150);
		b3.setIcon(new ImageIcon("code/Pictures/basiclevel3.png"));
		b4.setBounds(802, 170, 150, 150);
		b4.setIcon(new ImageIcon("code/Pictures/basiclevel4.png"));
		b5.setBounds(1040, 170, 150, 150);
		b5.setIcon(new ImageIcon("code/Pictures/basiclevel5.png"));
		b6.setBounds(88, 400, 150, 150);
		b6.setIcon(new ImageIcon("code/Pictures/basiclevel6.png"));
		b7.setBounds(326, 400, 150, 150);
		b7.setIcon(new ImageIcon("code/Pictures/basiclevel7.png"));
		b8.setBounds(564, 400, 150, 150);
		b8.setIcon(new ImageIcon("code/Pictures/basiclevel8.png"));
		b9.setBounds(802, 400, 150, 150);
		b9.setIcon(new ImageIcon("code/Pictures/basiclevel9.png"));
		b10.setBounds(1040, 400, 150, 150);
		b10.setIcon(new ImageIcon("code/Pictures/basiclevel10.png"));
		setFocusable(true);
		this.setVisible(true);
	}

	/**
	 * Dessine les éléments dans le panel
	 */
	public void paintComponent(Graphics g) {
		try {
			Image ground = ImageIO
					.read(new File("code/Pictures/wallpaperlevel.png"));
			g.drawImage(ground, 0, 0, null);
		} catch (Exception e) {
		}
	}

}
