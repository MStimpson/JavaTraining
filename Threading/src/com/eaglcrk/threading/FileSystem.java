/**
 * 
 * Recursion
 * 
 * */
package com.eaglcrk.threading;

import java.io.File;

public class FileSystem {
	
	public static void listFiles(File path){
		try{
			int i=1;
			File[] files= path.listFiles();
			for(File f : files){
				if(f.isDirectory()){
					listFiles(new File(f.getCanonicalPath()));			
				}else{
					if(f.getName().toLowerCase().endsWith(".txt")){
						Task task = new Task(i);
						task.run(f.getCanonicalPath());
						i++;
					}
				}
			}	
		}catch(Exception ex){}
	}
	
	public static void main(String[] args){	
		listFiles(new File("C:\\Users\\mstimpson\\Documents\\Testdir"));		
	}
}