package it.unina.p2.esercitazione.threads;

public class RubricaPrinter extends Thread {

	private RubricaThreadedApp rubrica;
	
	public RubricaPrinter(RubricaThreadedApp rubrica) {
		
		this.rubrica = rubrica;
	}

	public void run() {
		
		rubrica.stampaContatti(4);
	}

}
