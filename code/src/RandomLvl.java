import java.awt.Rectangle;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe qui crée un niveau aléatoire et jouable
 * 
 * @author Deryck Olivier, De Visch Justine
 * 
 */
public class RandomLvl {
	String Game[][] = new String[15][15];
	private static ArrayList<Wall> Walls;
	private static ArrayList<Goal> Goals;
	private static ArrayList<Box> Boxes;
	Wall Wall;
	Char Char;
	Goal Goal;
	Box Box;
	FileReader fr;
	FileWriter fw;
	int nbBoxOK = 0;

	/**
	 * Constructeur des niveaux aléatoires
	 * 
	 * 
	 * 
	 */
	public RandomLvl() {
		Generate();
		RandMoves();
		LoadLevel();
		CheckBoxes();
		Automove();
		Autosave();
	}

	/**
	 * Crée un fichier contenants des mouvements aléatoires
	 */

	public void RandMoves() {
		try {
			fw = new FileWriter("code/Mov/alea.mov");
			int nb = 100 + (int) (Math.random() * 400);
			while (nb != 0) {
				int m = (1 + (int) (Math.random() * 4));
				if (m == 1) {
					fw.write("1");
				} else if (m == 2) {
					fw.write("2");
				} else if (m == 3) {
					fw.write("3");
				} else if (m == 4) {
					fw.write("4");
				}
				nb--;
			}
			fw.flush();
			fw.close();
		} catch (Exception e) {

		}
	}

	/**
	 * Met les caisses en état OK si elles sont sur un objectif
	 */
	public void CheckBoxes() {

		for (int i = 0; i < Boxes.size(); i++) {
			Box = (Box) Boxes.get(i);
			for (int j = 0; j < Goals.size(); j++) {
				Goal = (Goal) Goals.get(j);
				if ((Box.getX() == Goal.getX()) && Box.getY() == Goal.getY()) {
					Box.setState("OK");
					nbBoxOK++;
				}
			}
		}
	}

	/**
	 * Simule une partie en lisant le niveau aléatoirement créé
	 */
	public void LoadLevel() {

		try {
			Walls = new ArrayList<Wall>();
			Boxes = new ArrayList<Box>();
			Goals = new ArrayList<Goal>();

			fr = new FileReader("code/Maps/alea1.xsb");
			int x = 0, y = 0, i = 0;

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
				if (x == 14) {
					y++;
					x = 0;
				} else {
					x++;
				}

			}

		} catch (Exception ex) {
		}
	}

	/**
	 * Realise les des mouvements depuis la lecture du fichier .mov
	 */
	void Automove() {
		try {
			fr = new FileReader("code/Mov/alea.mov");
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
				CheckBoxes();
			}
		} catch (Exception e) {
		}

	}

	/**
	 * Verifie les collisions d'un objet avec un autre quand le personnage tire
	 * une caisse
	 */
	public void CheckCollision() {
		Rectangle CharRec = Char.getBounds();

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
			}
		}

		for (int i = 0; i < Boxes.size(); i++) {
			Box = (Box) Boxes.get(i);
			Rectangle BoxRec = Box.getBounds();
			if (CharRec.intersects(BoxRec)) {
				if (Char.getDir() == "DOWN") {
					Char.setY(Char.getY() - 48);
				} else if (Char.getDir() == "UP") {
					Char.setY(Char.getY() + 48);
				} else if (Char.getDir() == "LEFT") {

					Char.setX(Char.getX() + 48);
				} else if (Char.getDir() == "RIGHT") {

					Char.setX(Char.getX() - 48);

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
	 * Sauvegarde l'état du niveau
	 */
	public void Autosave() {
		try {
			fw = new FileWriter("code/Maps/alea.xsb");
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (Game[i][j] == "CHARACT" || Game[i][j] == "CHAROK") {
						Game[i][j] = null;
					}
				}
			}
			if (Game[Char.getX() / 48][Char.getY() / 48] == null) {
				Game[Char.getX() / 48][Char.getY() / 48] = "CHARACT";
			}
			for (int i = 0; i < Goals.size(); i++) {
				Goal = Goals.get(i);
				Game[Goal.getX() / 48][Goal.getY() / 48] = "GOAL";
				if ((Char.getX() == Goal.getX())
						&& (Char.getY() == Goal.getY())) {
					Game[Char.getX() / 48][Char.getY() / 48] = "CHAROK";
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
			for (int i = 0; i < Walls.size(); i++) {
				Wall = Walls.get(i);
				Game[(Wall.getX() / 48)][Wall.getY() / 48] = "WALL";
			}

			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
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
			fw.flush();
			fw.close();
		} catch (Exception ex) {
		}
	}

	/**
	 * Génère le niveau aléatoire
	 */
	public void Generate() {
		try {
			fw = new FileWriter("code/Maps/alea1.xsb");

			int k = 1 + (int) (Math.random() * 2);

			int z = 11 + (int) (Math.random() * 4);
			for (int i = 0; i <= 59; i++) {
				if (i / 15 == 0) {
					int x = (int) (Math.random() * 3);
					Game[i % 15][z] = "WALL";
					z = z + x - 1;
					if (z >= 15) {
						z--;
					} else if (z <= 11) {
						z++;
					}

				} else if (i / 15 == 2) {
					int x = (int) (Math.random() * 3);
					Game[k][(i % 15)] = "WALL";
					k = k + x - 1;
					if (k >= 3) {
						k--;
					} else if (k <= 0) {
						k++;
					}
				} else if (i / 15 == 1) {
					int x = (int) (Math.random() * 3);
					Game[(i % 15)][k] = "WALL";

					k = k + x - 1;
					if (k >= 3) {
						k--;
					} else if (k <= 0) {
						k++;
					}
				} else if (i / 15 == 3) {
					int x = (int) (Math.random() * 3);
					Game[z][(i % 15)] = "WALL";
					z = z + x - 1;
					if (z >= 15) {
						z--;
					} else if (z <= 11) {
						z++;
					}
				}

			}
			int q = 2 + (int) (Math.random() * 3);
			int o = 0;
			while (o != q) {
				int x = 4 + (int) (Math.random() * 8);
				int y = 4 + (int) (Math.random() * 8);
				if (Game[x][y] == null) {
					Game[x][y] = "BOXOK";
					o++;
				} else {
					x = 4 + (int) (Math.random() * 8);
					y = 4 + (int) (Math.random() * 8);
				}
			}

			int p = 0;
			while (p != 1) {
				int x = 5 + (int) (Math.random() * 3);
				int y = 5 + (int) (Math.random() * 3);
				if (Game[x][y] == null) {
					Game[x][y] = "CHARACT";
					p++;
				} else {
					x = 5 + (int) (Math.random() * 3);
					y = 5 + (int) (Math.random() * 3);

				}
			}
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (Game[i][j] == null) {
						int x = (int) (Math.random() * 6);
						if (x == 3) {
							Game[i][j] = "WALL";
						}
					}
				}
			}
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (Game[i][j] == "WALL") {
						fw.write("#");
					}

					else if (Game[i][j] == null) {
						fw.write(" ");
					} else if (Game[i][j] == "BOXOK") {
						fw.write("*");
					} else if (Game[i][j] == "CHARACT") {
						fw.write("@");
					}
				}
				fw.write("\r\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
