package be.fastned.application.mvc;

import javax.swing.*;

public class MVCRunner extends JFrame {
	
	static final long serialVersionUID=20080721;

	public MVCRunner(JPanel view){
		super();
		setTitle("FastNed");
		setSize(360, 100);
		setContentPane(view);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);   
	}
	
	public static void main(String[] args) {
		ModelOplossing model = new ModelOplossing();
		new Controller(model);
	}
}
