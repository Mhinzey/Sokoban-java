import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Classe qui gère le panel contenant le choix des apparences de jeu.
 * @author mhinzey
 *
 */
public class Skin extends JPanel {

	JButton casual = new JButton();
	JButton angel = new JButton();
	JButton demon = new JButton();
	JButton kitchen = new JButton();
	JButton garden = new JButton();
	JButton Exit = new JButton();
	JButton Menu = new JButton();

	/**
	 * Constructeur de la classe, construit les elements dans le panel
	 */
	public Skin() {
		this.setLayout(null);
		this.add(casual);
		this.add(angel);
		this.add(demon);
		this.add(kitchen);
		this.add(garden);
		this.add(Exit);
		this.add(Menu);
		Exit.setBounds(1110, 10, 150, 60);
		Exit.setIcon(new ImageIcon("code/Pictures/buttongame_exit.png"));
		Menu.setBounds(10, 10, 150, 60);
		Menu.setIcon(new ImageIcon("code/Pictures/buttongame_Menu.png"));
		casual.setBounds(88, 170, 150, 400);
		casual.setIcon(new ImageIcon("code/Pictures/casual_button.png"));
		angel.setBounds(326, 170, 150, 400);
		angel.setIcon(new ImageIcon("code/Pictures/angel/angel_button.png"));
		demon.setBounds(564, 170, 150, 400);
		demon.setIcon(new ImageIcon("code/Pictures/demon/demon_button.png"));
		kitchen.setBounds(802, 170, 150, 400);
		kitchen.setIcon(new ImageIcon("code/Pictures/kitchen/kitchen_button.png"));
		garden.setBounds(1040, 170, 150, 400);
		garden.setIcon(new ImageIcon("code/Pictures/garden/garden_button.png"));
		setFocusable(true);
		this.setVisible(true);
	}

	/**
	 * Dessine les elements dans le panel
	 */
	public void paintComponent(Graphics g) {
		try {
			Image ground = ImageIO.read(new File("code/Pictures/wallpaperskin.png"));
			g.drawImage(ground, 0, 0, null);
		} catch (Exception e) {
		}
	}

}
