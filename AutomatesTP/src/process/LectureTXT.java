package process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//import sun.jvm.hotspot.debugger.windbg.AddressDataSource;




public class LectureTXT {
	private BufferedReader buffer;
	
	public boolean isInt(String chaine){
		boolean valeur = true;
		char[] tab = chaine.toCharArray();

		for(char carac : tab){
				if(!Character.isDigit(carac) && valeur){ valeur = false; }
		}

		return valeur;
	}
	
	public LectureTXT(String path, Transport exploitant) {
		try {
			//liste des stations de métro
		ArrayList<String> stationsMetro = new ArrayList<String>(); 
			stationsMetro.add("Gare");
			stationsMetro.add("Avlon");
			stationsMetro.add("Syen");
			stationsMetro.add("Ecole");
			stationsMetro.add("Parc");
			//liste des stations de car inter-cité
		ArrayList<String> stationsCar = new ArrayList<String>();
			stationsCar.add("Limo");
			stationsCar.add("Syen");
			stationsCar.add("Singha");
			stationsCar.add("Avlon");
			stationsCar.add("Neuville");
			stationsCar.add("Gare");
			
		buffer = new BufferedReader(new FileReader(path));
		
		//Lecture fichier metro.txt
		if(exploitant.equals(Transport.METRO)) {
			String ligne;
			Sommet sommetDepart;
			Sommet sommetArrivee;
			Heure heureDebut = new Heure();
			Heure heureFin = new Heure();
			int intervaleDepart = 0;
			Horaire horaire = new Horaire();
			ArrayList<String[]> DepArrDur = new ArrayList<>();	
			Arc arc = new Arc();
			
		    ligne = buffer.readLine();
		   	if(ligne.startsWith("% métro")) {
		   		do{
		 			ligne = buffer.readLine();
		    		if(ligne.startsWith("%stations")) {
		  				ligne = buffer.readLine();
		  				for (int i = 0; i < ligne.split(" ").length; i++) {
							if (stationsMetro.contains(ligne.split(" ")[i])) {
								//System.out.println("Stations "+(i+1)+ " correcte");
							}
							else {
								System.err.println("Stations "+(i+1)+ " incorrecte");
								System.exit(0);
							}
						}
		    		}
		    		
		    		else if(ligne.startsWith("%liaisons")){
		    			if((ligne = buffer.readLine()).startsWith("%depart arrivee duree")) {
	    					int i = 0;
		    				while(!(ligne = buffer.readLine()).isEmpty()) {
		    					if (stationsMetro.contains(ligne.split(" ")[0]) && stationsMetro.contains(ligne.split(" ")[1]) && isInt(ligne.split(" ")[2])) {
			    					DepArrDur.add(ligne.split(" "));
								}
		    					else {
		    						System.err.println("trajet "+(i+1)+" incorrecte");
		    						System.exit(0);
		    					}
		    					i++;
		    				}
		    			}
		    			else {
		    				System.err.println("Veuillez remplacer la ligne 6 par %depart arrivee duree ");
		    				break;
		    			}
		    		}
		    		else if(ligne.startsWith("%à partir de")) {
		    			ligne = buffer.readLine();
		    			if (isInt(ligne)) {
		    				heureDebut = new Heure(ligne);							
						}
		    			else {
		    				System.err.println("L'heure du premier départ n'est pas correcte veuillez entrer un entier sous la forme : hhmm ");
		    				System.exit(0);
		    			}
		    		}
		    		else if(ligne.startsWith("%toutes les x minutes")) {
		    			ligne = buffer.readLine();
		    			if (isInt(ligne)) {
			    			intervaleDepart = Integer.parseInt(ligne);
						}
		    			else {
		    				System.err.println("L'intervalle entre chaque départ n'est pas correcte veuillez entrer un entier sous la forme : mm ");
		    				System.exit(0);
		    			}
		    		}
		    		else if(ligne.startsWith("%dernier départs de Gare")) {
		    			ligne = buffer.readLine();
		    			if (isInt(ligne)) {
			    			heureFin = new Heure(ligne);
						}
		    			else {
		    				System.err.println("L'heure du dernier départ n'est pas correcte veuillez entrer un entier sous la forme : hhmm ");
		    				System.exit(0);
		    			}
		    			break;
		    		}
		    		else if(ligne.isBlank()) {
		    			continue;
		    		}
		    		else {
		    			System.err.println("Erreur de syntax veuillez vérfiier les différents commentaires du fichier metro.txt ");
		    			System.exit(0);
		    		}
		    	}while(ligne != null);
		   		for(int i=0; i<DepArrDur.size(); i++) {
		   			sommetDepart = new Sommet(DepArrDur.get(i)[0]);
		   			sommetArrivee = new Sommet(DepArrDur.get(i)[1]);
		   			Reseau.addSommet(sommetDepart);
		   			Reseau.addSommet(sommetArrivee);
		   			Heure heureDepart = heureDebut;
		   			Heure heureArrivee;
		   			while(!heureDepart.plusGrandQue(heureFin)) {
		   				heureArrivee = Heure.addDuree(heureDepart, Integer.parseInt(DepArrDur.get(i)[2]));
		   				horaire = new Horaire(heureDepart, heureArrivee);
		   				arc = new Arc(sommetDepart, sommetArrivee, horaire, Transport.METRO);
		   				Reseau.addArc(arc);
		   				heureDepart = Heure.addDuree(heureDepart, intervaleDepart);
		   			}
		   		}
		    }
		    else {
		    	System.err.println("Veuillez remplacer la ligne 1 par : % métro ");
		   	}		    	
		    buffer.close();
		    System.out.println("Fichier métro.txt valide");
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
	    		if(ligne.startsWith("% Car Inter-Cité")) {
	    			int j = 0;
	    			while(!(ligne = buffer.readLine()).startsWith("//")) {
	    				if(ligne.startsWith("%")) {
	    					if((ligne.startsWith("% tables horaires") && j == 0) 
	    							|| (ligne.equals("%") && j == 1) 
	    							|| (ligne.equals("% liste des liaisons :") && j == 2)
	    							|| (ligne.equals("% depart arrivee duree-trajet") && j == 3)
	    							|| (ligne.equals("%") && j == 4)
	    							|| (ligne.equals("% liste d'horaires :") && j == 5)
	    							|| (ligne.equals("% depart arrivee heure-depart") && j == 6)  
	    							|| (ligne.equals("%") && j == 7)
	    							) 
	    					{
	    						//System.out.println("fine");
	    					}
	    					else {
	    						System.out.println("Erreur dans les lignes 2 à 9 veuillez les remplacer par : ");
	    		    			System.out.println("% Car Inter-Cité");
	    		    			System.out.println("% tables horaires");
	    		    			System.out.println("%");
	    		    			System.out.println("% liste des liaisons :");
	    		    			System.out.println("% depart arrivee duree-trajet");
	    		    			System.out.println("%");
	    		    			System.out.println("% liste d'horaires :");
	    		    			System.out.println("% depart arrivee heure-depart");
	    		    			System.out.println("%");
	    						System.exit(0);
	    					}
	    					j++;
	    					//System.out.println(j);
	    				}
	    				else {
    						String[] det = ligne.replaceAll("\\s+", " ").split(" ");
	    					if (stationsCar.contains(det[0]) && stationsCar.contains(det[1]) && isInt(det[2])) {
	    						DepArrDur.add(det);
	    					}
	    					else {
	    						System.out.println("Veuillez entrer un trajet de car inter-cité et une durée sous la forme : mm");
	    						System.exit(0);
	    					}	    					
	    				}
	    			}
	    			System.out.println(" ");
	    			while((ligne = buffer.readLine()) != null){
	    				String[] det = ligne.replaceAll("\\s+", " ").split(" ");
	    				if (stationsCar.contains(det[0]) && stationsCar.contains(det[1]) && isInt(det[2])) {
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
	    					arc = new Arc(sommetDepart, sommetArrivee, horaire, Transport.CAR);
	    					Reseau.addSommet(sommetDepart);
	    					Reseau.addSommet(sommetArrivee);
	    					Reseau.addArc(arc);
	    				}
	    				else {
	    					System.out.println("Veuillez entrer un trajet de car inter-cité conforme et une horaire sous la forme : hhmm");
    						System.exit(0);
	    				}
    				}
	    		}
	    		else {
	    			System.out.println("Veuillez remplacer la ligne 1 par : % Car Inter-Cité ");
	    			System.exit(0);
	    		}
	    	buffer.close();
	    	System.out.println("Fichier InterCites.txt valide");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
