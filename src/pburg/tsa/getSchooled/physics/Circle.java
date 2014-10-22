package pburg.tsa.getSchooled.physics;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle extends Ellipse2D {

	private int centerX, centerY, radius;

	public Circle(Dimension center, int radius) {
		this.radius = radius;
		this.centerX = center.width;
		this.centerY = center.height;
	}

	public Circle(int centerX, int centerY, int radius) {
		this.radius = radius;
		this.centerX = centerX;
		this.centerY = centerY;
	}

	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}

	@Override
	public double getX() {
		return centerX - radius;
	}

	@Override
	public double getY() {
		return centerY - radius;
	}

	@Override
	public double getWidth() {
		return radius * 2;
	}

	@Override
	public double getHeight() {
		return radius * 2;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void setFrame(double x, double y, double w, double h) {
		if (w != h) {
			try {
				throw new Exception("The given dimensions are not circular");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		} else {
			radius = (int) (w / 2);
			this.centerX = (int) (x + radius);
			this.centerY = (int) (y + radius);
		}

	}
	
	public void setLocationByCenter(int x, int y) {
		this.centerX = x;
		this.centerY = y;
	}
	
	public void setLocationByCorner(int x, int y) {
		this.centerX = x + radius;
		this.centerY = y + radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public double getCenterX() {
		return centerX;
	}
	
	public double getCenterY() {
		return centerY;
	}
	
	public int getRadius() {
		return radius;
	}

}
