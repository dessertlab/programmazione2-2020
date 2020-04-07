package it.unina.p2.threads;

public class MyThread implements Runnable {

	private int threadNumber;

	public MyThread(int i) {
		this.threadNumber = i;
	}
	
	@Override
	public void run() {

		System.out.println("I am a thread (using interfaces) n " + threadNumber);

	}

}
