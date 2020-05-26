package it.unina.p2.jms.magazzinojms.server;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Magazzino {
	/*
	 * Magazzino gestisce una coda a gestione circolare. La dimensione della coda e' pari a 10. All'arrivo di 
		un  nuovo  messaggio client, il  Magazzino  crea  un  nuovo  thread,  che  estrae  le  informazioni  dal 
		messaggio (tipo richiesta, id_articolo).
		Se  il  messaggio  e'  di  tipo  'deposita', id_articolo e'  inserito  nella  coda. Nel  caso di un  messaggio 
		'preleva', viene prelavato l'id_articolo in testa alla coda e restituito al client tramite la coda Risposta. 
		Quando la coda e' piena, il thread che effettua un deposito e' messo in attesa; analogamente, se la coda e' 
		vuota,  il  thread  che  effettua  una preleva e'  messo  in  attesa.  L'accesso  alla  coda  e'  gestito  in  mutua 
		esclusione.
	 */
	
	private int[] coda;
	private final Lock lock;
	private final Condition nonPieno;
	private final Condition nonVuoto;
	private int putptr, takeptr, count;
	
	public Magazzino(){
		coda = new int[10];
		lock = new ReentrantLock();
		nonPieno = lock.newCondition();
		nonVuoto = lock.newCondition();
		putptr = 0;
		takeptr = 0;
		count = 0;
	}
	
	public void Deposita(int valore){
		lock.lock();
		try{
			while(isPieno()) nonPieno.await();
			coda[putptr] = valore;
			System.out.println("[SERVER-LOG]Depositato valore: " + valore +" in posizione: " + putptr );
			putptr = (putptr + 1) % coda.length;
			count++;
			nonVuoto.signal();
		} catch (InterruptedException e) {
			// Eccezione lanciata dalla await
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public int Preleva(){
		lock.lock();
		try{
			while(isVuoto()) nonVuoto.await();
			int valorePrelevato = coda[takeptr];
			System.out.println("[SERVER-LOG]Prelevato valore: " + valorePrelevato +" dalla posizione: " + takeptr );
			takeptr = (takeptr + 1) % coda.length;
			count--;
			nonPieno.signal();
			return valorePrelevato;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return 0;
	}
	
	private boolean isPieno(){
		return count == coda.length;
	}
	
	private boolean isVuoto(){
		return count == 0;
	}
	
	
}
