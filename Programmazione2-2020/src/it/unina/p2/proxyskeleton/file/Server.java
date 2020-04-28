package it.unina.p2.proxyskeleton.file;


public class Server {
	public static void main(String[] args) {
		ServizioReale serv = new ServizioReale();
		serv.runSkeleton();
	}
}
