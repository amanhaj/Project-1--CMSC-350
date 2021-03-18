import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
* Author: Amanda Hajati
* FileName: Project1_GUI.java
* Course: CMSC 350 6381
* Date Completed: 1/23/2019
*/

public class Project1_GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//create the variables for the GUI
	 public JPanel panel;
	 public JLabel enterExpressionLabel;
	 public static JTextField enterExpressionText;
	 public static JButton evaluateButton;
	 public  JLabel resultLabel;
	 public static JTextField resultText;
	//create string to store the user input
	 static String s;
	
//design the GUI
public Project1_GUI() {
	
	setFont(new Font("Arial", Font.PLAIN, 16));
	setTitle("Infix Expression Evaluator");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(1500, 800, 1200, 450);
	
	//initialize components of GUI panel
    panel = new JPanel();
    panel.setLayout(null);
	setContentPane(panel);
    enterExpressionLabel = new JLabel("Enter Infix Expression");
    enterExpressionText = new JTextField("");
    evaluateButton = new JButton("Evaluate");
    resultLabel = new JLabel("Result");
    resultText = new JTextField();
    
    //set result as uneditable
    resultText.setText("");
    resultText.setEditable(false);
    
    //add components to panel
    panel.add(enterExpressionLabel);
    panel.add(enterExpressionText);
    panel.add(evaluateButton);
    panel.add(resultLabel);
    panel.add(resultText);
    
    //set fonts
    enterExpressionLabel.setFont(new Font("Arial", Font.BOLD, 30));
    enterExpressionText.setFont(new Font("Arial", Font.PLAIN, 30));
    evaluateButton.setFont(new Font("Arial", Font.PLAIN, 30));
    resultLabel.setFont(new Font("Arial", Font.BOLD, 30));
    resultText.setFont(new Font("Arial", Font.PLAIN, 30));
    
    //set layout for each component
    enterExpressionLabel.setBounds(100, 50, 350, 50);
    enterExpressionText.setBounds(450, 50, 600, 50);
    evaluateButton.setBounds(500, 150, 200, 65);
    resultLabel.setBounds(200, 265, 115, 50);
    resultText.setBounds(330, 265, 600, 50);
    
    //set columns for textfields
    enterExpressionText.setColumns(20);
    resultText.setColumns(20);
    
    //add action listeners for evaluate button
    Handler_Class handler = new Handler_Class();
	evaluateButton.addActionListener(handler);
	
}

//second class to handle the actions of the button
public class Handler_Class implements ActionListener  {
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==Project1_GUI.evaluateButton) {
				
			//get input from the user when they press evaluate
			Project1_GUI.s = Project1_GUI.enterExpressionText.getText();
				
			//call method from the Project1_Infix class to make calculation
			int result = Project1_Infix.evaluate(Project1_GUI.s);
			String r = String.valueOf(result);
				
			Project1_GUI.resultText.setText(r);
				
}
	//clear data if user clicks the textbar again after evaluating expression
	Project1_GUI.enterExpressionText.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Project1_GUI.enterExpressionText.setText("");
			Project1_GUI.resultText.setText("");
			
		    }
		});
	}}

//main
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		@SuppressWarnings("static-access")
		public void run() {
			
			try {
				try {
					Project1_GUI frame = new Project1_GUI();
					frame.setVisible(true);
					new Project1_Infix().evaluate(s);
					 	
				} catch (Throwable t ) {
					
				}
			} catch (Exception  e) {
				e.printStackTrace();
		  }	
		} 
	});
}}


