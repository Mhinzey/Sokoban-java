import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe qui g�re la fen�tre de jeu
 * 
 * @author Deryck Olivier, De Visch Justine
 * 
 */

public class GameFrame extends JFrame implements KeyListener, ActionListener {
	Menu menu = new Menu();
	Editor edit = new Editor();
	RandomLvl rlvl = new RandomLvl();
	Lvlinfo lvl = new Lvlinfo();
	Game game = new Game();
	String ip;
	Game gameSelect = new Game(ip);
	BasicLevel bl = new BasicLevel();
	int Level;
	Lvlinfo li = new Lvlinfo();
	FileReader frn, fr;
	FileWriter fw;
	boolean success = false;
	Skin skin=new Skin();
	NewSkin newskin = new NewSkin();

	/**
	 * Constructeur de la classe, cree les boutons et les actions associees
	 */
	public GameFrame() {
		setFocusable(true);
		addKeyListener(this);
		Image icone = Toolkit.getDefaultToolkit()
				.getImage("code/Pictures/icone.png");
		this.setIconImage(icone);
		this.setTitle("Sokoban");
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(menu);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		try {
			frn = new FileReader("code/Maps/autosavelvl");
			BufferedReader br = new BufferedReader(frn);
			Level = Integer.parseInt(br.readLine());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		menu.bCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getContentPane().removeAll();
				add(skin);
				repaint();
				revalidate();
				skin.casual.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewSkin.setN(1);				
					}
					});
				
				skin.angel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewSkin.setN(2);				
				}
				});
				skin.demon.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewSkin.setN(3);				
					}
					});
				skin.kitchen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewSkin.setN(4);				
					}
					});
				skin.garden.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewSkin.setN(5);				
					}
					});
				
				skin.Exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						
					}
				});
				skin.Menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						add(menu);
						menu.requestFocus();
						repaint();
						revalidate();
					}
				});
			}
		});
		
		
		
		
		
		
		
		
		
		menu.bSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ip = JOptionPane.showInputDialog(null, "Choose Level path:",
						"Level Select", JOptionPane.QUESTION_MESSAGE);
				try {
					fr = new FileReader("code/Maps/UserLvl/" + ip);
					success = true;
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				if (success == true) {
					if (ip != null) {
						lvl.setN(4);
						final Game game = new Game(ip);
						getContentPane().removeAll();
						add(game);
						game.requestFocus();
						repaint();
						revalidate();
						game.Exit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
							}
						});
						game.Help.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								lvl.getN();
								new Help(GameFrame.this,"Help",true);
								game.requestFocus();

							}
							
						});

						game.Menu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								getContentPane().removeAll();
								add(menu);
								menu.requestFocus();
								repaint();
								revalidate();
							}
						});

					}
				}
			}
		});
		menu.bNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lvl.setN(1);
				try {
					fw = new FileWriter("code/Maps/autosavelvl");

					String lvl = Integer.toString(1);

					fw.write(lvl);
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				final Game game = new Game();
				getContentPane().removeAll();
				add(game);
				game.requestFocus();
				repaint();
				revalidate();
				game.Exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				game.Help.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						lvl.getN();
						new Help(GameFrame.this,"Help",true);
						game.requestFocus();

					}
					
				});
				game.Menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						add(menu);
						menu.requestFocus();
						repaint();
						revalidate();
					}
				});
			}
		});
		menu.bEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lvl.setN(0);
				getContentPane().removeAll();
				add(edit);
				edit.requestFocus();
				repaint();
				revalidate();
			}
		});
		menu.bExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.bChooseLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				try {
					frn = new FileReader("code/Maps/autosavelvl");
					BufferedReader br = new BufferedReader(frn);
					Level = Integer.parseInt(br.readLine());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				add(bl);
				repaint();
				revalidate();
				

			}

		});
		bl.Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		bl.Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getContentPane().removeAll();
				
				add(menu);
				menu.requestFocus();
				repaint();
				revalidate();
			}
		});
		
		bl.b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lvlinfo.setN(6);
				final Game game = new Game();
				getContentPane().removeAll();
				add(game);
				game.requestFocus();
				repaint();
				revalidate();
				game.Exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				game.Help.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						lvl.getN();
						new Help(GameFrame.this,"Help",true);
						game.requestFocus();

					}
					
				});
				game.Menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						add(menu);
						menu.requestFocus();
						revalidate();
						repaint();
						
					}
				});
			}
		});

		bl.b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 2) {

					Lvlinfo.setN(7);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
						
					}
				}
			}
		});
		bl.b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 3) {
					Lvlinfo.setN(8);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bl.b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 4) {
					Lvlinfo.setN(9);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();
						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bl.b5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (Level >= 5) {
					Lvlinfo.setN(10);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bl.b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 6) {
					Lvlinfo.setN(11);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bl.b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 7) {
					Lvlinfo.setN(12);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bl.b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 8) {
					Lvlinfo.setN(13);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bl.b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 9) {
					Lvlinfo.setN(14);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

							
						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bl.b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Level >= 10) {
					Lvlinfo.setN(15);
					final Game game = new Game();
					getContentPane().removeAll();
					add(game);
					game.requestFocus();
					repaint();
					revalidate();
					game.Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					game.Help.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							lvl.getN();
							new Help(GameFrame.this,"Help",true);
							game.requestFocus();

						}
						
					});
					game.Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getContentPane().removeAll();
							add(menu);
							menu.requestFocus();
							repaint();
							revalidate();
						}
					});
				} else {
					try {
						BlockedLvl error = new BlockedLvl();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		menu.bRand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lvl.setN(2);
				final Game game = new Game();
				getContentPane().removeAll();
				add(game);
				game.requestFocus();
				repaint();
				revalidate();
				game.Exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				game.Help.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						lvl.getN();
						new Help(GameFrame.this,"Help",true);
						game.requestFocus();
					}
					
				});
				game.Menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						add(menu);
						menu.requestFocus();
						repaint();
						revalidate();
					}
				});
			}
		});
		menu.bContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lvl.setN(3);
				final Game game = new Game();
				getContentPane().removeAll();
				add(game);
				game.requestFocus();
				repaint();
				revalidate();
				game.Exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				game.Help.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						lvl.getN();
						new Help(GameFrame.this,"Help",true);
						game.requestFocus();
					}
					
				});
				game.Menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						add(menu);
						menu.requestFocus();
						repaint();
						revalidate();
					}
				});
			}
		});
		edit.Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		edit.Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				add(menu);
				repaint();
				revalidate();
			}
		});
		edit.Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						lvl.getN();
						new Help(GameFrame.this,"Help",true);
						edit.requestFocus();
			}			
		});
	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void keyPressed(KeyEvent arg0) {

	}

	/**
	 * Effectue des actions lorsqu'on appuie sur certaines touches du clavier
	 */
	public void keyReleased(KeyEvent arg0) {
		int Touche = arg0.getKeyCode();
		if (Touche == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

	}

	public void keyTyped(KeyEvent arg0) {

	}


}
