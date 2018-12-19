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

import com.decodage.Chiffrement.TestPane;

public class dechiffrement {
	
	public static void main(String args[]) {
		new dechiffrement();
	}
	
	public dechiffrement(){
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
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
        	//CHANGE THIS LAYOUT!!
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
