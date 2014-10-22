package pburg.tsa.getSchooled.ui;

import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public abstract class DebugConsole extends JPanel implements KeyListener {

	public static final Color TRANS_DARK_GRAY = new Color(64, 64, 64, 200);
	public static final Color TRANS_WHITE = new Color(255, 255, 255, 200);
	public static final Color TRANS_LIGHT_GRAY = new Color(192, 192, 192, 200);
	
	public static final String CLOSE_WINDOW_IMG = "assets/ui/closeImage.png";

	private static final long serialVersionUID = -3188136815039596133L;

	private int borderHeight, borderWidth;

	private JTextArea debugInfo;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JLabel closeButton, debugPrompt;
	private JPanel panel;

	/**
	 * Creates a new instance of DebugConsole
	 * 
	 * @param parentContainerWidth - The parent containter's initial width for sizing the console. Can be reset by calling {@link #resizeConsole(int, int)}
	 * @param parentContainerHeight - The parent containter's initial height for sizing the console. Can be reset by calling {@link #resizeConsole(int, int)}
	 * @throws IOException 
	 */
	public DebugConsole(int parentContainerWidth, int parentContainerHeight) throws IOException {
		
		borderWidth = parentContainerWidth / 4;
		borderHeight = parentContainerHeight / 4;
		setSize(parentContainerWidth - borderWidth * 2, parentContainerHeight - borderHeight * 2);
		setLocation(borderWidth, borderHeight);
		setBackground(Color.WHITE);
		setFocusable(false);
		setOpaque(false);
		setBorder(null);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0 };
		setLayout(gridBagLayout);

		debugInfo = new JTextArea();
		debugInfo.setFont(new Font("Serif", Font.PLAIN, 14));
		debugInfo.setEditable(false);
		debugInfo.addKeyListener(this);
		debugInfo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		debugInfo.setAlignmentY(Component.TOP_ALIGNMENT);
		debugInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
		debugInfo.setOpaque(false);
		debugInfo.setBorder(null);
		debugInfo.setForeground(TRANS_WHITE);
		
		scrollPane = new JScrollPane(debugInfo);
		scrollPane.setWheelScrollingEnabled(false);
		scrollPane.setRequestFocusEnabled(false);
		scrollPane.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setFocusable(false);
		scrollPane.setAutoscrolls(true);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.isShiftDown()) {
	                // Horizontal scrolling
	                Adjustable adj = scrollPane.getHorizontalScrollBar();
	                int scroll = e.getUnitsToScroll() * adj.getBlockIncrement();
	                adj.setValue(adj.getValue() + scroll);
	            } else {
	                // Vertical scrolling
	                Adjustable adj = scrollPane.getVerticalScrollBar();
	                int scroll = e.getUnitsToScroll() * adj.getBlockIncrement();
	                adj.setValue(adj.getValue() + scroll);
	            }
			}
		});
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 10, 10, 10);
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		CustomScrollBarUI scrollBarVertUI = new CustomScrollBarUI(CustomScrollBarUI.VERTICAL_SCROLLBAR);
		JScrollBar scrollBarVert = scrollPane.getVerticalScrollBar();
		scrollBarVert.setMaximumSize(new Dimension(11, Integer.MAX_VALUE));
		scrollBarVert.setMinimumSize(new Dimension(11, 0));
		scrollBarVert.setPreferredSize(new Dimension(11, Integer.MAX_VALUE));
		scrollBarVert.setOpaque(false);
		scrollBarVert.setUI(scrollBarVertUI);
		
		CustomScrollBarUI scrollBarHorUI = new CustomScrollBarUI(CustomScrollBarUI.HORIZONTAL_SCROLLBAR);
		JScrollBar scrollBarHor = scrollPane.getHorizontalScrollBar();
		scrollBarHor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 11));
		scrollBarHor.setMinimumSize(new Dimension(0, 11));
		scrollBarHor.setPreferredSize(new Dimension(Integer.MAX_VALUE, 11));
		scrollBarHor.setOpaque(false);
		scrollBarHor.setUI(scrollBarHorUI);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setFont(new Font("Serif", Font.PLAIN, 14));
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 10, 10, 10);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 1, 21, 0 };
		gbl_panel.rowHeights = new int[] { 17, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		textField = new JTextField();
		textField.setDoubleBuffered(true);
		textField.setFont(new Font("Serif", Font.PLAIN, 14));
		textField.addKeyListener(this);
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createLineBorder(TRANS_LIGHT_GRAY, 1, true));
		textField.setForeground(TRANS_WHITE);
		textField.setCaretColor(TRANS_WHITE);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);

		debugPrompt = new JLabel("Debug> ");
		debugPrompt.setFont(new Font("Serif", Font.PLAIN, 14));
		debugPrompt.setForeground(TRANS_WHITE);
		debugPrompt.setOpaque(false);
		
		GridBagConstraints gbc_debugPrompt = new GridBagConstraints();
		gbc_debugPrompt.anchor = GridBagConstraints.WEST;
		gbc_debugPrompt.gridx = 0;
		gbc_debugPrompt.gridy = 0;
		panel.add(debugPrompt, gbc_debugPrompt);
		
		closeButton = new JLabel(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream(CLOSE_WINDOW_IMG))));
		closeButton.setFont(new Font("Dialog", Font.BOLD, 5));
		closeButton.setSize(new Dimension(17, 17));
		closeButton.setPreferredSize(new Dimension(17, 17));
		closeButton.setMaximumSize(new Dimension(17, 17));
		closeButton.setMinimumSize(new Dimension(17, 17));
		closeButton.setOpaque(false);
		closeButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				closeConsole();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(10, 0, 5, 10);
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		add(closeButton, gbc_btnNewButton);

	}

	@Override
	protected void paintComponent(Graphics graph) {
		Graphics2D g2d = (Graphics2D) graph.create();
		g2d.setColor(TRANS_DARK_GRAY);
		g2d.fillRoundRect(0, 0, borderWidth * 2, borderHeight * 2, 20, 20);
		g2d.dispose();
	}

	/**
	 * Used to resize the console whenever it is needed. (i.e. The parent component gets resized)
	 * 
	 * @param parentContainerWidth - The width of the parent component
	 * @param parentContainerHeight - The height of the parent component
	 */
	public void resizeConsole(int parentContainerWidth, int parentContainerHeight) {
		borderWidth = parentContainerWidth / 4;
		borderHeight = parentContainerHeight / 4;
		setSize(parentContainerWidth - borderWidth * 2, parentContainerHeight - borderHeight * 2);
		setLocation(borderWidth, borderHeight);
	}

	/**
	 * This method is to be used to get the text area from the console so that it can be written to by a logger.
	 * 
	 * @return The JTextArea of the console
	 */
	public JTextArea getDebugTextArea() {
		return debugInfo;
	}

	/**
	 * To be called from the instantiating class to properly focus on the input field when the console is opened.
	 */
	public void requestTextFieldFocus() {
		panel.requestFocusInWindow();
		textField.requestFocusInWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			closeConsole();
			return;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * This method tells the instantiating class that the user is attempting to close the console.
	 */
	public abstract void closeConsole();
}
