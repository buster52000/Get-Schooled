package pburg.tsa.getSchooled.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pburg.tsa.getSchooled.Start;
import pburg.tsa.getSchooled.logging.Logging;

public abstract class UI extends JFrame implements MouseListener, KeyListener, WindowListener, ComponentListener {

	private static final long serialVersionUID = 86095243998441453L;
	private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

	private boolean consoleOpen;
	private int screenHeight, screenWidth;

	private DebugConsole console;
	private JPanel contentPane;
	private JLabel imageLabel;

	@SuppressWarnings("serial")
	public static void main(String args[]) throws IOException {
		UI ui = new UI() {

			@Override
			public void resumeGame() {
				System.out.println("Game Resumed");
			}

			@Override
			public void pauseGame() {
				System.out.println("Game Paused");
			}
		};
		Logging.setUpOutput(ui.getDebugArea(), Start.LOG_PREFIX);
	}

	@SuppressWarnings("serial")
	public UI() throws IOException {
		super(Start.READABLE_NAME);
		contentPane = new JPanel(true);
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		consoleOpen = false;
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addKeyListener(this);
		addMouseListener(this);
		addWindowListener(this);
		addComponentListener(this);

		setAlwaysOnTop(true);
		setAutoRequestFocus(true);
		getContentPane().setBackground(Color.BLACK);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = dim.height;
		screenWidth = dim.width;

		imageLabel = new JLabel();
		imageLabel.setSize(screenWidth, screenHeight);
		imageLabel.setLocation(0, 0);
		imageLabel.setIcon(new ImageIcon("testPic.jpg"));
		console = new DebugConsole(screenWidth, screenHeight) {

			@Override
			public void closeConsole() {
				hideDebugConsole();
			}
		};
		console.setVisible(false);
		console.setEnabled(false);
		contentPane.add(console);
		contentPane.add(imageLabel);

		setVisible(true);
		device.setFullScreenWindow(this);
		requestFocus(false);
	}

	/**
	 * Gets the debug console's text area for logging purposes
	 * @return debugTextArea - the console's output text area
	 */
	public JTextArea getDebugArea() {
		return console.getDebugTextArea();
	}

	/**
	 * Sets the debug console to visible, sets the focus to the console, and pauses the game
	 */
	private void showDebugConsole() {
		pauseGame();
		setFocusable(false);
		consoleOpen = true;
		console.setEnabled(true);
		console.setVisible(true);
		console.requestTextFieldFocus();
		System.out.println("Debug opened");
	}

	/**
	 * Hides the debug console but leaves it available to be shown if requested
	 * sets the focus on the JFrame
	 * resumes the game
	 */
	private void hideDebugConsole() {
		console.setVisible(false);
		console.setEnabled(false);
		consoleOpen = false;
		setFocusable(true);
		requestFocusInWindow();
		System.out.println("Debug Closed");
		resumeGame();
	}

	/**
	 * Automatically resizes the UI using the current dimensions of the JFrame
	 */
	public void resizeUI() {
		console.resizeConsole(contentPane.getWidth(), contentPane.getHeight());
		imageLabel.setSize(contentPane.getWidth(), contentPane.getHeight());
		revalidate();
		repaint();
	}

	public void closeWindow() {
		setVisible(false);
		dispose();
		Logging.shutdownLogger();
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("UI " + e.getKeyChar());
		if (!consoleOpen) {
			switch (e.getKeyChar()) {
			case '`':
			case '~':
				showDebugConsole();
				break;
			case 'q':
			case 'Q':
				Start.running = false;
				closeWindow();
				break;
			default:
				e.consume();
				break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {
		device.setFullScreenWindow(this);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		Start.running = false;
		closeWindow();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		device.setFullScreenWindow(null);
		requestFocus();
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

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentResized(ComponentEvent e) {
		resizeUI();
	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	/**
	 * Tells the instantiating class that the user has done something that should cause the game to pause.
	 * To unpause the game call {@link #resumeGame()}
	 */
	public abstract void pauseGame();

	/**
	 * Tells the instantiating class that the user has done something that should cause the game to unpause.
	 * To pause the game call {@link #pauseGame()}
	 */	
	public abstract void resumeGame();
}
