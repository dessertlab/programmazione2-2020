package it.unina.p2.io;

import java.util.StringTokenizer;

public class StringTokenApp {

	public static void main(String[] args) {

		String content[] = new String[2];
		
		content[0] = "Roberto,INFORMATICA,36";
		content[1] = "Pietro,ELETTRONICA,30";
		
		int i;
		for(i=0; i<content.length; i++) {
			
			StringTokenizer tok = new StringTokenizer(content[i], ",");
			
			String nome = tok.nextToken();
			String corso = tok.nextToken();
			int eta = Integer.parseInt(tok.nextToken());
			
			System.out.println("----");
			System.out.println("Nome:\t"+nome);
			System.out.println("Corso:\t"+corso);
			System.out.println("Età:\t"+eta);
		}
		
	}

}
