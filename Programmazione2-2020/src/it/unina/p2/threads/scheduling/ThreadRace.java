package it.unina.p2.threads.scheduling;
  public class ThreadRace {
    public static void main(String args[]) {
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
      Clicker hi = new Clicker(Thread.NORM_PRIORITY + 2);
      Clicker lo = new Clicker(Thread.NORM_PRIORITY - 2);
      lo.startThread();
      hi.startThread();
      try {
        Thread.sleep(3000);
      }
      catch (Exception e){}
      lo.stopThread();
      hi.stopThread();
      System.out.println(lo.getClick()+" vs. "+hi.getClick());
      System.out.println(lo.isAlive());
      System.out.println(hi.isAlive());
    }
  }