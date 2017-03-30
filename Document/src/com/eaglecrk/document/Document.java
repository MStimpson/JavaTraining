package com.eaglecrk.document;

import java.io.IOException;
import java.util.Random;
import java.util.TreeMap;

public class Document {
	
public static void main(String[] args){
	String t, fin = "";
	StringBuilder sb = new StringBuilder();
		try{	
			
			Reader read = new DocReader("data/input.txt");			
						
			while(true){		
				t = read.read();
				if(t == null){break;}
				fin = fin+ " \n" +t;
				sb.append(t);
				for(String st : t.split(" ")){
				    if(st.startsWith("t")){
				        //System.out.println("I found a word that"
				        		//	 + " begins with t: "+st);
				    }
				}
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		//System.out.println("Read: " + fin);
		//System.out.println(fin.length());
		//System.out.println(fin.charAt(23));
		
		//System.out.println(sb.toString().replaceAll("[\\[() ;\\]]+", " "));
	
		//.*, .?, .+
		
		//**************************************//
		TreeMap<String, Integer> map = new TreeMap<>();
		Random rn = new Random();
		
		for(int z=0;z<5;z++){
			
		map.put("words",new Integer(rn.nextInt(1000)));
		map.put("characters", new Integer(rn.nextInt(1000)));
		
		Integer i = map.get("sentences");
		if(i == null)
			i = new Integer(1);
		else
			i = new Integer(i.intValue() +1);
			map.put("sentences", i);
		}
		for(String str: map.keySet()){
			System.out.println(str + ": " + map.get(str));
			
		}
		
		
	}
}
