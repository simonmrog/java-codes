import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Subject extends JPanel {

	//Subject parameters
	String subjectName;
	String[] columnNames = {"Descripci�n", "Valor (%)", "Nota (0-5)"};
	int numberOfRows, numberOfActiveRows;
	
	//Subject components
	JTable table;
	DefaultTableModel model; //model for the table
	JScrollPane scrollpane; 
	JTextField subjectTitleField;
	JTextField subjectGrade, subjectCredits;
	
	public Subject (int rows, int width, int height) {
			
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		//Creates a textfield for the title
		subjectTitleField = new JTextField("Nombre");
		subjectTitleField.setMaximumSize(new Dimension(200, 800));
		subjectTitleField.setHorizontalAlignment(JTextField.CENTER);
		
		JPanel panel = new JPanel();
		
		//Creates a textfield for the grade
		subjectGrade = new JTextField("Nota acumulada");
		subjectGrade.setPreferredSize(new Dimension(100, 30));
		subjectGrade.setHorizontalAlignment(JTextField.CENTER);
		
		//Creates a textfield for the credits
		subjectCredits = new JTextField("Cr�ditos");
		subjectCredits.setPreferredSize(new Dimension(100, 30));
		subjectCredits.setHorizontalAlignment(JTextField.CENTER);
		
		panel.add(subjectGrade);
		panel.add(subjectCredits);
		
		//Creates and configures our subject table
        model = new DefaultTableModel(columnNames, rows);
		table = new JTable(model);
		table.setRowHeight(25);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.setPreferredScrollableViewportSize(new Dimension(width, height));
		scrollpane = new JScrollPane(table);
		
		for (int i=0; i<10; i++) {
			table.setValueAt(" ", i, 0);
			table.setValueAt(" ", i, 1);
			table.setValueAt(" ", i, 2);
		}
		
		setLayout(layout);
		setBorder(BorderFactory.createLineBorder(Color.black));
		add(subjectTitleField);
		add(panel);
		add(scrollpane);

	}
	
	public String getName() {
		return subjectName;
	}
	
	public float getGrade() {
		String line = subjectGrade.getText();
		return Float.parseFloat(line);
	}
	
	public int getNumberOfRows() {
		
		numberOfRows = 0;
		String line = (String) table.getValueAt(0, 2);
		for (int i=1; !line.equals(" "); i++) {
			numberOfRows = numberOfRows + 1;
			line = (String) table.getValueAt(i, 2);
		}
		return numberOfRows;
	}
	
	public int getCredits() {
		String line = subjectCredits.getText();
		return Integer.parseInt(line);
	}
	
	public void setName(String name) {
		subjectTitleField.setText(name);
		subjectName = name;
	}
	
	public void setGrade(String grade) {
		subjectGrade.setText(grade);
	}
	
	public void setNumberOfRows(int rows) {
		numberOfRows = rows;
	}
	
	public void setValueAt(String value, int i, int j) {
		if (i < table.getRowCount() && j < table.getColumnCount())
			table.setValueAt(value, i, j);
	}
	
	public String getValueAt(int i, int j) {
		return (String) (table.getValueAt(i, j));
	}
	
	
	public void centerTextInTable() {
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0; i<table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	}

	public void setCredits(String credits) {
		subjectCredits.setText(credits);
	}
	
	public float getSubjectGrade() {
		
		float perc, grade, value = 0;
		
		for (int i=0; i<numberOfRows; i++) {
			
			perc = (float) table.getModel().getValueAt(i,1);
			grade = (float) table.getModel().getValueAt(i, 2);
			value = value + perc*grade;
		}
		return value/100;	
	}
	
	public void showSubjectStatus() {
		
		String desc, perc, grades;
		
		for (int i=0; i<numberOfRows; i++) {
			
			desc = (String) table.getModel().getValueAt(i,0);
			perc = (String) table.getModel().getValueAt(i,1);
			grades = (String) table.getModel().getValueAt(i,2);
			System.out.println(desc + " " + perc + " " + grades);
		}
	}
}
