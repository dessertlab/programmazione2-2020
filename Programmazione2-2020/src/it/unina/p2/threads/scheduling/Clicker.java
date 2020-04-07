package it.unina.p2.threads.scheduling;

   public class Clicker implements Runnable {
     private int click = 0;
     private Thread t;
     public volatile boolean running = true;
     public Clicker(int p) {
       t = new Thread(this);
       t.setPriority(p);
     }
     public int getClick(){
      return click;
    }
    public void run() {
      System.out.println("Running thread is: "+this.t.getName()+", with Priority: "+this.t.getPriority());
      //System.out.println("Thread "+this.t.getPriority());
    	while (this.running) {
        click++;
      //System.out.println("Running thread is: "+this.t.getName()+", with Priority: "+this.t.getPriority()+" clicks "+click);
      }
    	 System.out.println("Stopped "+this.t.getName());
    }
    public void stopThread() {
      running = false;
      System.out.println("stopThread: Stopped "+this.t.getName());
      System.out.println("stopThread: running "+this.running);
    }
    public void startThread() {
      t.start();
    }
    public boolean isAlive() {
    	return t.isAlive();
    }
  }



