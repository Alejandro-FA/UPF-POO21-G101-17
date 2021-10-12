
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyGUI extends JPanel implements ActionListener {

	// This can be ignored for now...
	private static final long serialVersionUID = 1000L;

	// A GridLayout is a convenient way to organize the GUI components
	private GridLayout layout;

	// The GUI components are defined here
	private JButton button;
	private JTextField field_name, field_nPlanes;
	// private JComboBox<String> box;
	private JLabel label_name, label_nPlanes;

	public MyGUI() {
		// Add the components of the GUI
		initComponents();

		// Place the GUI inside a window and show it on the screen
		JFrame frame = new JFrame("My GUI");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void initComponents() {
		// Initialize GUI components
		button = new JButton("Create Airline");
		button.addActionListener(this);

		field_name = new JTextField();
		field_nPlanes = new JTextField();
		// box = new JComboBox<String>(new String[] { "Option 1", "Option 2", "Option 3" });
		label_name = new JLabel("Airline name");
		label_nPlanes = new JLabel("Number of airplanes");

		// Create a new layout
		layout = new GridLayout(3, 2);
		setLayout(layout);
		// add(box);
		add(label_name);
		add(label_nPlanes);
		add(field_name);
		add(field_nPlanes);
		add(button);
	}

	public void actionPerformed( ActionEvent evt ) {
		Airline airline = new Airline( field_name.getText() , Integer.parseInt(field_nPlanes.getText()));
		airline.printAirline();
    	// label.setText(airline.getName());
	}

}
