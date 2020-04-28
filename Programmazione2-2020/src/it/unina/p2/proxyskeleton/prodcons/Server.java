package it.unina.p2.proxyskeleton.prodcons;

public class Server {
	public static void main(String[] args) {
		ServizioReale servizio = new ServizioReale();
		System.out.println("Server avviato");
		servizio.runSkeleton();
	}
}
