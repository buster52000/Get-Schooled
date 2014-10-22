package pburg.tsa.getSchooled.ui;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class CustomScrollBarUI extends MetalScrollBarUI {

	public static final int VERTICAL_SCROLLBAR = 0;
	public static final int HORIZONTAL_SCROLLBAR = 1;

	private int scrollBarDirection;

	public CustomScrollBarUI(int direction) {
		scrollBarDirection = direction;
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		g.translate(thumbBounds.x, thumbBounds.y);
		g.setColor(DebugConsole.TRANS_LIGHT_GRAY);
		if (scrollBarDirection == VERTICAL_SCROLLBAR)
			g.fillRoundRect(3, 2, thumbBounds.width - 6, thumbBounds.height - 4, 3, 3);
		else
			g.fillRoundRect(2, 3, thumbBounds.width - 4, thumbBounds.height - 6, 3, 3);
		g.translate(-thumbBounds.x, -thumbBounds.y);

	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.translate(trackBounds.x, trackBounds.y);
		g.setColor(DebugConsole.TRANS_LIGHT_GRAY);
		if (scrollBarDirection == VERTICAL_SCROLLBAR)
			g.drawPolygon(new int[] { 3, 8, 9, 9, 8, 3, 1, 1 }, new int[] { 0, 0, 2, trackBounds.height - 2, trackBounds.height - 1, trackBounds.height - 1, trackBounds.height - 2, 2 }, 8);
		else
			g.drawPolygon(new int[] { 0, 0, 2, trackBounds.width - 2, trackBounds.width - 1, trackBounds.width - 1, trackBounds.width - 2, 2 }, new int[] { 3, 8, 9, 9, 8, 3, 1, 1 }, 8);
		g.translate(-trackBounds.x, -trackBounds.y);
	}

	@SuppressWarnings("serial")
	@Override
	protected JButton createDecreaseButton(int orientation) {
		BasicArrowButton btn = new BasicArrowButton(orientation) {
			@Override
			public void paint(Graphics g) {
				if (getModel().isPressed())
					g.setColor(DebugConsole.TRANS_WHITE);
				else
					g.setColor(DebugConsole.TRANS_LIGHT_GRAY);
				if (scrollBarDirection == VERTICAL_SCROLLBAR)
					g.fillPolygon(new int[] { 0, 5, 10 }, new int[] { 13, 2, 13 }, 3);
				else
					g.fillPolygon(new int[] { 13, 2, 13 }, new int[] { 0, 5, 10 }, 3);
			}
		};
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		return btn;
	}

	@SuppressWarnings("serial")
	@Override
	protected JButton createIncreaseButton(int orientation) {
		BasicArrowButton btn = new BasicArrowButton(orientation) {
			@Override
			public void paint(Graphics g) {
				if (getModel().isPressed())
					g.setColor(DebugConsole.TRANS_WHITE);
				else
					g.setColor(DebugConsole.TRANS_LIGHT_GRAY);
				if (scrollBarDirection == VERTICAL_SCROLLBAR)
					g.fillPolygon(new int[] { 1, 5, 10 }, new int[] { 3, 14, 3 }, 3);
				else
					g.fillPolygon(new int[] { 3, 14, 3 }, new int[] { 1, 5, 10 }, 3);
			}
		};
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		return btn;
	}
}