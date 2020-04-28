package it.unina.p2.proxyskeleton.prodcons;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServizioReale extends Skeleton {

	public int[] coda;
	public final Lock lock = new ReentrantLock();
	public final Condition nonPieno = lock.newCondition();
	public final Condition nonVuoto = lock.newCondition();
	int count,putptr,takeptr;
	
	
	public ServizioReale(){
		coda = new int[5];
	}
	
	@Override
	public void produci(int i) {
		lock.lock();
		try{
			while(isPieno()) nonPieno.await();
			coda[putptr] = i;
			System.out.println("Prodotto valore: " + i + " , in posizione : " + putptr);
			putptr = ( putptr + 1 ) % coda.length;
			count++;
			nonVuoto.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}

	@Override
	public int consuma() {
		lock.lock();
		try{
			while(isVuoto()) nonVuoto.await();
			int returnme = coda[takeptr];
			System.out.println("Prelevato : " + returnme + " da posizione : " + takeptr);
			takeptr = (takeptr + 1) % coda.length;
			count--;
			nonPieno.signal();
			return returnme;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return 0;
	}
	
	private boolean isVuoto(){
		return count == coda.length;
	}
	
	private boolean isPieno(){
		return count == 0;
	}

}
