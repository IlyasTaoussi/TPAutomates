package process;

import java.io.BufferedReader;
import java.io.FileReader;

public class MainTxt {
	// Intercité , il faut eliminer tout l'espace à part un seul pour realiser le split(" ") et parcourir le tableau
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	BufferedReader lecteurAvecBuffer = null;
	    String ligne;
	    
	    try{
	    	lecteurAvecBuffer = new BufferedReader(new FileReader("src/resource/InterCites.txt"));
	    	ligne = lecteurAvecBuffer.readLine();
	    		if(ligne.startsWith("% Car")) {
	    			while(!(ligne = lecteurAvecBuffer.readLine()).startsWith("//")) {
	    				if(ligne.startsWith("%")) {
	    					continue;
	    				}
	    				else {
	    					String[] det = ligne.replaceAll("\\s+", " ").split(" ");
		    				System.out.println("Trajet + heure Depart :" + det[0] + " " +  det[1] + " " + det[2]);
	    				}
	    			}
	    			System.out.println(" ");
	    			while((ligne = lecteurAvecBuffer.readLine()) != null){
	    				String[] det = ligne.replaceAll("\\s+", " ").split(" ");
	    				System.out.println("Trajet + heure Depart :" + det[0] + " " +  det[1] + " " + det[2]);
	    			}
	    		}
	    		else {
	    			System.err.println("Format Txt non valide !!!!!");
	    		}
	    	
	    	lecteurAvecBuffer.close();
	      }catch(Exception e){
	    	e.printStackTrace();
	      }*/
		System.err.println("Lecture Metro !!!!");
		LectureTXT metro = new LectureTXT("src/resource/metro.txt", Transport.METRO);
	//	System.err.println("Lecture Car !!!!!");
	//	LectureTXT car = new LectureTXT("src/resource/InterCites.txt", Transport.CAR);

		//System.out.println("Liste d'arc : ");
		for (Arc a : Reseau.getListArc()) {
			System.out.println(a);
		}
		//System.out.println("Liste des sommets : ");
		for (Sommet s : Reseau.getListSommet()) {
			System.out.println(s);
		}
	  }
}
