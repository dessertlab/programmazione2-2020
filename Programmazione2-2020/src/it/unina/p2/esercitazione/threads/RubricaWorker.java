package it.unina.p2.esercitazione.threads;

public class RubricaWorker extends Thread {

	private Contatto contatto;
	private RubricaThreadedApp rubrica;
	
	public RubricaWorker(RubricaThreadedApp rubrica, Contatto contatto) {
		
		this.contatto = contatto;
		this.rubrica = rubrica;
	}

	public void run() {

		rubrica.addContatto(contatto);
	}

}
