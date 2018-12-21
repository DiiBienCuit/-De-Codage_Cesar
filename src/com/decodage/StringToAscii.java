package com.decodage;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.lang.*; 
import java.nio.charset.*; 
import java.io.*;

public class StringToAscii {
	private StringBuilder sb;
	private int decalage;
	private String cle;
	
	//constructor
	public StringToAscii(StringBuilder sb) {
		this.sb = sb;
	}
	
	//function to set the decalage entered by user
	public void setDecalage(int decalage){
		this.decalage = decalage;
	} 
	
	//function to set the vigenere key entered by user
	public void setCle(String cle){
		this.cle = cle;
	}
	
	public ArrayList getCleDecalage(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i<this.cle.length();i++){
			int ascii = (int)this.cle.charAt(i);
			list.add(ascii-65);
		}
		return list;
	}
	//function to code the message by turning to ascii code first
	public ArrayList code() {
		String sb2 = this.sb.toString();
		ArrayList<Integer> list = new ArrayList<Integer>();
    	for (char c : sb2.toCharArray()){
			list.add((int)c);
		}
		return list;
	}
	
	
	public String Code_Vignere(ArrayList<Integer> list_msg, ArrayList<Integer> list_cle){
		
		StringBuilder sb2 = new StringBuilder() ;
		int key_length = this.cle.length();
		//System.out.println(key_length); 
		for(int i = 0;i<this.sb.length();i++){
			StringBuilder sb1 = new StringBuilder();
			sb1.append(this.cle.charAt(i));
			ArrayList<Integer> list_local = new ArrayList<Integer>();
			int position = i % key_length;
			list_local.add(list_msg.get(i));
			StringToAscii local = new StringToAscii(sb1);
			String char_local = local.AsciiToString(list_local, position);
			sb2.append(char_local);
		}
		String result = sb2.toString();
		return result;
	}
	
	//A-Z = 65-90
	//a-z = 97 -122
	//function turn the ascii code to chars, then return the coded message
	public String AsciiToString(ArrayList<Integer> list,int decalage){
		StringBuilder sb1 = new StringBuilder() ;
		int isMaj;
		for(int i : list){
			if(i >=97 && i <= 122){
				//is min
				isMaj = 0;
			}else if(i >=65 && i <= 90){
				//is maj
				isMaj = 1;
			}else{
				//char is not an alphabet char
				isMaj = 2;
			}
			int j = i + decalage;

			//check if its out of the range
			switch(isMaj){
				case 0:
					if(j>122){
						j = j-26;
					}else if(j < 97){
						j = j+26;
					}
					break;
				case 1:
					if(j>90){
						j = j-26;
					}else if(j < 65){
						j = j+26;
					}
					break;
				// dont change those chars whos not regular alphabet 
				case 2:
					j = i;
					break;
				default:
					break;
			}
			sb1.append((char)j);

		}
		String sb2 = sb1.toString();
		return sb2;
	}
	
	//function to calculate chars' frequency, used for decoding
	public HashMap count_frequence(){
		HashMap<Character, Double> map = new HashMap<Character, Double>();
		String sb1 = this.sb.toString().toUpperCase().trim();
		int total = 0;
		for(char c: sb1.toCharArray()){
			if(c != ' ' && c != ' ' && (c >= 'A' && c <= 'Z')){
				total ++;
			}
			
		}
		for(char c: sb1.toCharArray()){
			int counter = 0;
			//test if the char was already added into the map
			//public boolean map.containsKey(valueOfAKey)
			if(c != ' ' && c != ' ' && (c >= 'A' && c <= 'Z')){
				if(!map.containsKey(c)){
					//System.out.println(c);  
					for(char ch : sb1.toCharArray()){
						if(ch == c){
							counter ++;
						}
						
					}
					//System.out.println(counter);
					map.put(c, (double)counter*100/total);
				}
			}
		}
		return map;
	}
	
	//print the frequency in console
	public void getFreqencyHashMap(){
		HashMap<Character, Double> map = new HashMap<Character, Double>();
		map = this.count_frequence();
		System.out.println("----------------------------------------");
		System.out.println("Table de frequence:");
		for (char ch: map.keySet()){

            String key = Character.toString(ch);
            String value = map.get(ch).toString();  
            System.out.println(key + " " + value + " %");  
		} 
	}
	
	//function to calculate the decalage
	//frequence = ["E","A","S","I","N","T","R","L","U","O","D","C","P","M","V","G","F","B","Q","H","X","J","Y","Z","K","W"];
	//Frequence for the language in French 
	public int calculateDecalage(){
		int decalage;
		double maxFrequency = 0;
		char codedE = 'E';
		HashMap<Character, Double> map = new HashMap<Character, Double>();
		map = this.count_frequence();
		
		//get the max value of frequency
		for (char ch: map.keySet()){
            char key = ch;
            double value = map.get(ch);  
            if(value > maxFrequency){
            	maxFrequency = value;
            	codedE = key;
            }
		} 
		decalage = (int)'E' - (int)codedE ;
		System.out.println("le decalage est : " + -decalage);  
		this.setDecalage(decalage);
		return decalage;
	}
	
	//test main function
	public static void main(String args[]) throws ParseException {
		StringBuilder text= new StringBuilder(); 
		text.append("try to code me!");
		StringToAscii toByte = new StringToAscii(text);
		// translating text String to 7 bit ASCII encoding 
		ArrayList<Integer> list = new ArrayList<Integer>();
		int decalage = -3;
		toByte.setDecalage(decalage);

		System.out.println("ASCII value of " + text + " is following"); 
		System.out.println(toByte.code()); 
		list = toByte.code();
		System.out.println("after:"); 
		System.out.println(toByte.AsciiToString(list,decalage)); 
		
		}
	
	
	}

