package com.decodage;
import java.lang.*; 
import java.nio.charset.*; 
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.filechooser.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/*
 * @TO DO
 * test pane add cases for decoding
 * add for cesar whether you know the decalage?
 * add coding vigenere
 * add decoding vigenere
 * add whether you know the key? Start from the case tht we know the key
 */


//main function for code message using methode Cesar
public class Chiffrement{
	
	//function main
	public static void main(String args[]) {
		new Chiffrement();
	}
	
	public Chiffrement() {
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	int decalage = -1;
            	String cle = "NO KEY DEFINED";
            	int choix = 0;
            	String cod_mode = "[NO MODE DEFINED]";
            	
            	Scanner sc = new Scanner(System.in);
            	do{
            		System.out.println("------------------MENU------------------");
        			
            		System.out.println("1: Codage Cesar");
            		System.out.println("2: Codage Veginere");
            		System.out.println("----------------------------------------");
            		System.out.println("Veuillez choisir la methode de codage:");
            		choix = sc.nextInt();
            		if(choix != 1 && choix !=2){
            			System.out.println("---!---!---!---ERROR---!---!---!---");
            			System.out.println("Veuillez entrez integer 1 ou 2!!");
            		}
            	}while(choix != 1 && choix !=2);
            	System.out.println("----------------------------------------");
            	
            	//Coding in cesar
            	if(choix == 1){
            		cod_mode = "[CESAR]";
                	do{
                        System.out.println(cod_mode+" Entrez le nombre de caractere a decaler:");
                        decalage = sc.nextInt();
                        
                    }while(decalage < 0);
            	}else if(choix == 2){
            		cod_mode = "[VIGENERE]";
            		do{
            			cle = sc.nextLine();
            			cle = cle.toUpperCase();
                        System.out.println(cle);
                        System.out.println(cod_mode+ " Entrez votre cle (en alphabet):");    
                        
                    }while(!cle.matches("[A-Za-z]+"));
            	}else{
            		System.out.println("Why you are here?");	
            	}
            	System.out.println("----------------------------------------");
            	System.out.println(cod_mode + " Importer le fichier à coder:");
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                //define and add the java frame for GUI
                AddPane(decalage,cle,choix);
            }
        });
    }
	
	public void AddPane(int decalage,String cle,int choix){
		//define and add the java frame for GUI
        JFrame frame = new JFrame("Codage Cesar & Vigenere");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TestPane(decalage,cle,choix,1));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
}