package it.unina.p2.threads;


public class ExtThread extends Thread {
	private int threadNumber;
	
	public ExtThread(int i) {
		this.threadNumber = i;
	}
	
	public void run(){
		System.out.println("I am an EXTENDED thread n " + threadNumber);
	}
	
}
