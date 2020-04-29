package it.unina.p2.esercitazione.networking.UDPserver;

import java.io.IOException;

public interface IRubrica {

	void aggiungiContatto(Contatto c) throws ContattoAlreadyExistent, IOException;
	Contatto cercaContatto(String nome, String cognome) throws ContattoNotFound, IOException;
	int totaleContatti() throws IOException;

}
