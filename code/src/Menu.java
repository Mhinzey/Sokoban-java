import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Classe du panel de menu principal
 * @author Deryck Olivier, De Visch Justine
 *
 */
public class Menu extends JPanel {
	public JButton bChooseLevel = new JButton();
	public JButton bNewGame = new JButton();
	public JButton bEditor = new JButton();
	public JButton bRand = new JButton();
	public JButton bExit = new JButton();
	public JButton bContinue = new JButton();
	public JButton bSelect = new JButton();
	// ***************************************************************************************
	public JButton bCustom = new JButton();

	/**
	 * Constructeur de la classe, crée les boutons
	 */
	public Menu() {
		bNewGame.setBounds(480, 100, 350, 60);
		bNewGame.setIcon(new ImageIcon("code/Pictures/newgame.png"));
		bContinue.setBounds(480, 170, 350, 60);
		bContinue.setIcon(new ImageIcon("code/Pictures/continue.png"));
		bChooseLevel.setBounds(480, 240, 350, 60);
		bChooseLevel.setIcon(new ImageIcon("code/Pictures/chooselevel.png"));
		bRand.setBounds(480, 310, 350, 60);
		bRand.setIcon(new ImageIcon("code/Pictures/randomlevel.png"));
		bSelect.setBounds(480, 380, 350, 60);
		bSelect.setIcon(new ImageIcon("code/Pictures/selectlevel.png"));
		bEditor.setBounds(480, 450, 350, 60);
		bEditor.setIcon(new ImageIcon("code/Pictures/leveleditor.png"));
		bExit.setBounds(480, 590, 350, 60);
		bExit.setIcon(new ImageIcon("code/Pictures/exit.png"));
		bCustom.setBounds(480, 520, 350, 60);
		bCustom.setIcon(new ImageIcon("code/Pictures/skins.png"));
		this.setLayout(null);
		this.add(bChooseLevel);
		this.add(bNewGame);
		this.add(bEditor);
		this.add(bRand);
		this.add(bExit);
		this.add(bSelect);
		this.add(bContinue);
		this.add(bCustom);
	}

	/**
	 * Dessine le fond du menu
	 */
	public void paintComponent(Graphics g) {
		try {
			Image ground = ImageIO.read(new File("code/Pictures/wallpaper.png"));
			g.drawImage(ground, 0, 0, null);
		} catch (Exception e) {
		}
	}

}