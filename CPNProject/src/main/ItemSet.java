package main;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemSet {
	/**
	 * 1. config.txt - three lines, each line is an integer
	 *          line 1 - number of items per transaction
	 *          line 2 - number of transactions
	 *          line 3 - minsup
	 * 2. transa.txt - transaction file, each line is a transaction, items are separated by a space
	 * */
    public ArrayList<String> removePequenas(ArrayList<String> in) throws IOException
    {
    	for(int i = 0; i < in.size(); i++)
        {
    		String transacao = in.get(i);

            if(transacao.length() < 3)
            {
                // Encontrou um item cadastrado com menos de 3 transacoes

                // Remove.
            	in.remove(transacao);
            	i--;
            }
        }
		return in;
    }
    
    public ArrayList<String> montaItem(ArrayList<String> in) throws IOException
    {
    	
    	ArrayList<String> map = new ArrayList<String>();
    	for(int i = 0; i < in.size(); i++)
        {
    		String transacao = "";
    		for (char ch = 'A'; ch <= 'H'; ch++) {
    		    //lista.add(Character.valueOf(ch));
    			if (in.get(i).indexOf(ch) > -1) {
    				transacao = transacao.concat(" 1");
        		}else {
        			transacao = transacao.concat(" 0");
        		}
    			
    		}
    		map.add(transacao);
    		
        }
		return map;
    } 
    
    public ArrayList<ArrayList<String>> montaItem2(ArrayList<String> in) throws IOException
    {
    	
    	ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
    	for(int i = 0; i < in.size(); i++)
        {
    		ArrayList<String> transacao = new ArrayList<String>();
    		for (char ch = 'A'; ch <= 'H'; ch++) {
    		    //lista.add(Character.valueOf(ch));
    			if (in.get(i).indexOf(ch) > -1) {
    				transacao.add(""+ch);
        		}
    			
    		}
    		
    		map.add(transacao);
        }
		return map;
    }
    @SuppressWarnings("resource")
	public void escreveArquivo(String caminho, ArrayList<ArrayList<String>> in) throws IOException
    {
    	new FileOutputStream(caminho);
    	FileWriter arq = new FileWriter(caminho);
        PrintWriter gravarArq = new PrintWriter(arq);
        for(int i = 0; i < in.size(); i++)
        {
        	gravarArq.println(in.get(i));
        }
        arq.close();
    }
    
    public static String sortString(String inputString) 
    { 
        // convert input string to char array 
        char tempArray[] = inputString.toCharArray(); 
          
        // sort tempArray 
        Arrays.sort(tempArray); 
          
        // return new sorted string 
        return new String(tempArray); 
    } 
    

    public ArrayList<String> organizaItens(ArrayList<String> in) throws IOException
    {
    	ArrayList<String> out = new ArrayList<String>();
    	for(int i = 0; i < in.size(); i++)
        {
    		String transacao = in.get(i);
    		transacao = sortString(transacao);
    		
    		transacao = removeDupsInPlace(transacao);

            out.add(transacao);
        }
		return out;
    }
    public static String removeDupsInPlace(String word) {
        final StringBuilder output = new StringBuilder();
     
        for (int i = 0; i < word.length(); i++) {
          String character = word.substring(i, i + 1);
          if (output.indexOf(character) < 0) // if not contained
            output.append(character);
        }
        return output.toString();
      }
    
}
