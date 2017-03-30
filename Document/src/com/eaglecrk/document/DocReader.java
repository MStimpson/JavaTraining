//this is the read file
package com.eaglecrk.document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DocReader implements Reader{
	
	private BufferedReader in = null;
	
	public DocReader(String filename){
	try{
		in = new BufferedReader(new FileReader(filename));
	}catch(IOException ioe){
		ioe.printStackTrace();
	}
}
	
	public String read() throws IOException{
		return in.readLine();
	}	
}
