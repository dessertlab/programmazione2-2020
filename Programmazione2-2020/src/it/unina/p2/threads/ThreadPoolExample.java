package it.unina.p2.threads;

import java.util.concurrent.*;
public class ThreadPoolExample {

    public static void main(String args[]) {
       Executor service = Executors.newFixedThreadPool(10);
       for (int i =0; i<100; i++){
           service.execute(new Worker(i));
    	       //(new Thread(new Worker(i))).start();
       }
    }
  
}


class Worker implements Runnable{
    
	private int id;
  
    public Worker(int id){
        this.id = id;
    }
  
    @Override
    public void run() {
        System.out.println("Worker " + this.id +" eseguito da " + Thread.currentThread().getName());
    }
  
}
