package process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import sun.jvm.hotspot.debugger.windbg.AddressDataSource;

public class LectureTXT {
	private BufferedReader buffer;
	
	public LectureTXT(String path, Transport exploitant) {
		try {
		buffer = new BufferedReader(new FileReader(path));
		if(exploitant.equals(Transport.METRO)) {
			String ligne;
			Sommet sommetDepart;
			Sommet sommetArrivee;
			Heure heureDebut = new Heure();
			Heure heureFin = new Heure();
			int duree = 0;
			int intervaleDepart = 0;
			Horaire horaire = new Horaire();
			ArrayList<Horaire> listHoraire;
			Arc arc;
		    ligne = buffer.readLine();
		   	if(ligne.startsWith("% métro")) {
		   		do{
		 			ligne = buffer.readLine();
		    		if(ligne.startsWith("%stations")) {
		  				ligne = buffer.readLine();
		    			System.out.println("Stations : " + ligne.split(" "));
		    		}
		    		else if(ligne.startsWith("%liaisons")){
		    			if((ligne = buffer.readLine()).startsWith("%depart")) {
		    				while(!(ligne = buffer.readLine()).isEmpty()) {
		    					System.out.println("ligne : " + ligne);
		    					sommetDepart = new Sommet(ligne.split(" ")[0]);
		    					sommetArrivee = new Sommet(ligne.split(" ")[1]);
				    			duree = Integer.parseInt(ligne.split(" ")[2]);
		    					arc = new Arc(sommetDepart, sommetArrivee, Transport.METRO);
		    					sommetDepart.addTrajet(arc);
		    					Reseau.getListSommet().add(sommetDepart);
		    					Reseau.getListSommet().add(sommetArrivee);
		    					Reseau.addArc(arc);
		    				}
		    			}
		    			else {
		    				System.err.println("Format Txt non valide !!!!!");
		    				break;
		    			}
		    		}
		    		else if(ligne.startsWith("%à partir")) {
		    			ligne = buffer.readLine();
		    			System.out.println("Heure Depart : "+ ligne);
		    			heureDebut = new Heure(ligne);
		    		}
		    		else if(ligne.startsWith("%toutes")) {
		    			ligne = buffer.readLine();
		    			System.out.println("Toutes les "+ligne+" minutes");
		    			intervaleDepart = Integer.parseInt(ligne);
		    		}
		    		else if(ligne.startsWith("%dernier")) {
		    			ligne = buffer.readLine();
		    			System.out.println("Heure Dernier Depart : " + ligne);
		    			heureFin = new Heure(ligne);
		    			break;
		    		}
		    		
		    		
		    		else if(ligne.isBlank()) {
		    			continue;
		    		}
		    		else {
		    			System.err.println("Format Txt non valide !!!!!");
		    			break;
		    		}
		    		while (!heureDebut.plusGrandQue(heureFin)) {
						heureDebut = Heure.addDuree(heureDebut, intervaleDepart);
		    		}
		    	}while(ligne != null);
		    }
		    else {
		    	System.err.println("Format Txt non valide !!!!!");
		   	}
		    	
		    buffer.close();
		}
		else if(exploitant.equals(Transport.CAR)) {
			String ligne;
	    	buffer = new BufferedReader(new FileReader("src/resource/InterCites.txt"));
	    	ligne = buffer.readLine();
	    		if(ligne.startsWith("% Car")) {
	    			while(!(ligne = buffer.readLine()).startsWith("//")) {
	    				if(ligne.startsWith("%")) {
	    					continue;
	    				}
	    				else {
	    					String[] det = ligne.replaceAll("\\s+", " ").split(" ");
		    				System.out.println("Trajet + heure Depart :" + det[0] + " " +  det[1] + " " + det[2]);
	    				}
	    			}
	    			System.out.println(" ");
	    			while((ligne = buffer.readLine()) != null){
	    				String[] det = ligne.replaceAll("\\s+", " ").split(" ");
	    				System.out.println("Trajet + heure Depart :" + det[0] + " " +  det[1] + " " + det[2]);
	    			}
	    		}
	    		else {
	    			System.err.println("Format Txt non valide !!!!!");
	    		}
	    	
	    	buffer.close();
		}
		else {
			System.err.println("Something fucked up happened");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
