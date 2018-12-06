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
public class MainFunction{
	
	//function main
	public static void main(String args[]) {
		new MainFunction();
	}
	
	public MainFunction() {
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                //define and add the java frame for GUI
                JFrame frame = new JFrame("CESAR");
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
        private JButton dec_btn;
        private JTextArea dec_text;
        private JFileChooser chooser;

        public TestPane() {
        	//CHANGE THIS LAYOUT!!
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
                    int decalage = -1;
                    
                    //ask for the decalage
            do{
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Entrez le nombre de caractere a decaler: \n");
                decalage = sc.nextInt();
            }while(decalage < 0);
            
            System.out.println("2. Importez votre fichier a coder: \n");
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
	                    		System.out.println("Votre message apres etre code:\n"); 
	                    		System.out.println(toByte.AsciiToString(list)); 
	                    		
	                    		textArea.setCaretPosition(0);
	                    		
	                    		//export the final file coded
	                    		try{
	                    				//File r = new File("C:\\Users\\d.cui.13\\Desktop\\FileCoded.txt");
	                    				File r = new File("C:\\Users\\I336796\\Desktop\\FileCoded.txt");
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