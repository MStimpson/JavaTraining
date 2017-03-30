package com.numberconvert.nc;

import java.util.Scanner;

public class NumberConverter {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
						
		System.out.println("Enter an integer:");		
		
		try{
			int v = sc.nextInt();
			System.out.println("Convereted to binary: "+Integer.toBinaryString(v));
			System.out.println("Convereted to hex: "   +Integer.toHexString(v));
			System.out.println("Convereted to octal: " +Integer.toOctalString(v));
			//System.out.println(String.format("%4o %3d %2X %c",v, v, v, v, toAscii(v)));
		} catch (Exception e) {System.out.println("That's not a valid number!");}
			
		sc.nextLine();		
	}
	
	public static char toAscii(int num){
		return ((num > 32 && num < 127) ? (char)num: ' ');
	}
}
