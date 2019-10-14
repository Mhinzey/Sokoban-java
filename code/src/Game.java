import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.AttributedString;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe qui gere les informations relatives au jeu, charge le niveau,gere les
 * deplacements des objets, sauvegarde, ...
 * 
 * @author Deryck Olivier, Justine De Visch
 * 
 */
public class Game extends JPanel implements KeyListener {
	String Game[][] = new String[21][15];
	String Save[][] = new String[21][15];
	int Level = 1;
	private static ArrayList<Wall> Walls;
	private static ArrayList<Goal> Goals;
	private static ArrayList<Box> Boxes;
	private static ArrayList<String> Moves;
	Lvlinfo Lvl;
	Wall Wall;
	Char Char;
	Goal Goal;
	Box Box;
	Font levelFont = new Font("STIX", Font.BOLD, 40);
	FileReader fr, frn;
	FileWriter fw;
	String ip, m, op;
	JButton Exit = new JButton();
	JButton Menu = new JButton();
	JButton Help = new JButton();
	boolean KeySpace;
	int Nbrlvl, Nbrtot;

	/**
	 * Constructeur(dans le cas ou Game ne prend pas d'argument, c'est a dire
	 * lorsque on fait une nouvelle partie/continue une partie/joue un niveau
	 * aleatoire
	 */
	public Game() {
		this.setLayout(null);

		this.setVisible(true);
		LoadLevel();
		setFocusable(true);
		addKeyListener(this);
		Replaylevel();
		CheckLevelDone();
		this.add(Exit);
		Exit.setBounds(1050, 350, 150, 60);
		Exit.setIcon(new ImageIcon("code/Pictures/buttongame_exit.png"));
		this.add(Menu);
		Menu.setBounds(1050, 250, 150, 60);
		Menu.setIcon(new ImageIcon("code/Pictures/buttongame_Menu.png"));
		this.add(Help);
		Help.setBounds(1170, 10, 70, 40);
		Help.setIcon(new ImageIcon("code/Pictures/buttongame_help.png"));

	}
	/**
	 * Constructeur dans le cas où c'est l'utilisateur qui sélectionne le niveau qu'il souhaite jouer.
	 * @param input	La chaine de caractère qui est le nom du niveau que l'utilisateur sélectionne.
	 */
	public Game(String input) {
		ip = "code/Maps/UserLvl/" + input;
		this.setLayout(null);
		this.setVisible(true);
		Lvl.setN(4);
		LoadLevel();
		setFocusable(true);
		addKeyListener(this);
		CheckLevelDone();
		this.add(Exit);
		Exit.setBounds(1050, 350, 150, 60);
		Exit.setIcon(new ImageIcon("code/Pictures/buttongame_exit.png"));
		this.add(Menu);
		Menu.setBounds(1050, 250, 150, 60);
		Menu.setIcon(new ImageIcon("code/Pictures/buttongame_Menu.png"));
		this.add(Help);
		Help.setBounds(1170, 10, 70, 40);
		Help.setIcon(new ImageIcon("code/Pictures/buttongame_help.png"));

	}

	/**
	 * Constructeur(dans le cas ou Game prend en compte des parametres, c'est a
	 * dire lors de l'application d'un .mov a un niveau, soient les test
	 * unitaires et le test en ligne de commande)
	 * 
	 * @param input
	 *            , une chaine de caractere qui doit etre le fichier d'entree
	 * @param moves
	 *            , une chaine de caracteres qui doit etre le fichier
	 *            contenenant les mouvements a appliquer au niveau
	 * @param output
	 *            , une chaine de caracteres qui doit etre le fichier de niveau
	 *            une fois les mouvements appliques
	 */
	public Game(String input, String moves, String output) {
		ip = "code/Maps/" + input;
		m = "code/Mov/" + moves;
		op = output;
		Lvl.setN(5);
		LoadLevel();
		Automove();
		CheckCollision();
		CheckLevelDone();

	}

	/**
	 * obtient un n de la classe Lvlinfo, ce n sert a definir les instructions
	 * selon le type de partie (Nouvelle partie, continuer,aleatoire ou encore
	 * test unitaire)
	 * 
	 * @return l'indice n selon le type de partie
	 */
	private int n() {
		return Lvl.getN();
	}

	/**
	 * Fonction qui charge le niveau depuis un fichier dans des arraylist
	 * 
	 * @exception erreur
	 *                si on ne peut pas lire le fichier définit
	 */
	public void LoadLevel() {

		try {
			Walls = new ArrayList<Wall>();
			Boxes = new ArrayList<Box>();
			Goals = new ArrayList<Goal>();
			if (n() == 1) {
				fr = new FileReader("code/Maps/Level_" + Level + ".xsb");
			} else if (n() == 2) {
				fr = new FileReader("code/Maps/alea.xsb");
			} else if (n() == 3) {
				fr = new FileReader("code/Maps/autosave");
				frn = new FileReader("code/Maps/autosavelvl");
				BufferedReader br = new BufferedReader(frn);
				Level = Integer.parseInt(br.readLine());
				frn = new FileReader("code/Maps/autosavepaslvl");
				BufferedReader br1 = new BufferedReader(frn);
				Nbrlvl = Integer.parseInt(br1.readLine());
				frn = new FileReader("code/Maps/autosavepastot");
				BufferedReader br2 = new BufferedReader(frn);
				Nbrtot = Integer.parseInt(br2.readLine());
				Lvlinfo.setN(1);

			}

			else if (n() == 5 || n() == 4) {
				fr = new FileReader(ip);
			} else if (n() >= 6) {
				Level = n() - 5;
				fr = new FileReader("code/Maps/Level_" + Level + ".xsb");
				Lvlinfo.setN(1);

			}

			int x = 6, y = 0, i = 0;

			while ((i = fr.read()) != -1) {
				char Img = (char) i;
				if (Img == '#') {
					Game[x][y] = "WALL";
					Wall = new Wall(x * 48, y * 48);
					Walls.add(Wall);
				} else if (Img == '@') {
					Game[x][y] = "CHARACT";
					Char = new Char(x * 48, y * 48);
				} else if (Img == '$') {
					Game[x][y] = "BOX";
					Box = new Box(x * 48, y * 48);
					Boxes.add(Box);
				} else if (Img == '.') {
					Game[x][y] = "GOAL";
					Goal = new Goal(x * 48, y * 48);
					Goals.add(Goal);
				} else if (Img == '*') {
					Game[x][y] = "BOXOK";
					Box = new Box(x * 48, y * 48);
					Boxes.add(Box);
					Goal = new Goal(x * 48, y * 48);
					Goals.add(Goal);
				} else if (Img == '+') {
					Game[x][y] = "CHARACTOK";
					Char = new Char(x * 48, y * 48);
					Goal = new Goal(x * 48, y * 48);
					Goals.add(Goal);

				}

				else if (Img == ' ') {
					Game[x][y] = null;
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
			CheckLevelDone();

		} catch (Exception ex) {
		}
		repaint();
	}

	/**
	 * dessine les objets du jeu en recuperant les images et les coordonnees
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		ImageIcon ground = new ImageIcon("code/Pictures/play_ground.png");

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ground.getImage(), 0, 0, null);
		for (int i = 0; i < Goals.size(); i++) {
			Goal = (Goal) Goals.get(i);
			g2d.drawImage(Goal.getImage(), Goal.getX(), Goal.getY(), null);
		}
		for (int i = 0; i < Walls.size(); i++) {
			Wall = (Wall) Walls.get(i);
			g2d.drawImage(Wall.getImage(), Wall.getX(), Wall.getY(), null);
		}

		for (int i = 0; i < Boxes.size(); i++) {
			Box = (Box) Boxes.get(i);
			g2d.drawImage(Box.getImage(), Box.getX(), Box.getY(), null);
		}
		g2d.drawImage(Char.getImage(), Char.getX(), Char.getY(), null);

		g.setColor(Color.BLACK);
		g.setFont(levelFont);

		g.setColor(Color.BLACK);
		g.setFont(levelFont);
		g.drawString("LEVEL: " + Level, 65, 150);

		AttributedString text = new AttributedString("Moves done in this level");
		text.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 15),
				0, "Moves done in this level".length());
		g.drawString(text.getIterator(), 70, 200);
		g.drawString("" + Nbrlvl, 150, 250);

		AttributedString text2 = new AttributedString("Moves done in the game");
		text2.addAttribute(TextAttribute.FONT,
				new Font("Arial", Font.BOLD, 15), 0,
				"Moves done in the game".length());
		g.drawString(text2.getIterator(), 70, 300);
		g.drawString("" + Nbrtot, 150, 350);

	}

	public void keyPressed(KeyEvent arg0) {
		int Touche = arg0.getKeyCode();
		if (Touche == KeyEvent.VK_SPACE)
			KeySpace = true;

	}

	/**
	 * Realise les actions quand on appuie sur les touches du clavier
	 */

	public void keyReleased(KeyEvent arg0) {
		Moves = new ArrayList<String>();
		int Touche = arg0.getKeyCode();
		if (Touche == KeyEvent.VK_DOWN && KeySpace == false) {
			Char.setDir("DOWN");
			Char.Move();
			CheckCollision();
			Moves.add("1");
			Replaylevel();

		} else if (Touche == KeyEvent.VK_UP && KeySpace == false) {
			Char.setDir("UP");
			Char.Move();
			CheckCollision();
			Moves.add("2");
			Replaylevel();

		} else if (Touche == KeyEvent.VK_RIGHT && KeySpace == false) {
			Char.setDir("RIGHT");
			Char.Move();
			CheckCollision();
			Moves.add("3");
			Replaylevel();

		} else if (Touche == KeyEvent.VK_LEFT && KeySpace == false) {
			Char.setDir("LEFT");
			Char.Move();
			CheckCollision();
			Moves.add("4");
			Replaylevel();

		} else if (Touche == KeyEvent.VK_R) {
			LoadLevel();
			Nbrlvl = 0;
		} else if (Touche == KeyEvent.VK_N && n() == 2) {
			RandomLvl rlvl = new RandomLvl();

			LoadLevel();
		} else if (Touche == KeyEvent.VK_SPACE)
			KeySpace = false;
		if (Touche == KeyEvent.VK_DOWN && KeySpace == true) {
			Char.setDir("DOWN");
			Char.Move();
			CheckTCollision();

		} else if (Touche == KeyEvent.VK_UP && KeySpace == true) {
			Char.setDir("UP");
			Char.Move();
			CheckTCollision();

		}
		if (Touche == KeyEvent.VK_RIGHT && KeySpace == true) {
			Char.setDir("RIGHT");
			Char.Move();
			CheckTCollision();

		}
		if (Touche == KeyEvent.VK_LEFT && KeySpace == true) {
			Char.setDir("LEFT");
			Char.Move();
			CheckTCollision();

		}
		repaint();
		CheckLevelDone();

		if (n() != 2 && n() != 4) {

			try {
				frn = new FileReader("code/Maps/autosavelvl");
				BufferedReader br = new BufferedReader(frn);
				int maxlvl = Integer.parseInt(br.readLine());

				if (Level >= maxlvl) {
					Autosave();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Applique des mouvements depuis un fichier lu, utilisé lors des tests
	 * unitaires
	 */
	void Automove() {
		try {
			fr = new FileReader(m);
			int i;
			while ((i = fr.read()) != -1) {
				char Img = (char) i;
				if (Img == '1') {
					Char.setDir("DOWN");
					Char.Move();
					CheckCollision();
				} else if (Img == '2') {
					Char.setDir("UP");
					Char.Move();
					CheckCollision();
				} else if (Img == '3') {
					Char.setDir("RIGHT");
					Char.Move();
					CheckCollision();
				} else if (Img == '4') {
					Char.setDir("LEFT");
					Char.Move();
					CheckCollision();
				}
				CheckLevelDone();
				Autosave();
			}
		} catch (Exception e) {
		}
	}

	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * on verifie si le niveau est fini, c'est a dire si toutes les caisses sont
	 * sur les objectifs
	 */
	public void CheckLevelDone() {
		int nbBoxOK = 0;
		if ((Goals.size() != Boxes.size())) {
			RandomLvl rlvl = new RandomLvl();
			LoadLevel();
		}
		if ((Goals.size() == 0)) {
			RandomLvl rlvl = new RandomLvl();
			LoadLevel();
		} else {
			for (int i = 0; i < Boxes.size(); i++) {
				Box = (Box) Boxes.get(i);
				for (int j = 0; j < Goals.size(); j++) {
					Goal = (Goal) Goals.get(j);
					if ((Box.getX() == Goal.getX())
							&& Box.getY() == Goal.getY()) {
						Box.setState("OK");
						nbBoxOK++;
					}
				}

				if (nbBoxOK == Boxes.size()) {

					Level++;
					Nbrlvl = 0;
					if (n() == 4) {
						Level = 1;

					}
					if (n() == 1 || n() == 3) {
						if (Level == 11) {
							Level = 1;
						}
					} else if (n() == 2) {
						RandomLvl rlvl = new RandomLvl();
					}
					Moves.clear();

					LoadLevel();
				}
			}
		}
	}

	/**
	 * Verifie les collisions d'un objet avec un autre
	 */
	public void CheckCollision() {
		Rectangle CharRec = Char.getBounds();

		Nbrlvl++;
		Nbrtot++;

		for (int i = 0; i < Walls.size(); i++) {
			Wall = (Wall) Walls.get(i);
			Rectangle WallRec = Wall.getBounds();

			if (CharRec.intersects(WallRec)) {
				if (Char.getDir() == "DOWN") {
					Char.setY(Char.getY() - 48);

				} else if (Char.getDir() == "UP") {
					Char.setY(Char.getY() + 48);

				} else if (Char.getDir() == "LEFT") {
					Char.setX(Char.getX() + 48);

				} else if (Char.getDir() == "RIGHT") {
					Char.setX(Char.getX() - 48);

				}
				Nbrlvl--;
				Nbrtot--;

			}

		}

		for (int i = 0; i < Boxes.size(); i++) {
			Box = (Box) Boxes.get(i);
			Rectangle BoxRec = Box.getBounds();
			if (CharRec.intersects(BoxRec)) {
				if (Char.getDir() == "DOWN") {

					if ((Game[BoxRec.x / 48][(BoxRec.y + 48) / 48] != "WALL")
							&& (Game[BoxRec.x / 48][(BoxRec.y + 48) / 48] != "BOX")
							&& (Game[BoxRec.x / 48][(BoxRec.y + 48) / 48] != "BOXOK")) {
						Box.setY(Box.getY() + 48);
						Game[CharRec.x / 48][(CharRec.y) / 48] = "CHARACT";
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;
						Game[BoxRec.x / 48][(BoxRec.y + 48) / 48] = "BOX";

					} else {
						Nbrlvl--;
						Nbrtot--;
						Char.setY(Char.getY() - 48);
					}
				}

				else if (Char.getDir() == "UP") {
					if ((Game[BoxRec.x / 48][(BoxRec.y - 48) / 48] != "WALL")
							&& (Game[BoxRec.x / 48][(BoxRec.y - 48) / 48] != "BOX")
							&& (Game[BoxRec.x / 48][(BoxRec.y - 48) / 48] != "BOXOK")) {
						Box.setY(Box.getY() - 48);
						Game[CharRec.x / 48][(CharRec.y) / 48] = "CHARACT";
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;
						Game[BoxRec.x / 48][(BoxRec.y - 48) / 48] = "BOX";
					} else {
						Char.setY(Char.getY() + 48);
						Nbrlvl--;
						Nbrtot--;

					}
				} else if (Char.getDir() == "LEFT") {
					if ((Game[(BoxRec.x - 48) / 48][(BoxRec.y) / 48] != "WALL")
							&& (Game[(BoxRec.x - 48) / 48][(BoxRec.y) / 48] != "BOX")
							&& (Game[(BoxRec.x - 48) / 48][(BoxRec.y) / 48] != "BOXOK")) {
						Box.setX(Box.getX() - 48);
						Game[CharRec.x / 48][(CharRec.y) / 48] = "CHARACT";
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;
						Game[(BoxRec.x - 48) / 48][(BoxRec.y) / 48] = "BOX";
					} else {
						Char.setX(Char.getX() + 48);
						Nbrlvl--;
						Nbrtot--;

					}
				} else if (Char.getDir() == "RIGHT") {
					if ((Game[(BoxRec.x + 48) / 48][(BoxRec.y) / 48] != "WALL")
							&& (Game[(BoxRec.x + 48) / 48][(BoxRec.y) / 48] != "BOX")
							&& (Game[(BoxRec.x + 48) / 48][(BoxRec.y) / 48] != "BOXOK")) {
						Box.setX(Box.getX() + 48);
						Game[(CharRec.x) / 48][(CharRec.y) / 48] = "CHARACT";
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;
						Game[(BoxRec.x + 48) / 48][(BoxRec.y) / 48] = "BOX";
					} else {
						Char.setX(Char.getX() - 48);
						Nbrlvl--;
						Nbrtot--;

					}
				}
			}
		}

		for (int j = 0; j < Goals.size(); j++) {
			Goal = (Goal) Goals.get(j);
			Rectangle GoalRec = Goal.getBounds();
			for (int k = 0; k < Boxes.size(); k++) {
				Box = (Box) Boxes.get(j);
				Rectangle BoxRec = Box.getBounds();
				if (!BoxRec.intersects(GoalRec)) {
					Box.setState("NORMAL");
				}
			}
		}
	}
	/**
	 * Vérifie les collisions lorsque le mouvement choisi est le mouvement de traction.
	 */
	public void CheckTCollision() {
		Rectangle CharRec = Char.getBounds();
		Nbrlvl++;
		Nbrtot++;
		for (int i = 0; i < Walls.size(); i++) {
			Wall = (Wall) Walls.get(i);
			Rectangle WallRec = Wall.getBounds();

			if (CharRec.intersects(WallRec)) {
				if (Char.getDir() == "DOWN") {
					Char.setY(Char.getY() - 48);
					Nbrlvl--;
					Nbrtot--;

				} else if (Char.getDir() == "UP") {
					Char.setY(Char.getY() + 48);
					Nbrlvl--;
					Nbrtot--;

				} else if (Char.getDir() == "LEFT") {
					Char.setX(Char.getX() + 48);
					Nbrlvl--;
					Nbrtot--;

				} else if (Char.getDir() == "RIGHT") {
					Char.setX(Char.getX() - 48);
					Nbrlvl--;
					Nbrtot--;

				}
			}
		}

		for (int i = 0; i < Boxes.size(); i++) {
			Box = (Box) Boxes.get(i);
			Rectangle BoxRec = Box.getBounds();
			if (CharRec.intersects(BoxRec)) {
				if (Char.getDir() == "DOWN") {
					Char.setY(Char.getY() - 48);
					Nbrlvl--;
					Nbrtot--;
				} else if (Char.getDir() == "UP") {
					Char.setY(Char.getY() + 48);
					Nbrlvl--;
					Nbrtot--;
				} else if (Char.getDir() == "LEFT") {

					Char.setX(Char.getX() + 48);
					Nbrlvl--;
					Nbrtot--;
				} else if (Char.getDir() == "RIGHT") {

					Char.setX(Char.getX() - 48);
					Nbrlvl--;
					Nbrtot--;

				}
			}
		}
		for (int i = 0; i < Boxes.size(); i++) {
			Box = (Box) Boxes.get(i);
			Rectangle BoxRec = Box.getBounds();
			if (!CharRec.intersects(BoxRec)) {
				if (Char.getDir() == "DOWN") {
					if (((Char.getX()) == Box.getX())
							&& (Box.getY() == Char.getY() - 96)) {
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;

						Box.setY(Box.getY() + 48);
						Game[(CharRec.x) / 48][(CharRec.y) / 48] = "CHARACT";
						Game[(BoxRec.x) / 48][(BoxRec.y + 48) / 48] = "BOX";
					}
				} else if (Char.getDir() == "UP") {
					if (((Char.getX()) == Box.getX())
							&& (Char.getY() + 96 == Box.getY())) {
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;
						Box.setY(Box.getY() - 48);
						Game[(CharRec.x) / 48][(CharRec.y) / 48] = "CHARACT";

						Game[(BoxRec.x) / 48][(BoxRec.y - 48) / 48] = "BOX";
					}
				} else if (Char.getDir() == "LEFT") {
					if (((Char.getX() + 96) == Box.getX())
							&& (Box.getY() == Char.getY())) {
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;

						Box.setX(Box.getX() - 48);
						Game[(CharRec.x) / 48][(CharRec.y) / 48] = "CHARACT";
						Game[(BoxRec.x - 48) / 48][(BoxRec.y) / 48] = "BOX";
					}
				} else if (Char.getDir() == "RIGHT") {
					if (((Char.getX() - 96) == Box.getX())
							&& (Box.getY() == Char.getY())) {
						Game[BoxRec.x / 48][(BoxRec.y) / 48] = null;

						Box.setX(Box.getX() + 48);
						Game[(CharRec.x) / 48][(CharRec.y) / 48] = "CHARACT";
						Game[(BoxRec.x + 48) / 48][(BoxRec.y) / 48] = "BOX";
					}
				}
			}
		}

		for (int j = 0; j < Goals.size(); j++) {
			Goal = (Goal) Goals.get(j);
			Rectangle GoalRec = Goal.getBounds();
			for (int k = 0; k < Boxes.size(); k++) {
				Box = (Box) Boxes.get(j);
				Rectangle BoxRec = Box.getBounds();
				if (!BoxRec.intersects(GoalRec)) {
					Box.setState("NORMAL");

				}
			}

		}

	}

	/**
	 * Sauvegarde l'état de la partie actuelle
	 */
	public void Autosave() {
		if (n() != 2) {
			try {
				if (n() == 5) {
					fw = new FileWriter(op);
				} else {
					fw = new FileWriter("code/Maps/autosave");
				}

				for (int i = 0; i < Game.length; i++) {
					for (int j = 0; j < Game[i].length; j++) {
						if (Game[i][j] == "CHARACT" || Game[i][j] == "CHAROK") {
							Game[i][j] = null;
						}
					}
				}
				for (int i = 0; i < Goals.size(); i++) {
					Goal = Goals.get(i);
					Game[Goal.getX() / 48][Goal.getY() / 48] = "GOAL";
					if ((Char.getX() == Goal.getX())
							&& (Char.getY() == Goal.getY())) {
						Game[Goal.getX() / 48][Goal.getY() / 48] = "CHAROK";
					}

				}
				for (int i = 0; i < Boxes.size(); i++) {
					Box = Boxes.get(i);
					if (Box.getState() == "OK") {
						Game[Box.getX() / 48][Box.getY() / 48] = "BOXOK";
					} else {
						Game[Box.getX() / 48][Box.getY() / 48] = "BOX";

					}
				}
				if (Game[Char.getX() / 48][Char.getY() / 48] == null) {
					Game[Char.getX() / 48][Char.getY() / 48] = "CHARACT";
				}

				for (int i = 0; i < 15; i++) {
					for (int j = 6; j < 21; j++) {
						if (Game[j][i] == "WALL") {
							fw.write("#");
						} else if (Game[j][i] == "GOAL") {
							fw.write(".");
						} else if (Game[j][i] == "CHARACT") {
							fw.write("@");
						} else if (Game[j][i] == "BOX") {
							fw.write("$");
						} else if (Game[j][i] == null) {
							fw.write(" ");
						} else if (Game[j][i] == "CHAROK") {
							fw.write("+");
						} else if (Game[j][i] == "BOXOK") {
							fw.write("*");
						}
					}

					fw.write("\r\n");
				}
				fw.close();
			} catch (Exception ex) {
			}
			try {
				fw = new FileWriter("code/Maps/autosavelvl");

				String lvl = Integer.toString(Level);

				fw.write(lvl);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fw = new FileWriter("code/Maps/autosavepaslvl");

				String lvl = Integer.toString(Nbrlvl);

				fw.write(lvl);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fw = new FileWriter("code/Maps/autosavepastot");

				String lvl = Integer.toString(Nbrtot);

				fw.write(lvl);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sauvegarde les mouvements qui ont été réalisés pour finir un niveau
	 */
	public void Replaylevel() {
		try {

			fw = new FileWriter("code/Mov/Level" + Level + ".mov");

			for (int i = 0; i < Moves.size(); i++) {
				if (Moves.get(i) == "1") {
					fw.write("1");
				} else if (Moves.get(i) == "2") {
					fw.write("2");
				} else if (Moves.get(i) == "3") {
					fw.write("3");
				} else if (Moves.get(i) == "4") {
					fw.write("4");
				}
			}
			fw.write("\r\n");
			fw.close();
		} catch (Exception ex) {
		}
	}
}
