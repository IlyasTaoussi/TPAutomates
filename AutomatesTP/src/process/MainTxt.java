package process;

import java.io.BufferedReader;
import java.io.FileReader;

public class MainTxt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader lecteurAvecBuffer = null;
	    String ligne;

	    try{
	    	lecteurAvecBuffer = new BufferedReader(new FileReader("src/resource/metro.txt"));
	    	ligne = lecteurAvecBuffer.readLine();
	    		if(ligne.startsWith("% métro")) {
	    			do{
	    				ligne = lecteurAvecBuffer.readLine();
	    				if(ligne.startsWith("%stations")) {
	    					ligne = lecteurAvecBuffer.readLine();
	    					System.out.println("Stations : " + ligne.split(" "));
	    				}
	    				else if(ligne.startsWith("%liaisons")){
	    					if((ligne = lecteurAvecBuffer.readLine()).startsWith("%depart")) {
	    						while(!(ligne = lecteurAvecBuffer.readLine()).isEmpty()) {
	    							System.out.println("ligne : " + ligne);
	    						}
	    					}
	    					else {
	    						System.err.println("Format Txt non valide !!!!!");
	    						break;
	    					}
	    				}
	    				else if(ligne.startsWith("%à partir")) {
	    					ligne = lecteurAvecBuffer.readLine();
	    					System.out.println("Heure Depart : "+ ligne);
	    				}
	    				else if(ligne.startsWith("%toutes")) {
	    					ligne = lecteurAvecBuffer.readLine();
	    					System.out.print("Toutes les ");System.err.print(ligne);System.out.println(" minutes");
	    				}
	    				else if(ligne.startsWith("%dernier")) {
	    					ligne = lecteurAvecBuffer.readLine();
	    					System.out.println("Heure Dernier Depart : " + ligne);
	    					break;
	    				}
	    				else if(ligne.isBlank()) {
	    					continue;
	    				}
	    				else {
	    					System.err.println("Format Txt non valide !!!!!");
	    					break;
	    				}
	    			}while(ligne != null);
	    		}
	    		else {
	    			System.err.println("Format Txt non valide !!!!!");
	    		}
	    	
	    	lecteurAvecBuffer.close();
	      }catch(Exception e){
	    	e.printStackTrace();
	      }
	  }
}
