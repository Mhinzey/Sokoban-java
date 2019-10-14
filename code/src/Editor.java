import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe qui s'occupe de l'editeur de niveau
 * 
 * @author Deryck Olivier, De Visch Justine
 * 
 */
public class Editor extends JPanel implements MouseListener,
		MouseMotionListener, KeyListener {
	String Editeur[][] = new String[21][15];
	String Pictureselect[] = { "WALL", "BOX", "CHARACT", "GOAL", "CHAROK",
			"BOXOK" };
	String DefaultPicture = "WALL";
	int Mx, My;
	int indexInc = 0;
	Image Charact;
	Image Wall;
	Image Box;
	Image Goal;
	Image Charok;
	Image Boxok;
	FileWriter fw;
	FileReader fr;
	ImageIcon ground = new ImageIcon("code/Pictures/play_ground.png");
	JButton box = new JButton("");
	JButton boxok = new JButton("");
	JButton wall = new JButton("");
	JButton charact = new JButton("");
	JButton charactok = new JButton("");
	JButton goal = new JButton("");
	JButton Exit = new JButton("");
	JButton Menu = new JButton("");
	JButton Help = new JButton("");

	/**
	 * Constructeur de l'editeur
	 */
	public Editor() {
		this.setLayout(null);
		ImageIcon sCharact = new ImageIcon("code/Pictures/hero_face.png");
		ImageIcon sCharok = new ImageIcon("code/Pictures/CharOk.png");
		Charact = sCharact.getImage();
		Charok = sCharok.getImage();

		this.add(box);
		box.setBounds(80, 100, 48, 48);
		box.setIcon(new ImageIcon("code/Pictures/box.jpg"));
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultPicture = Pictureselect[1];
				repaint();
				requestFocus();
			}
		});
		this.add(boxok);
		boxok.setBounds(180, 100, 48, 48);
		boxok.setIcon(new ImageIcon("code/Pictures/box_ok.jpg"));
		boxok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultPicture = Pictureselect[5];
				repaint();
				requestFocus();

			}
		});
		this.add(wall);
		wall.setBounds(80, 290, 48, 48);
		wall.setIcon(new ImageIcon("code/Pictures/wall.jpg"));
		wall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultPicture = Pictureselect[0];
				repaint();
				requestFocus();

			}
		});
		this.add(charact);
		charact.setBounds(80, 200, 48, 48);
		charact.setIcon(new ImageIcon("code/Pictures/hero_face.png"));
		charact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultPicture = Pictureselect[2];
				repaint();
				requestFocus();

			}
		});
		this.add(goal);
		goal.setBounds(180, 290, 48, 48);
		goal.setIcon(new ImageIcon("code/Pictures/goal.png"));
		goal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultPicture = Pictureselect[3];
				repaint();
				requestFocus();

			}
		});
		this.add(charactok);
		charactok.setBounds(180, 200, 48, 48);
		charactok.setIcon(new ImageIcon("code/Pictures/CharOk.png"));
		charactok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultPicture = Pictureselect[4];
				repaint();
				requestFocus();

			}
		});
		this.add(Exit);
		Exit.setBounds(1050, 350, 150, 60);
		Exit.setIcon(new ImageIcon("code/Pictures/buttongame_exit.png"));
		this.add(Menu);
		Menu.setBounds(1050, 250, 150, 60);
		Menu.setIcon(new ImageIcon("code/Pictures/buttongame_Menu.png"));
		this.add(Help);
		Help.setBounds(1170, 10, 70, 40);
		Help.setIcon(new ImageIcon("code/Pictures/buttongame_help.png"));

		ImageIcon sBox = new ImageIcon("code/Pictures/box.jpg");
		ImageIcon sBoxok = new ImageIcon("code/Pictures/box_ok.jpg");
		Boxok = sBoxok.getImage();
		Box = sBox.getImage();

		ImageIcon sWall = new ImageIcon("code/Pictures/wall.jpg");
		Wall = sWall.getImage();

		ImageIcon sGoal = new ImageIcon("code/Pictures/goal.png");
		Goal = sGoal.getImage();
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);

		addKeyListener(this);
	}

	/**
	 * Dessine les composants dans la fenetre de jeu
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ground.getImage(), 0, 0, null);

		for (int i = 6; i < 21; i++) {
			for (int j = 0; j < 15; j++) {
				if (Editeur[i][j] == "WALL") {
					g2d.drawImage(Wall, i * 48, j * 48, null);
				}
				if (Editeur[i][j] == "CHARACT") {
					g2d.drawImage(Charact, i * 48, j * 48, null);
				}
				if (Editeur[i][j] == "CHAROK") {
					g2d.drawImage(Charok, i * 48, j * 48, null);
				}
				if (Editeur[i][j] == "BOX") {
					g2d.drawImage(Box, i * 48, j * 48, null);
				}
				if (Editeur[i][j] == "BOXOK") {
					g2d.drawImage(Boxok, i * 48, j * 48, null);
				}
				if (Editeur[i][j] == "GOAL") {
					g2d.drawImage(Goal, i * 48, j * 48, null);
				}
			}
		}
		if (DefaultPicture == "WALL") {
			g2d.drawImage(Wall, Mx, My, null);
		} else if (DefaultPicture == "BOX") {
			g2d.drawImage(Box, Mx, My, null);
		} else if (DefaultPicture == "BOXOK") {
			g2d.drawImage(Boxok, Mx, My, null);
		} else if (DefaultPicture == "CHARACT") {
			g2d.drawImage(Charact, Mx, My, null);
		} else if (DefaultPicture == "CHAROK") {
			g2d.drawImage(Charok, Mx, My, null);
		}

		else if (DefaultPicture == "GOAL") {
			g2d.drawImage(Goal, Mx, My, null);
		}

	}

	/**
	 * Applique des effets selon les touches pressees
	 */
	public void keyPressed(KeyEvent arg0) {
		char key = arg0.getKeyChar();
		if (key == 's') {
			try {
				String dir = (JOptionPane.showInputDialog(null,
						"Choose save path:", "Map editor",
						JOptionPane.QUESTION_MESSAGE));
				fw = new FileWriter("code/Maps/UserLvl/" + dir);
				for (int i = 0; i < 15; i++) {
					for (int j = 6; j < 21; j++) {
						if (Editeur[j][i] == "WALL") {
							fw.write("#");
						} else if (Editeur[j][i] == "CHARACT") {
							fw.write("@");
						} else if (Editeur[j][i] == "CHAROK") {
							fw.write("+");
						} else if (Editeur[j][i] == "BOX") {
							fw.write("$");
						} else if (Editeur[j][i] == "BOXOK") {
							fw.write("*");
						} else if (Editeur[j][i] == "GOAL") {
							fw.write(".");
						} else if (Editeur[j][i] == null) {
							fw.write(" ");
						}
					}
					fw.write("\r\n");
				}
				fw.close();
			} catch (Exception ex) {
			}
		} else if (key == 'l') {
			try {
				String dir = (JOptionPane.showInputDialog(null,
						"Enter reading path:", "Map editor",
						JOptionPane.QUESTION_MESSAGE));
				fr = new FileReader("code/Maps/UserLvl" + dir);
				int x = 6, y = 0, i = 0;

				while ((i = fr.read()) != -1) {
					char Img = (char) i;

					if (Img == '#') {
						Editeur[x][y] = "WALL";
					} else if (Img == '@') {
						Editeur[x][y] = "CHARACT";
					} else if (Img == '+') {
						Editeur[x][y] = "CHAROK";
					} else if (Img == '$') {
						Editeur[x][y] = "BOX";
					} else if (Img == '*') {
						Editeur[x][y] = "BOXOK";
					} else if (Img == '.') {
						Editeur[x][y] = "GOAL";
					} else if (Img == ' ') {
						Editeur[x][y] = null;
					} else if (Img == '\r' || Img == '\n') {
						x--;
					}

					if (x == 20) {
						y++;
						x = 6;
					} else {
						x++;
					}
				}
			} catch (Exception ex) {
			}
		} else if (key == 'r') {
			for (int i = 6; i < 21; i++) {
				for (int j = 0; j < 15; j++) {
					Editeur[i][j]= null;
				}
			}
			repaint();
		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseDragged(MouseEvent arg0) {
	}

	/**
     * Effectue des actions lorsque on bouge la souris.
     */
	public void mouseMoved(MouseEvent arg0) {
		Mx = arg0.getX() - 24;
		My = arg0.getY() - 24;
		repaint();
	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	/**
	 * Permet de poser/enlever une image dans l'editeur lorsqu'on clique
	 */
	public void mouseReleased(MouseEvent arg0) {
		int x = arg0.getX() / 48;
		int y = arg0.getY() / 48;

		if (arg0.getButton() == MouseEvent.BUTTON1) {
			Editeur[x][y] = DefaultPicture;
		} else if (arg0.getButton() == MouseEvent.BUTTON3) {
			Editeur[x][y] = null;
		}
		repaint();
	}

}
