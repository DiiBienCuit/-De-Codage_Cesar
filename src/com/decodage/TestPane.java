package com.decodage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

	//define the class of the Test Panel
    public class TestPane extends JPanel {

        private JButton open;
        private JTextArea textArea;
        private JButton dec_btn;
        private JTextArea dec_text;
        private JFileChooser chooser;
        private int decalage;
        private String cle;
        private int choix;

        public TestPane(int decalage,String cle, int choix) {
        	this.decalage = decalage;
        	this.cle = cle;
        	this.choix = choix;
        	
            setLayout(new BorderLayout());
            open = new JButton("Choissisez votre fichier txt");
            textArea = new JTextArea(20, 40);
            add(new JScrollPane(textArea));
            add(open, BorderLayout.SOUTH);

            open.addActionListener(new ActionListener() {
                @Override
                

                public void actionPerformed(ActionEvent e) {
            
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
                                    sb.append(text);
                                    textArea.append("\n");
                                    sb.append("\n");
                                }
                                
                                StringToAscii toByte = new StringToAscii(sb);
                      		  
	                            ArrayList<Integer> list = new ArrayList<Integer>();
	                            String path = "";
	                            //mode : cesar
	                            if(choix == 1){
	                            	toByte.getDecalage(decalage);
	 	                    		list = toByte.code();
	 	                    		textArea.append("\n");
	 	                    		textArea.append("----------------------------------------\n");
	 	                    		textArea.append("\n");
	 	                    		textArea.append("Avec un decalage de " + decalage + ", \n");
	 	                    		textArea.append("votre message apres etre code est:\n");
	 	                    		textArea.append("\n");
	 	                    		textArea.append(toByte.AsciiToString(list));
	 	                    		path = "C:\\Users\\I336796\\Desktop\\FileCoded_cesar.txt";
	                            }else if (choix == 2){
	                            	path = "C:\\Users\\I336796\\Desktop\\FileCoded_vigenere.txt";
	                            }else{
	                            	System.out.println("How did you get here?");
	                            }
	   
	                           
	                    		System.out.println("----------------------------------------"); 
	                    		System.out.println("Votre message a été codé!");
	                    		System.out.println("-----!---!---!---GREAT---!---!---!------"); 
	                    		
	                    		textArea.setCaretPosition(0);
	                    		
	                    		//export the final file coded
	                    		try{
	                    				File r = new File(path);
	                    				FileWriter pw = new FileWriter(r);
	                    				pw.write(toByte.AsciiToString(list));
	                    				pw.flush();
	                    		        pw.close();
	                    				textArea.append("\n");
	                    				textArea.append("----------------------------------------\n");
	                    				textArea.append("\n");
	                    				textArea.append("Fichier avec ce message est genere!\n");
	                    				System.out.println("Fichier généré avec succes à: "+ path);
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

    public void cesar(){
    	
    }
    
    public void export(){
    	
    }
}
