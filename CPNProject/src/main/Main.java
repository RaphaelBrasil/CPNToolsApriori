package main;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cpn.EncodeDecode;
import cpn.JavaCPN;
import main.ItemSet;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		JavaCPN connS = new JavaCPN();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		ItemSet it = new ItemSet();
		Apriori ap = new Apriori();

		ArrayList<String> transacoes = new ArrayList<String>();
		ArrayList<String> novasTransacoes = new ArrayList<String>();
		ArrayList<String> itens = new ArrayList<String>();
		ArrayList<ArrayList<String>>  map = new ArrayList<ArrayList<String>>();
		String recv;
		while (true) {

			System.out.println("===========================================");
			System.out.println("Starting general communication.");
			System.out.println("Sending port(9001) to CPN tools.");
			connS.accept(9001);
			
			
			while (true) {
				recv = connS.receive().toString();
				if(recv.equals("Close")) {
					
					break;
				}
				transacoes.add(recv);
			}
			
			

			
			System.out.println(transacoes);
			
			novasTransacoes = it.removePequenas(transacoes);
			System.out.println(novasTransacoes);
			
			novasTransacoes = it.organizaItens(novasTransacoes);
			System.out.println(novasTransacoes);
			
			map = it.montaItem2(novasTransacoes);
			it.escreveArquivo("transa.txt", map);
			
			itens = ap.apriori(map, 10);
			connS.send(EncodeDecode.encode(itens.toString()));
			connS.disconnect();
			
			
			System.out.println("Ending general communication.");
			System.out.println("===========================================");

			System.out.println("Restart process? y/n");
			String decision = scanner.nextLine();
			if (!decision.equals("y"))
				
				break;
			transacoes.clear();
			connS.disconnect();
		}

	}

}
