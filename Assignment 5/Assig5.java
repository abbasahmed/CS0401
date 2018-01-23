/**CS0401 Intermediate programming with Java
 * 
 * CS401 Assignment 5
 * 
 * Name: Abbas Z Ahmed
 * Peoplesoft No. 4165368
 * Pitt Email ID: aza16@pitt.edu
 * 
 * Professor Ramirez 
 * 
 * Classes: Monday and Thursday at 1 PM - 2:15 PM
 * Lab: Thursday 10 AM-11:50AM
 * Lab Instructor: Zinan Zhuang
 * 
 * The purpose of this project is to create a graphical program that lets us draw our own objects, erase them, edit them and  
 * a lot of other functionality. The main purpose, ultimately, is to use GUIs in a programs.
 * 
 */

/**
 * @author abbas
 *
 */

// CS 0401 Fall 2016
// Assignment 5 "starter" program.  You may use this as a starting point for
// Assignment 5.  

// Note 1: You will only receive credit for your additions and not for any of
// the functionality that is already provided.

// Note 2: You are not obliged to use this code at all.  If you can implement
// the requirements of Assignment 5 from scratch with your own code, that is
// also fine.  However, you must use the Mosaic, MCircle and MSquare classes
// as given without any changes.

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.print.*;

public class Assig5 {
	public static String software = "Mosaic Art 1.0";
	private Mosaic m;
	private DrawPanel thePanel; // DrawPanel is a subclass of JPanel
								// See details below.
	private JPanel buttonPanel;
	private JFrame theWindow;
	private JButton paintIt, eraseIt;
	private JButton editIt;

	// ArrayList of Mosaic to store the individual shapes. Note that
	// since Mosaic is the superclass of both MCircle and MSquare, both
	// shapes can be stored in this ArrayList
	private ArrayList<Mosaic> chunks;

	private double X, Y;

	private int editLocation;

	private double stX, stY;
	private double newSize;
	private Color newColor = Color.RED;
	private Color tempColor;
	private Color shiftColor1;
	private Color shiftColor2;
	private int shapeSelected;
	private int shapeShiftersShape;
	private int popupItem;

	private double lastX, lastY = 0;

	private boolean painting, erasing;
	private boolean editing;

	private boolean openedFile;

	private boolean changedEvent;

	private boolean popUpDone;

	private boolean savedFile;

	private boolean shapeShifters;
	private boolean colorShifters;

	private String currFile;
	private String opFile;

	private boolean popUP;
	private JMenuBar theBar;
	private JMenu fileMenu;
	private JMenu defaultsMenu;
	private JMenu effectsMenu;
	private JMenuItem endProgram, saveAs, printScene;
	private JMenuItem setColor, setSize, setShape;
	private JMenuItem square, circle;
	private JPopupMenu popup;
	private JMenuItem reColor;
	private JMenuItem reSize;
	private JMenuItem shapeTwisters;
	private JMenuItem colorTwisters;
	private JMenuItem newFile;
	private JMenuItem openFile;
	private JMenuItem saveCurr;
	private JMenuItem saveJPG;

	public Assig5() { // declaring all the variables such as the JMenu,
						// JMenuItems, etc.

		WindowListener WindowListen = new WindowEventDemo();

		theWindow = new JFrame(software);
		theWindow.setLayout(new BorderLayout());
		theWindow.addWindowListener(WindowListen);
		;

		ActionListener bListen = new ButtonListener();
		reColor = new JMenuItem("Recolor");
		reSize = new JMenuItem("Resize");
		reColor.addActionListener(bListen);
		reSize.addActionListener(bListen);

		thePanel = new DrawPanel(600, 600);
		newSize = 15;
		newColor = Color.RED;
		shapeSelected = 0;
		shapeShiftersShape = 0;
		popupItem = 0;
		painting = false;
		erasing = false;
		editing = false;

		openedFile = false;
		popUpDone = false;

		shapeShifters = false;
		colorShifters = false;
		savedFile = false; // checks whether file is saved or not
		changedEvent = false; // checks whether if there was any change made to
								// the mosaic drawing

		paintIt = new JButton("Paint");
		eraseIt = new JButton("Erase");
		editIt = new JButton("Edit");
		paintIt.addActionListener(bListen);
		eraseIt.addActionListener(bListen);
		editIt.addActionListener(bListen);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		buttonPanel.add(paintIt);
		buttonPanel.add(eraseIt);
		buttonPanel.add(editIt);
		theWindow.add(buttonPanel, BorderLayout.SOUTH);
		theWindow.add(thePanel, BorderLayout.CENTER);

		theBar = new JMenuBar();
		theWindow.setJMenuBar(theBar);

		fileMenu = new JMenu("File");
		defaultsMenu = new JMenu("Default");
		effectsMenu = new JMenu("Effects");
		theBar.add(fileMenu);
		theBar.add(defaultsMenu);
		theBar.add(effectsMenu);

		setColor = new JMenuItem("Set Color");
		setColor.addActionListener(bListen);
		defaultsMenu.add(setColor);

		setSize = new JMenuItem("Set Size");
		setSize.addActionListener(bListen);
		defaultsMenu.add(setSize);

		setShape = new JMenu("Set Shape");
		defaultsMenu.add(setShape);

		square = new JMenuItem("Square");
		circle = new JMenuItem("Circle");
		setShape.add(square);
		setShape.add(circle);

		saveAs = new JMenuItem("Save As");
		newFile = new JMenuItem("New");
		saveCurr = new JMenuItem("Save");
		printScene = new JMenuItem("Print");
		endProgram = new JMenuItem("Exit");
		openFile = new JMenuItem("Open");
		saveJPG = new JMenuItem("Save As JPG");
		fileMenu.add(newFile);
		fileMenu.add(openFile);
		fileMenu.add(saveCurr);
		fileMenu.add(saveAs);
		fileMenu.add(saveJPG);
		fileMenu.add(printScene);
		fileMenu.add(endProgram);

		shapeTwisters = new JMenuItem("Start Twisting Shapes");
		colorTwisters = new JMenuItem("Start Twisting Colors");
		effectsMenu.add(shapeTwisters);
		effectsMenu.add(colorTwisters);

		saveAs.addActionListener(bListen);
		newFile.addActionListener(bListen);
		openFile.addActionListener(bListen);
		saveCurr.addActionListener(bListen);
		printScene.addActionListener(bListen);
		endProgram.addActionListener(bListen);
		saveJPG.addActionListener(bListen);

		square.addActionListener(bListen);
		circle.addActionListener(bListen);
		shapeTwisters.addActionListener(bListen);
		colorTwisters.addActionListener(bListen);
		theWindow.pack();
		theWindow.setVisible(true);

		popUP = false;
	}

	// creating a new Mosaic object when doMyWork method is called. It also has
	// the functionality of
	// twisting shapes, colors and also mousedragging an object

	public void doMyWork(MouseEvent e) {
		double lXplus, lXminus, lYplus, lYminus;
		lXplus = lastX + newSize;
		lYplus = lastY + newSize;
		lXminus = lastX - newSize;
		lYminus = lastY - newSize;

		// boolean to check whether it can make a new object or not. This is
		// mainly to make sure there is
		// spacing between each mosaic object.

		boolean shouldMakeNewObject = (Y < lYminus && (X <= lXplus || X >= lXminus))
				|| (Y > lYplus && (X <= lXplus || X >= lXminus)) || (X > lXplus && (Y >= lYminus || Y <= lYplus))
				|| (X < lXminus && (Y <= lXplus || Y >= lYminus)) || (X <= lXminus && Y <= lYminus)
				|| (X <= lXminus && Y <= lYminus) || (X <= lXminus && Y <= lYminus);

		if (shouldMakeNewObject) { // if the boolean is true;

			int theShape = shapeSelected; // stores default selection

			if (shapeShifters) { // shape values keep switching for shape
									// twisting
				theShape = shapeShiftersShape;

				switch (shapeShiftersShape) {
				case 0:
					shapeShiftersShape = 1;
					break;
				case 1:
					shapeShiftersShape = 0;
					break;
				}
			}

			Color colorToUse = newColor; // stores default color;

			if (colorShifters) { // colors switch for color twisting
				colorToUse = shiftColor2;
				Color temp = shiftColor1;
				shiftColor1 = shiftColor2;
				shiftColor2 = temp;
			}

			if (theShape == 0) { // decides which shape to create
				m = new MCircle(newSize, X, Y, colorToUse);
			} else if (theShape == 1) {
				m = new MSquare(newSize, X, Y, colorToUse);
			}

			thePanel.add(m); // adds the mosaic object and updates the
								// coordinates of the previous objects
			lastX = X;
			lastY = Y;
			changedEvent = true;

		}
	}

	private class DrawPanel extends JPanel {
		private static final long serialVersionUID = -8108126504122723415L;

		private int prefwid, prefht;

		// Initialize the DrawPanel by creating a new ArrayList for the images
		// and creating a MouseListener to respond to clicks in the panel.
		public DrawPanel(int wid, int ht) {
			prefwid = wid;
			prefht = ht;

			chunks = new ArrayList<Mosaic>();

			// Add MouseListener to this JPanel to respond to the user
			// pressing the mouse. In your assignment you will also need a
			// MouseMotionListener to respond to the user dragging the mouse.
			addMouseListener(new MListen());
			addMouseMotionListener(new MyMotionListener()); // mousemotion
															// listener
			addMouseListener(new PopupTriggerListener()); // pop up listener
		}

		// This method allows a window that encloses this panel to determine
		// how much space the panel needs. In particular, when the "pack()"
		// method is called from an outer JFrame, this method is called
		// implicitly and the result determines how much space is needed for
		// the JPanel

		public Dimension getPreferredSize() {
			return new Dimension(prefwid, prefht);
		}

		// This method is responsible for rendering the images within the
		// JPanel. You should not have to change this code.
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			for (int i = 0; i < chunks.size(); i++) {
				chunks.get(i).draw(g2d);
			}
		}

		// Add a new Mosaic and repaint. The repaint() method call requests
		// that the panel be redrawn. Make sure that you call repaint()
		// after changes to your scenes so that the changes are actually
		// exhibited in the display.
		public void add(Mosaic m) {
			chunks.add(m);
			repaint();
			changedEvent = true;
		}

		// Remove the Mosaic at index i and repaint
		public void remove(int i) {
			if (chunks.size() > i)
				chunks.remove(i);
			repaint();
			changedEvent = true;
		}

		// Select a Mosaic that contains the point (x, y). Note that this
		// is using the contains() method of the Mosaic class, which in turn
		// is checking within the underlying RectangularShape of the object.
		public int select(double x, double y) {
			for (int i = 0; i < chunks.size(); i++) {
				if (chunks.get(i).contains(x, y)) {
					return i;
				}
			}
			return -1;
		}
	}

	// Save the images within the window to a file. Run this program to see the
	// format of the saved file.

	public void saveImages(String newFileName) {
		saveImages(newFileName, false);
	}

	public void saveImages(String fileName, boolean forceOverwrite) { // saves
																		// images
		if (!changedEvent && !forceOverwrite) { // forces overwrite if the file
												// is already saved
			return;
		} else if (fileName == null) {
			return;
		}

		File theFile = new File(fileName);

		// checks whether if the file exists or not. Also checks if overwriting
		// is required

		if (theFile.exists() && (forceOverwrite || !fileName.equals(currFile))) {
			int chooseOption = JOptionPane.showConfirmDialog(theWindow,
					"File Already Exists. Do you want to overwrite it?");
			if (chooseOption == JOptionPane.NO_OPTION || chooseOption == JOptionPane.CANCEL_OPTION) {
				changedEvent = true;
				savedFile = false;
				return;
			} else if (chooseOption == JOptionPane.YES_OPTION) {
			}
		}

		// writes the coordinates of the images

		try {
			PrintWriter P = new PrintWriter(theFile);
			P.println(chunks.size());
			for (int i = 0; i < chunks.size(); i++) {
				P.println(chunks.get(i).saveFile());
			}

			P.close();

			changedEvent = false;
			savedFile = true;
			currFile = fileName;
			theWindow.setTitle(software + " - " + currFile);
		} catch (IOException e) { // IO error
			JOptionPane.showMessageDialog(theWindow, "I/O Problem - File not Saved");
			changedEvent = true;
		}
	}

	// Extra Credit: Saving files as images

	public void saveAsJPEG() {
		if (currFile == null) {
			JOptionPane.showMessageDialog(theWindow, "File not Saved");
		} else if (currFile != null) {
			File theFile = new File(currFile + ".jpg");
			BufferedImage mosaicImage = new BufferedImage(thePanel.getWidth(), thePanel.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			if (theFile.exists()) {
				int chooseOption = JOptionPane.showConfirmDialog(theWindow,
						"File Already Exists. Do you want to overwrite it?");
				if (chooseOption == JOptionPane.NO_OPTION || chooseOption == JOptionPane.CANCEL_OPTION) {

					return;
				}
			}
			thePanel.paintComponent(mosaicImage.getGraphics());
			try {
				ImageIO.write(mosaicImage, "JPG", theFile);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(theWindow, "I/O Problem - File not Saved");
			}
		}
	}

	// method to open images,

	public void openImages() {
		if (opFile == null) {
			JOptionPane.showMessageDialog(theWindow, "File not Opened"); // if
																			// no
																			// file
																			// name
																			// entered
		} else if (opFile != null) {

			currFile = opFile; // sets to current file name

			savedFile = true;
			changedEvent = false;

			chunks.clear(); // clears the chunks arraylist before adding the new
							// ones of opened file

			try {

				Scanner reader = new Scanner(new File(currFile));
				String shape = null;
				double width;
				double centerX;
				double centerY;
				int red;
				int green;
				int blue;

				// Skip first line
				reader.nextLine();

				while (reader.hasNextLine()) { // loops for each line, takes in
												// all the values required to
												// make objects
					String delims = ",";
					String rgbDelim = ":";
					String[] tokens = (reader.nextLine()).split(delims);
					shape = tokens[0];
					width = Double.parseDouble(tokens[1]);
					centerX = Double.parseDouble(tokens[2]);
					centerY = Double.parseDouble(tokens[3]);
					String[] rgb = tokens[4].split(rgbDelim);
					red = Integer.parseInt(rgb[0]);
					green = Integer.parseInt(rgb[1]);
					blue = Integer.parseInt(rgb[2]);

					Color now = new Color(red, green, blue);
					Mosaic createNew;
					if (shape.equalsIgnoreCase("Circle")) { // if its a circle
						createNew = new MCircle(width, centerX, centerY, now);
						thePanel.add(createNew);

					} else if (shape.equalsIgnoreCase("Square")) { // if its a
																	// square
						createNew = new MSquare(width, centerX, centerY, now);
						thePanel.add(createNew);
					}
					openedFile = true;
					savedFile = true;
				}
				reader.close();

			} catch (FileNotFoundException e) { // file not found exception
				JOptionPane.showMessageDialog(theWindow, "File does not exist!");
			}
		}

	}

	// Listener for some buttons. Note that the JMenuItems are also listened
	// for here. Like JButtons, JMenuItems also generate ActionEvents when
	// they are clicked on. You will need to add more JButtons and JMenuItems
	// to your program and the logic of handling them will also be more
	// complex. See details in the Assignment 5 specifications.
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == paintIt) {
				painting = true;
				paintIt.setForeground(Color.RED);
				erasing = false;
				eraseIt.setForeground(Color.BLACK);
				editing = false;
				editIt.setForeground(Color.BLACK);
				popUP = false;

			} else if (e.getSource() == eraseIt) {
				painting = false;
				paintIt.setForeground(Color.BLACK);
				erasing = true;
				eraseIt.setForeground(Color.RED);
				editing = false;
				editIt.setForeground(Color.BLACK);
				popUP = false;

			} else if (e.getSource() == saveCurr) { // to save the current file

				if (chunks.size() == 0) { // if there's nothing to save
					JOptionPane.showMessageDialog(theWindow, "Nothing to Save!");
					changedEvent = false;
					return;
				}

				if (changedEvent || !savedFile) { // if the file is not saved
													// before
					JOptionPane.showMessageDialog(theWindow, "Save the file first!");
					String fileName = JOptionPane.showInputDialog(theWindow, "Enter new file name");
					saveImages(fileName);
				} else if (changedEvent && savedFile) { // if the file is
														// already saved
					saveImages(currFile);
				}
			} else if (e.getSource() == saveAs) { // to save the file
				if (chunks.size() == 0) {
					JOptionPane.showMessageDialog(theWindow, "Nothing to Save!"); // if
																					// there's
																					// nothing
																					// to
																					// save
					changedEvent = false;
					return;
				}

				String fileName = JOptionPane.showInputDialog(theWindow, "Enter new file name");
				saveImages(fileName, true);
			} else if (e.getSource() == endProgram) { // to exit the program

				if (chunks.size() == 0) {
					System.exit(0); // exits if nothing exists
				}

				if (changedEvent) { // if file is not saved
					if (chunks.size() > 0) {
						int selection = JOptionPane.showConfirmDialog(theWindow,
								"Do you want to save the current file?");
						if (selection == JOptionPane.YES_OPTION) {
							String fileName = JOptionPane.showInputDialog(theWindow, "Enter file name");
							saveImages(fileName);
							System.exit(0);
						} else if (selection == JOptionPane.NO_OPTION) {
							System.exit(0);
						} else if (selection == JOptionPane.CANCEL_OPTION) {
							return;
						}
					}
				} else
					System.exit(0);
			} else if (e.getSource() == printScene) { // prints out the image
				Printable thePPanel = new thePrintPanel(thePanel);
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(thePPanel);
				boolean ok = job.printDialog();
				if (ok) {
					try {
						job.print();
					} catch (PrinterException ex) {
						/* The job did not successfully complete */
					}
				}
			} else if (e.getSource() == editIt) {
				editing = true;
				editIt.setForeground(Color.RED);
				painting = false;
				paintIt.setForeground(Color.BLACK);
				erasing = false;
				eraseIt.setForeground(Color.BLACK);
				popUP = true;

				popup = new JPopupMenu("Edit"); // adding a new pop up
				popup.add(reColor);
				popup.add(reSize);

			} else if (e.getSource() == setColor) { // sets the color of the
													// mosaic objects
				newColor = JColorChooser.showDialog(thePanel, "Choose a color", newColor);
				paintIt.setForeground(Color.RED);
				changedEvent = true;
				if (colorShifters) { // sets off the color twisting effects
					colorShifters = false;
					JOptionPane.showMessageDialog(theWindow, "Color Twisting Turned Off");
					colorTwisters.setForeground(Color.BLACK);
					changedEvent = true;
				}
			} else if (e.getSource() == setSize) { // sets the size of the
													// mosaic objects
				String tempSize = JOptionPane.showInputDialog(theWindow, "What's the new size?");
				newSize = Double.parseDouble(tempSize);
				changedEvent = true;
			} else if (e.getSource() == circle) { // sets the default mosaic
													// object to circles
				shapeSelected = 0;
				changedEvent = true;
				if (shapeShifters) { // sets off the shape twisting effect
					shapeShifters = false;
					JOptionPane.showMessageDialog(theWindow, "Shape Twisting Turned Off");
					shapeTwisters.setForeground(Color.BLACK);
					changedEvent = true;
				}
			} else if (e.getSource() == square) { // sets the default mosaic
													// object to squares
				shapeSelected = 1;
				changedEvent = true;
				if (shapeShifters) { // sets off the shape twisting effect
					shapeShifters = false;
					JOptionPane.showMessageDialog(theWindow, "Shape Twisting Turned Off");
					shapeTwisters.setForeground(Color.BLACK);
					changedEvent = true;
				}
			} else if (e.getSource() == reColor && popUP) { // when re color is
															// chosen from pop
															// up
				int editLoc = thePanel.select(X, Y);
				tempColor = JColorChooser.showDialog(thePanel, "Choose a color", newColor);
				chunks.get(editLoc).setColor(tempColor);
				thePanel.repaint();
				chunks.get(editLoc).highlighted = false;
				changedEvent = true;
			} else if (e.getSource() == reSize && popUP) { // when recolor is
															// chosen from pop
															// up
				int editLoc = thePanel.select(X, Y);
				String tempSize = JOptionPane.showInputDialog(theWindow, "What's the new size?");
				chunks.get(editLoc).setSize(Double.parseDouble(tempSize));
				thePanel.repaint();
				chunks.get(editLoc).highlighted = false;
				changedEvent = true;
			} else if (e.getSource() == shapeTwisters) { // shape twisting
															// effects
				shapeShifters = !shapeShifters;
				changedEvent = true;
				if (shapeShifters) {
					shapeTwisters.setForeground(Color.BLUE);
					changedEvent = true;
				} else if (!shapeShifters) {
					shapeTwisters.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(theWindow, "Shape Twisting Turned Off");
				}
			} else if (e.getSource() == colorTwisters) { // color twisting
															// effect
				colorShifters = !colorShifters;
				changedEvent = true;
				if (colorShifters) {
					shiftColor1 = newColor;
					shiftColor2 = JColorChooser.showDialog(thePanel, "Select Second Color", newColor);
					colorTwisters.setForeground(Color.BLUE);
					changedEvent = true;
				} else if (!colorShifters) {
					colorTwisters.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(theWindow, "Color Twisting Turned Off");
				}
			} else if (e.getSource() == openFile) { // when user chooses to open
													// new file
				if (!changedEvent) {
					opFile = JOptionPane.showInputDialog(theWindow, "Enter the file name to open");
					openImages();
					if (opFile != null && savedFile && openedFile) {
						theWindow.setTitle(software + " - " + opFile);
					}
					changedEvent = false;
				} else if (changedEvent) {
					if (chunks.size() > 0) {
						int selection = JOptionPane.showConfirmDialog(theWindow,
								"Do you want to save the current file before opening the new one?");
						if (selection == JOptionPane.YES_OPTION) {
							String fileName = JOptionPane.showInputDialog(theWindow, "Enter file name");
							saveImages(fileName);
						} else if (selection == JOptionPane.CANCEL_OPTION) {
							return;
						}
					}
					opFile = JOptionPane.showInputDialog(theWindow, "Enter the file name to open");
					openImages();
					if (opFile != null && savedFile && openedFile) {
						theWindow.setTitle(software + " - " + opFile);
					}
					changedEvent = false;
				}
			} else if (e.getSource() == newFile) { // when user chooses to
													// create a new file
				if (changedEvent || !savedFile) { // checks if the current file
													// is saved or not before
													// making a new one
					if (chunks.size() > 0) {
						int selection = JOptionPane.showConfirmDialog(theWindow, "Save Scene?");
						if (selection == JOptionPane.YES_OPTION) {
							String fileName = JOptionPane.showInputDialog(theWindow, "Enter file name");
							saveImages(fileName);
						} else if (selection == JOptionPane.CANCEL_OPTION) {
							return;
						}
					}
				} else if (!changedEvent || savedFile) {
					if (chunks.size() > 0) {
						int selection = JOptionPane.showConfirmDialog(theWindow,
								"Do you want to save the current file before opening the new one?");
						if (selection == JOptionPane.YES_OPTION) {
							saveImages(currFile);
						} else if (selection == JOptionPane.CANCEL_OPTION) {
							return;
						}
					}
				}
				theWindow.dispose();
				String[] args = {};
				Assig5.main(args); // makes new window
				changedEvent = false;
			} else if (e.getSource() == saveJPG) { // extra credit, lets user
													// save files as jpg
				if (chunks.size() > 0) {
					currFile = JOptionPane.showInputDialog(theWindow, "Enter file name");
					saveAsJPEG();
					if (currFile != null)
						theWindow.setTitle(software + " - " + currFile);
				} else if (chunks.size() == 0) {
					JOptionPane.showMessageDialog(theWindow, "Nothing to save!");
				}
			}
		}
	}

	public class WindowEventDemo implements WindowListener { // windows listener

		public void windowClosing(java.awt.event.WindowEvent windowEvent) { // when
																			// user
																			// tries
																			// closing

			if (!savedFile) { // checks if the file is saved before quitting

				if (chunks.size() > 0) {
					int selection = JOptionPane.showConfirmDialog(theWindow, "Do you want to save the current file?");
					if (selection == 0) {
						String fileName = JOptionPane.showInputDialog(theWindow, "Enter file name");
						saveImages(fileName);
						System.exit(0);
					} else if (selection == 1) {
						System.exit(0);
					} else if (selection == 2) {
						theWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					}
				}
			} else
				System.exit(0);
		}

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowClosed(WindowEvent e) {

		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowOpened(WindowEvent e) {

		}

	}

	// Simple mouse event handling to allow a mousePressed to add or remove
	// a Mosaic from the display. You will need to enhance this
	// MouseAdapter and you will also need to add a MouseMotionListener to
	// your program. In this simple program all of the Mosaics drawn are
	// MCircles and they all have the same size and color. You must add in
	// your program the ability to change all of these attributes.

	private class MListen extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			X = e.getX(); // Get the location where mouse was pressed
			Y = e.getY();

			if (painting) {
				// create new MCircle and add it to the ArrayList
				doMyWork(e);
			} else if (erasing) {
				// see if the point is within a shape -- if so delete
				// that shape
				int loc = thePanel.select(X, Y);
				if (loc > -1) {
					thePanel.remove(loc);
				}
			} else if (editing) { // if the user is editing
				editLocation = thePanel.select(X, Y); // returns the index of
														// the selected object

				if (editLocation > -1) { // if it exists, the x and y
											// coordinates are stored
					thePanel.repaint();
					stX = X;
					stY = Y;
				}
			}
			thePanel.repaint(); // repaints

		}
	}

	private class MyMotionListener implements MouseMotionListener { // mouse
																	// motion
																	// listener

		public void mouseDragged(MouseEvent e) { // when the mouse is dragged
			X = e.getX(); // Get the location where mouse was pressed
			Y = e.getY();

			double prX = X;
			double prY = Y;

			if (painting) {
				doMyWork(e);
			} else if (erasing) {
				// see if the point is within a shape -- if so delete
				// that shape
				int loc = thePanel.select(X, Y);
				if (loc > -1) {
					thePanel.remove(loc);
				}
			} else if (editing) {
				// editLocation = thePanel.select(X, Y);

				if (editLocation > -1) {
					chunks.get(editLocation).move(prX - stX, prY - stY); // moves
																			// the
																			// obuject
																			// to
																			// the
																			// new
																			// coordinates
					stX = prX; // sets the previous coordinates to current ones
					stY = prY;
					thePanel.repaint();
				}

			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}

	private class PopupTriggerListener extends MouseAdapter { // pop up listener
		public void mousePressed(MouseEvent ev) { // sets the higlighting to
													// false when clicked
													// somewhere else

			if (popUpDone) {
				if (popupItem != -1) {
					chunks.get(popupItem).highlighted = false;
					thePanel.repaint();
					popUpDone = false;

				} else if (popupItem != 1) {
					try {
						thePanel.repaint();
						popUpDone = true;
					} catch (IndexOutOfBoundsException e) {
						thePanel.repaint();
					}
				}
			}

		}

		public void mouseReleased(MouseEvent ev) { // sshows pop up when mouse
													// is released
			if (ev.isPopupTrigger() && popUP && editing) { // happens only in
															// editing mode

				if (popupItem > -1) { // if the the popupItem index object
										// exists
					chunks.get(popupItem).highlighted = false;
					thePanel.repaint();
					popUpDone = true;
					popupItem = -1;
				}

				X = ev.getX();
				Y = ev.getY();

				int editLoc = thePanel.select(X, Y);
				if (editLoc == -1) { // if the mouse is released somewhere else
					return;
				}

				popup.show(ev.getComponent(), ev.getX(), ev.getY());

				chunks.get(editLoc).highlighted = true;
				thePanel.repaint();

				popupItem = editLoc; // setting popupItem to editloc

			}
		}

		public void mouseClicked(MouseEvent ev) {
		}
	}

	// main constructor
	public static void main(String[] args) {
		new Assig5();
	}
}

// This class is taken from the Web and is somewhat buggy but it does a basic
// print of the panel.
class thePrintPanel implements Printable {
	JPanel panelToPrint;

	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform t = new AffineTransform();
		t.scale(0.9, 0.9);
		g2d.transform(t);
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		// pf.setOrientation(PageFormat.REVERSE_LANDSCAPE);
		/* Now print the window and its visible contents */
		panelToPrint.printAll(g);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

	public thePrintPanel(JPanel p) {
		panelToPrint = p;
	}
}