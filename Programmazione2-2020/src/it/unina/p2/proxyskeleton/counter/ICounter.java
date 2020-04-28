package it.unina.p2.proxyskeleton.counter;

// Interfaccia del servizio
public interface ICounter {
	
	void inc();
	void sum(int value);
	int get();
	void square();

}
