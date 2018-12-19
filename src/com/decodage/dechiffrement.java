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
	            	String cle_entered = "";
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
	            		
	            		/*
	            		 * Strange hereeeeeeeeeeeeeeeeeee!!!!! 
	            		 */
	            		do{
	            			cle_entered = sc.nextLine();
		            		System.out.println("----------------------------------------");
		            		System.out.println("[Dechiffrement] Connaissez-vous la clé pour déchiffrer? (Entrez Y pour Oui, N pour Non)");
		            		cle_entered = cle_entered.toUpperCase();
		            		System.out.println(cle_entered);
		            		
		            	}while(cle_entered != "Y" && cle_entered != "N");
		            	
		            	if(cle_entered == "Y"){
		            		cle_connue = true;
		            		
		            		switch(choix){
		            		case 1:
		            			do{
		            				System.out.println("[Dechiffrement] Entrez la cle (qui sera un entier positive)");
				            		cle_cesar = sc.nextInt();
				            		System.out.println(cle_cesar);
		            			}while(cle_cesar < 0);
		            			
		            			break;
		            		case 2: 
		            			do{
		            				cle_vigenere = sc.nextLine();
		            				System.out.println("[Dechiffrement] Entrez la cle (qui sera une string)");
		            				cle_vigenere = cle_vigenere.toUpperCase();
				            		System.out.println(cle_cesar);
		            			}while(cle_cesar < 0);
		            			break;
		            		default: break;
		            		}
		            	}else{
		            		cle_connue = false;
		            	}
	            	}else{
	            		//@TODO
	            	}
	            	
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                    ex.printStackTrace();
	                }
	                
	                //define and add the java frame for GUI
	                JFrame frame = new JFrame("Dechiffrement");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.add(new TestPane());
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	}
	
	//define the class of the Test Panel
    public class TestPane extends JPanel {

        private JButton open;
        private JTextArea textArea;
        private JFileChooser chooser;

        public TestPane() {
        	
            setLayout(new BorderLayout());
         
            open = new JButton("Choissisez votre fichier txt");
            textArea = new JTextArea(20, 40);
         
            add(new JScrollPane(textArea));
            add(open, BorderLayout.SOUTH);
            
            //add(new JScrollPane(dec_text));
            //add(dec_btn, BorderLayout.SOUTH);

            open.addActionListener(new ActionListener() {
                @Override
                

                public void actionPerformed(ActionEvent e) {
            
            System.out.println("1. Importez votre fichier a dechiffrer: \n");
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
                                StringBuilder sb = new StringBuilder("");
                                //show the initial message
                                textArea.append("Votre message initial est:\n");
                                textArea.append("\n");
                                while ((text = br.readLine()) != null) {
                                    textArea.append(text);
                                    sb.append(text);
                                    textArea.append("\n");
                                    sb.append("\n");
                                }
                                
                                StringToAscii toByte = new StringToAscii(sb);
                      		  
	                            ArrayList<Integer> list = new ArrayList<Integer>();

	                    		textArea.append("\n");
	                    		textArea.append("----------------------------------------\n");
	                    		textArea.append("\n");
	                    		toByte.count_frequence();
	                    		toByte.getFreqencyHashMap();
	                    		System.out.println("----------------------------------------"); 
	                    		
	                    		textArea.append("Le decalage du codage Cesar etait: ");
	                    		textArea.append(Integer.toString(toByte.calculateDecalage()));
	                    		textArea.append("----------------------------------------\n");
	                    		System.out.println("----------------------------------------"); 
	                    		textArea.append("\n");
	                    		
	                    		//show the message decoded
	                    		list = toByte.code();
	                    		textArea.append("Votre message apres etre dechiffre est:\n");
	                    		textArea.append("\n");
	                    		textArea.append(toByte.AsciiToString(list));
	                    		System.out.println("\n"); 
	                    		System.out.println("Votre message apres etre dechiffre:\n"); 
	                    		System.out.println(toByte.AsciiToString(list)); 
	                    		
	                    		textArea.setCaretPosition(0);
	                    		
	                    		//export the final file coded
	                    		try{
	                    				
	                    				File r = new File("C:\\Users\\I336796\\Desktop\\FileDecoded.txt");
	                    				FileWriter pw = new FileWriter(r);
	                    				pw.write(toByte.AsciiToString(list));
	                    				pw.flush();
	                    		        pw.close();
	                    				textArea.append("\n");
	                    				textArea.append("----------------------------------------\n");
	                    				textArea.append("\n");
	                    				textArea.append("Fichier avec ce message est genere!\n");
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
