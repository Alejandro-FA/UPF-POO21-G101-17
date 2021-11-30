package gui;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import entities.*;
import java.util.*;

public class DrawPanel extends JPanel implements MouseListener{
	protected LinkedList< Entity > drawables;
	protected LinkedList< Entity > selection;

	public DrawPanel() {
		drawables = new LinkedList< Entity >();
		selection = new LinkedList< Entity >();
		addMouseListener( this );
	}

	public void addDrawable( Entity entity ) {
		drawables.add( entity );
	}

	protected void paintComponent( Graphics g ) {
		super.paintComponent(g);
		for ( int i = 0; i < drawables.size(); ++i )
			drawables.get( i ).draw( g );
	}

	public void translate( int dx, int dy ) {
		for ( int i = 0; i < selection.size(); ++i )
			selection.get( i ).translate( dx, dy );
	}

	/************************ MouseListener Methods ****************************/
	public void mousePressed(MouseEvent e) {
		Point p = new Point( e.getX(), e.getY() );
		for (Entity entity: drawables) {
			// Only applicable to regions
			if (entity instanceof Region) {
				Region region = (Region) entity;
				boolean isClicked = region.isPointInside(p);
				boolean isSelected = selection.contains(region);
				if (isClicked && !isSelected) selection.add(region);
				else if (isClicked && isSelected) selection.remove(region);
			}
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}
