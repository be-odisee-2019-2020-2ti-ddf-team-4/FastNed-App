package be.fastned.application.mvc;

import be.fastned.application.domain.Oplossing;

import java.util.Observable;

import javax.swing.JOptionPane;

class ModelOplossing extends Observable {
	private String oplossing;
	private Oplossing oplossingObj;
  
  	public ModelOplossing() {
  		setOplossing("TEST");
    }

	public String getOplossing(){
		return this.oplossing;
	}

	public Oplossing getOplossingObj(){
		return this.oplossingObj;
	}

	public void setOplossing(String oplossing) {
		this.oplossing = oplossing;
		oplossingObj = new Oplossing(oplossing);

		setChanged();
		notifyObservers();
	}
}