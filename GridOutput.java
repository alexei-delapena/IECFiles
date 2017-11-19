import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;


public class GridOutput {

	public static void main(String[] args){
		
		GridOutput go = new GridOutput();
		
	}
	
	public GridOutput(){
		int scale = 100;
		int wan = 500;
		int tuu = 600;
		int tree = 700;
		JFrame frame = new JFrame();
		
		CartesianPanel panel = new CartesianPanel();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.drawPoint(new Point((wan/scale), (tuu/scale)));
		
		
		JFrame frame2 = new JFrame();
		JPanel popupPanel = new JPanel();
		popupPanel.setLayout(new GridLayout(2,1));
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		label.setText("The coordinates for this point is (" + wan + ", " + tuu + ").");
		label2.setText("The angle bearing is " + scale + ".");
		popupPanel.add(label);
		popupPanel.add(label2);
		frame2.add(popupPanel);
		frame2.setSize(400, 100);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}

class CartesianPanel extends JPanel{
	public static final int X_AXIS_FIRST_X_COORD = 50;
	 public static final int X_AXIS_SECOND_X_COORD = 600;
	 public static final int X_AXIS_Y_COORD = 600;

	 // y-axis coord constants
	 public static final int Y_AXIS_FIRST_Y_COORD = 50;
	 public static final int Y_AXIS_SECOND_Y_COORD = 600;
	 public static final int Y_AXIS_X_COORD = 50;

	 public static final int FIRST_LENGHT = 10;
	 public static final int SECOND_LENGHT = 5;

	 // size of start coordinate length
	 public static final int ORIGIN_COORDINATE_LENGHT = 6;

	 // distance of coordinate strings from axis
	 public static final int AXIS_STRING_DISTANCE = 20;

	 private List<Point> points = new ArrayList<>();
	 
	 private int xCoordNumbers = 10;
	 private int yCoordNumbers = 10;
	 private int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)
	      / xCoordNumbers;
	 private int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)
	      / yCoordNumbers;

	 public void drawPoint(Point point) {
	     points.add(point);
	     repaint();
	 }

	 private void drawPointOnPanel(Point point, Graphics g) {
	     final int pointDiameter = 5;
	     final int x = X_AXIS_FIRST_X_COORD + (point.x * xLength) - pointDiameter / 2;
	     final int y = Y_AXIS_SECOND_Y_COORD - (point.y * yLength) - pointDiameter / 2;
	     g.fillOval(x, y, pointDiameter, pointDiameter);
	 }

	 public void paintComponent(Graphics g) {
	     // existing code ...
		 super.paintComponent(g);

		  Graphics2D g2 = (Graphics2D) g;

		  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		    RenderingHints.VALUE_ANTIALIAS_ON);

		  // x-axis
		  g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD,
		     X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		  // y-axis
		  g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD, Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

		  // draw origin Point
		    g2.fillOval(X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2), 
		    Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
		    ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);

		    int j = 100;
		    int k = 100;
		    
		  // draw x-axis numbers
		  for(int i = 1; i < xCoordNumbers; i++) {
		   g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
		     X_AXIS_Y_COORD - SECOND_LENGHT,
		     X_AXIS_FIRST_X_COORD + (i * xLength),
		     X_AXIS_Y_COORD + SECOND_LENGHT);
		   g2.drawString(Integer.toString(j), 
		     X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
		     X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		   j+=100;
		  }

		  //draw y-axis numbers
		  for(int i = 1; i < yCoordNumbers; i++) {
		   g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
		     Y_AXIS_SECOND_Y_COORD - (i * yLength), 
		     Y_AXIS_X_COORD + SECOND_LENGHT,
		     Y_AXIS_SECOND_Y_COORD - (i * yLength));
		   g2.drawString(Integer.toString(k), 
		     Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, 
		     Y_AXIS_SECOND_Y_COORD - (i * yLength));
		   k+=100;
		  }
	     // draw points
	     points.forEach(p -> drawPointOnPanel(p, g));
	 }
}