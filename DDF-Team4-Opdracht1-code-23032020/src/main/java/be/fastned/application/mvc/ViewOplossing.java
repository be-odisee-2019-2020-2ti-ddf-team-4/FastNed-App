package be.fastned.application.mvc;

import be.fastned.application.domain.Oplossing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ViewOplossing extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private Oplossing oplossingObj;

	private  JLabel labelField;
	private JTextField oplossingField;
	private JButton oplossingButton;
	
	public ViewOplossing(Oplossing oplossingObj) {
		super();
		this.oplossingObj = oplossingObj;
		this.createGui();
	}
	
	private void createGui() {
		this.setLayout(new FlowLayout());

		labelField = new JLabel();
		labelField.setText("Oplossing tekst: ");
		this.add(labelField);

		oplossingField = new JTextField(oplossingObj.getOplossing());
		this.add(oplossingField);

		oplossingButton = new JButton();
		oplossingButton.setText("Confirm Input");
		this.add(oplossingButton);
	}
	
	public void setListener (ActionListener listener){
		oplossingButton.addActionListener(listener);
	}

	public void message(Observable o) {
		ModelOplossing model = (ModelOplossing) o;
		((ModelOplossing) o).setOplossing(oplossingField.getText());
		JOptionPane.showMessageDialog(null, ((ModelOplossing) o).getOplossing(), "InfoBox: " + "TESTED", JOptionPane.INFORMATION_MESSAGE);
	}

	public void update(Observable o, Object arg) {
		ModelOplossing model = (ModelOplossing) o;
	}

	public String getOplossingFieldValue(){
		return oplossingField.getText();
	}
}
