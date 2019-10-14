import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Classe qui définit la fenêtre d'erreur lorsqu'un des niveaux de base n'est pas deverouillé
 * @author Deryck Olivier, De Visch Justine
 *
 */
public class BlockedLvl extends JFrame {
	/**
	 * Constructeur de la classe, crée tous les éléments dans la fenêtre
	 * @throws IOException
	 */
	public BlockedLvl() throws IOException {
		setFocusable(true);
		Image icone = Toolkit.getDefaultToolkit()
				.getImage("code/Pictures/icone.png");
		this.setTitle("Locked level");
		this.setIconImage(icone);
		this.setSize(250, 250);
		this.setResizable(false);
		this.setVisible(true);
		setLayout(new BorderLayout());
		ImageIcon ground = new ImageIcon("code/Pictures/lock.png");
		JLabel image = new JLabel(ground);
		this.add(image);
		this.pack();
		this.setLocationRelativeTo(null);
	}

}
