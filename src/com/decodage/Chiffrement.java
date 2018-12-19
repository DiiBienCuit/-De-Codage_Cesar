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
            	String cle = "";
            	int choix = 0;
            	Scanner sc = new Scanner(System.in);
            	do{
            		System.out.println("------------------MENU------------------");
        			
            		System.out.println("1: Codage Cesar");
            		System.out.println("2: Codage Veginere");
            		System.out.println("----------------------------------------");
            		System.out.println("Veuillez choisir la methode de codage:");
            		choix = sc.nextInt();
            		if(choix != 1 && choix !=2){
            			System.out.println("-----------------ERROR------------------");
            			System.out.println("Veuillez entrez 1 ou 2!!");
            			System.out.println("");
            		}
            	}while(choix != 1 && choix !=2);
            	System.out.println("----------------------------------------");
            	if(choix == 1){
            		String cod_mode = "[CESAR]";
                	do{
                		
                        System.out.println(cod_mode+" Entrez le nombre de caractere a decaler:");
                        decalage = sc.nextInt();
                    }while(decalage < 0);
            	}else if(choix == 2){
            		String cod_mode = "[VIGENERE]";
            		do{
                        System.out.println(cod_mode+ " Entrez votre cle (en alphabet):");
                        decalage = sc.nextInt();
                    }while(decalage < 0);
            	}else{
            		System.out.println("Why you are here?");	
            	}
            	System.out.println("----------------------------------------");
            	System.out.println("Importer le fichier à coder:");
                try {
                	
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                //define and add the java frame for GUI
                AddPane(decalage,cle);
            }
        });
    }
	
	public void AddPane(int decalage,String cle){
		//define and add the java frame for GUI
        JFrame frame = new JFrame("Codage Cesar & Vigenere");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TestPane(decalage,cle));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	//define the class of the Test Panel
    public class TestPane extends JPanel {

        private JButton open;
        private JTextArea textArea;
        private JButton dec_btn;
        private JTextArea dec_text;
        private JFileChooser chooser;
        private int decalage;
        private String cle;

        public TestPane(int decalage,String cle) {
        	this.decalage = decalage;
        	this.cle = cle;
            setLayout(new BorderLayout());
           // dec_btn = new JButton("Valider le decalage");
            open = new JButton("Choissisez votre fichier txt");
            textArea = new JTextArea(20, 40);
            //dec_text = new JTextArea(10, 40);
            add(new JScrollPane(textArea));
            add(open, BorderLayout.SOUTH);
            
            //add(new JScrollPane(dec_text));
            //add(dec_btn, BorderLayout.SOUTH);

            open.addActionListener(new ActionListener() {
                @Override
                

                public void actionPerformed(ActionEvent e) {
                   /* int decalage = -1;
                    
                    //ask for the decalage
            do{
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Entrez le nombre de caractere a decaler: \n");
                decalage = sc.nextInt();
            }while(decalage < 0);*/
            
                    if (chooser == null) {
                        chooser = new JFileChooser();
                        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        chooser.setAcceptAllFileFilterUsed(false);
                        chooser.addChoosableFileFilter(new FileFilter() {
                            @Override
                            public boolean accept(File f) {
                                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                            }

                            @Override
                            public String getDescription() {
                                return "Text Files (*.txt)";
                            }
                        });
                    }

                    switch (chooser.showOpenDialog(TestPane.this)) {
                        case JFileChooser.APPROVE_OPTION:
                            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                                textArea.setText(null);
                                String text = null;
                                StringBuilder sb = new 
                                        StringBuilder("");
                                textArea.append("Votre message initial est:\n");
                                textArea.append("\n");
                                while ((text = br.readLine()) != null) {
                                    textArea.append(text);
                                   // textArea.append(decalage);
                                    sb.append(text);
                                    textArea.append("\n");
                                    sb.append("\n");
                                }
                                
                                StringToAscii toByte = new StringToAscii(sb);
                      		  
	                            ArrayList<Integer> list = new ArrayList<Integer>();
	                            toByte.getDecalage(decalage);
	                            //System.out.println("ASCII value of " + text + " is following"); 
	                    		//System.out.println(toByte.code()); 
	                    		list = toByte.code();
	                    		textArea.append("\n");
	                    		textArea.append("----------------------------------------\n");
	                    		textArea.append("\n");
	                    		textArea.append("Avec un decalage de " + decalage + ", \n");
	                    		textArea.append("votre message apres etre code est:\n");
	                    		textArea.append("\n");
	                    		textArea.append(toByte.AsciiToString(list));
	                    		System.out.println("----------------------------------------"); 
	                    		System.out.println("Votre message a été codé!");
	                    		System.out.println("----------------------------------------"); 
	                    		//System.out.println("Votre message apres etre code:"); 
	                    		//System.out.println(toByte.AsciiToString(list)); 
	                    		
	                    		textArea.setCaretPosition(0);
	                    		
	                    		//export the final file coded
	                    		try{
	                    				//File r = new File("C:\\Users\\d.cui.13\\Desktop\\FileCoded.txt");
	                    				String path = "C:\\Users\\I336796\\Desktop\\FileCoded.txt";
	                    				File r = new File(path);
	                    				FileWriter pw = new FileWriter(r);
	                    				pw.write(toByte.AsciiToString(list));
	                    				pw.flush();
	                    		        pw.close();
	                    				textArea.append("\n");
	                    				textArea.append("----------------------------------------\n");
	                    				textArea.append("\n");
	                    				textArea.append("Fichier avec ce message est genere!\n");
	                    				System.out.println("Fichier généré avec succes à:"+ path);
	                    				}catch(IOException e2){
	                    					System.err.println(e2);
	                    				}
	                    		
                            } catch (IOException exp) {
                                exp.printStackTrace();
                                JOptionPane.showMessageDialog(TestPane.this, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                    }
                }
            });
        }

    }

	
	
	
}