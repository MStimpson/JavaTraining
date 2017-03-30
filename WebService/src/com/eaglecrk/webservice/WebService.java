package com.eaglecrk.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebService {
	public String get(String url)throws Exception{
		StringBuilder text=new StringBuilder();
		URL site=new URL(url);
		URLConnection conn= site.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while((line=in.readLine()) != null){
			line=line.replaceAll("\\<.*?\\>", "");
			line=line.trim();
			text.append(line+"\n");
		}
		in.close();
		return text.toString();
	}
			
	public static void main(String[] args){
		try{
			WebService ws=new WebService();
			System.out.println(ws.get("http://apache.org"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
