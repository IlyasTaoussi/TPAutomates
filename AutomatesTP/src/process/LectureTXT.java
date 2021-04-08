package process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//import sun.jvm.hotspot.debugger.windbg.AddressDataSource;

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
			
			Arc arc = new Arc();
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
				    			Horaire h = new Horaire(heureDebut, heureFin, duree);
				    			ArrayList<Horaire> initial = new ArrayList<>();
				    			initial.add(h);
		    					arc = new Arc(sommetDepart, sommetArrivee, initial, Transport.METRO);
		    					sommetDepart.addTrajet(arc);
		    					Reseau.addSommet(sommetDepart);
		    					Reseau.addSommet(sommetArrivee);
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
		   /* 		Heure heureDepart = heureDebut;
		    		while (!heureDebut.plusGrandQue(heureFin)) {
		    			Heure heureArrivee = Heure.addDuree(heureDepart, duree);
						horaire = new Horaire(heureDepart, heureArrivee, duree);
						listHoraire.add(horaire);
						heureDepart = Heure.addDuree(heureDepart, intervaleDepart);
		    		}
		    		arc.setHoraires(listHoraire);
			*/		
		    	}while(ligne != null);
		   		ArrayList<Arc> copie = Reseau.cloneListArc();
		   		for(Arc a : copie) {
			    	if(a.getTransport().equals(Transport.METRO)){
			    		Heure heureDepart = heureDebut;
			    		ArrayList<Horaire> liste = new ArrayList<>();
			    		int dureeT = a.getHoraires().get(0).getDuree();
			    		while (!heureDepart.plusGrandQue(heureFin)) {
			    			Heure heureArrivee = Heure.addDuree(heureDepart, dureeT);
							horaire = new Horaire(heureDepart, heureArrivee, dureeT);
							liste.add(horaire);
							System.out.println(heureDepart);
							heureDepart = Heure.addDuree(heureDepart, intervaleDepart);
			    		}
			    		
			    		System.out.println(liste);
			    		a.updateHoraires(liste);
			    		Reseau.addArc(a);
			    	}
			    }
		    }
		    else {
		    	System.err.println("Format Txt non valide !!!!!");
		   	}		    	
		    buffer.close();
		    
		    
		}		
		//Lecture du fichier InterCites
		else if(exploitant.equals(Transport.CAR)) {
			String ligne;
			Sommet sommetDepart;
			Sommet sommetArrivee;
			Heure heureDepart = new Heure();
			Heure heureArrivee = new Heure();
			Horaire horaire = new Horaire();
			Arc arc = new Arc();
			int duree = 0;
			ArrayList<String[]> DepArrDur = new ArrayList<String[]>();
	    	buffer = new BufferedReader(new FileReader("src/resource/InterCites.txt"));
	    	ligne = buffer.readLine();
	    		if(ligne.startsWith("% Car")) {
	    			while(!(ligne = buffer.readLine()).startsWith("//")) {
	    				if(ligne.startsWith("%")) {
	    					continue;
	    				}
	    				else {
	    					String[] det = ligne.replaceAll("\\s+", " ").split(" ");
	    					DepArrDur.add(det);
	    				}
	    			}
	    			System.out.println(" ");
	    			while((ligne = buffer.readLine()) != null){
	    				String[] det = ligne.replaceAll("\\s+", " ").split(" ");
	    				sommetDepart = new Sommet(det[0]);
	    				sommetArrivee = new Sommet(det[1]);
	    				heureDepart = new Heure(det[2]);
	    				for(int i =0; i<DepArrDur.size(); i++) {
	    					if((sommetDepart.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[0]) && sommetArrivee.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[1])) || (sommetDepart.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[1]) && sommetArrivee.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[0]))) {
	    						duree = Integer.parseInt(DepArrDur.get(i)[2]);
	    					}
	    				}
	    				heureArrivee = Heure.addDuree(heureDepart, duree);
	    				horaire = new Horaire(heureDepart, heureArrivee, duree);
	    				arc = new Arc(sommetDepart, sommetArrivee, Transport.CAR);
	    				arc.addHoraire(horaire);
	    				sommetDepart.addTrajet(arc);
	    				Reseau.addSommet(sommetDepart);
    					Reseau.addSommet(sommetArrivee);
						Reseau.addArc(arc);
						
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
