package com.eaglcrk.threading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task extends Thread{

	public boolean active = true;
	public int index;
	private String line;
	
	public Task(int index){
		this.index=index;
	}
	public void run(String path) throws IOException{

		@SuppressWarnings("resource")
		BufferedReader in=new BufferedReader(new FileReader(path));
		
		while((line = in.readLine()) !=null){
			String row = line.trim();
			if(row.contains("MyPassword")){
				System.out.println(path);
			}
		}
	}
}