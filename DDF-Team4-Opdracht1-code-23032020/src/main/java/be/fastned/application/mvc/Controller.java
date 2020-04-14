package be.fastned.application.mvc;

import java.awt.event.*;

public class Controller implements ActionListener {
	private ModelOplossing model;
	private ViewOplossing view;

	public Controller (ModelOplossing model){
		this.model = model;
		view = new ViewOplossing(model.getOplossingObj());
		view.setListener(this);
		model.addObserver(view);
		new MVCRunner(view);
	}

	public void actionPerformed(ActionEvent e) {
		view.message(this.model);
	}
}
