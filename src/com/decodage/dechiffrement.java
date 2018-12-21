package com.decodage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import com.decodage.TestPane;

public class dechiffrement {
	
	public static void main(String args[]) {
		new dechiffrement();
	}
	
	public dechiffrement(){
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	boolean cle_connue = false;
	            	char cle_entered;
	            	int cle_cesar = -1;
	            	String cle_vigenere = "";
	            	Scanner sc = new Scanner(System.in);
	            	int choix = 0;
	            	
	            	//ask the user if he knows the decode method
	            	do{
	            		System.out.println("------------------MENU------------------");
	            		System.out.println("1: Codage Cesar");
	            		System.out.println("2: Codage Veginere");
	            		System.out.println("3: Je ne sais pas :(");
	            		System.out.println("----------------------------------------");
	            		System.out.println("Veuillez choisir la methode de decodage:");
	            		choix = sc.nextInt();
	            		if(choix != 1 && choix !=2 && choix != 3){
	            			System.out.println("---!---!---!---ERROR---!---!---!---");
	            			System.out.println("Veuillez entrez integer 1, 2 ou 3!!");
	      
	            		}
	            	}while(choix != 1 && choix !=2 && choix != 3);
	            	
	            	//ask the user if he knows the decoding key
	            	if(choix == 2 || choix == 1){
	            		do{
		            		System.out.println("----------------------------------------");
		            		System.out.println("[Dechiffrement] Connaissez-vous la clé pour déchiffrer? (Entrez Y pour Oui, N pour Non)");
		            		cle_entered = sc.next().charAt(0);
		            		cle_entered = Character.toUpperCase(cle_entered);
		            		
		            	}while(cle_entered != 'Y' && cle_entered != 'N');
		            	
		            	if(cle_entered == 'Y'){
		            		cle_connue = true;
		            		
		            		switch(choix){
		            		case 1:
		            			do{
		            				System.out.println("[Dechiffrement] Entrez la cle (qui sera un entier positive)");
				            		cle_cesar = sc.nextInt();
				            		//System.out.println(cle_cesar);
		            			}while(cle_cesar < 0);
		            			
		            			break;
		            		case 2: 
		            			do{
		            				cle_vigenere = sc.nextLine();
		            				System.out.println("[Dechiffrement] Entrez la cle (qui sera une string)");
		            				cle_vigenere = cle_vigenere.toUpperCase();
				            		
		            			}while(!cle_vigenere.matches("[A-Za-z]+"));
		            			System.out.println(cle_vigenere);
		            			//System.out.println("Je pourrais pas vous aider sur ça pour l'instant :(");
		            			break;
		            		default: break;
		            		}
		            	}else{
		            		cle_connue = false;
		            	}
	            	}else{
	            		//@TODO
	            		System.out.println("Je pourrais pas vous aider sur ça pour l'instant :(");
	            	}
	            	
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                    ex.printStackTrace();
	                }
	                
	                //define and add the java frame for GUI
	                JFrame frame = new JFrame("Dechiffrement");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.add(new TestPane(cle_cesar,cle_vigenere,choix,2));
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	}
}
