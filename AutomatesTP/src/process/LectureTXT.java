package process;

/*
 * @author Ilyas Taoussi , Lilian Tantot
 * @version 1.0 
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LectureTXT {
	private BufferedReader buffer;
	
	//Méthode retournant true si la chaîne de caractere passée en argument est un entier.
	public boolean isInt(String chaine){
		boolean valeur = true;
		char[] tab = chaine.toCharArray();

		for(char carac : tab){
				if(!Character.isDigit(carac) && valeur){ valeur = false; }
		}

		return valeur;
	}
	
	//Méthode servant à lire les ficher .txt et prenant en argument le lien du fichier ainsi qu'un moyen de tansport
	public LectureTXT(String path, Transport exploitant) {
		try {
			//liste des stations de métro
		ArrayList<String> stationsMetro = new ArrayList<String>(); 
			//liste des stations de car inter-cité
		ArrayList<String> stationsCar = new ArrayList<String>();
		buffer = new BufferedReader(new FileReader(path));	//Initialisation du BufferedReader qui servira a lire les lignes des fichiers .txt
		
		//Lecture fichier metro.txt
		if(exploitant.equals(Transport.METRO)) {
			//Initialisation des différentes données que l'on va devoir stocker
			String ligne;
			Sommet sommetDepart;
			Sommet sommetArrivee;
			Heure heureDebut = new Heure();
			Heure heureFin = new Heure();
			int intervaleDepart = 0;
			Horaire horaire = new Horaire();
			ArrayList<String[]> DepArrDur = new ArrayList<>();	
			Arc arc = new Arc();
			
			//debut de la lecture
		    ligne = buffer.readLine();
		   	if(ligne.startsWith("% métro")) { //lecture de la première ligne du fichier et on la compare avec ce que l'on devrait trouver dans un fichier conforme
		   		do{
		 			ligne = buffer.readLine();
		    		if(ligne.startsWith("%stations")) {	//lecture de la deuxième ligne du fichier et on la compare avec ce que l'on devrait trouver dans un fichier conforme
		  				ligne = buffer.readLine();	//ici on lit la liste des stations de métro
		  				for (int i = 0; i < ligne.split(" ").length; i++) {
							stationsMetro.add(ligne.split(" ")[i]);	//et on les stockent dans la liste stationsMetro 
						}
		    		}
		    		
		    		else if(ligne.startsWith("%liaisons")){ //test de conformité du fichier
		    			if((ligne = buffer.readLine()).startsWith("%depart arrivee duree")) { //test de conformité du fichier
	    					int i = 0;
		    				while(!(ligne = buffer.readLine()).isEmpty()) {
		    					if (stationsMetro.contains(ligne.split(" ")[0]) && stationsMetro.contains(ligne.split(" ")[1]) && isInt(ligne.split(" ")[2])) { //Ici on test que les différents trajet soit entre des stations qui existent dans stationsMetro et que leur durée soit bien un entier
			    					DepArrDur.add(ligne.split(" ")); //si c'est le cas on ajoute alors les données de la ligne lu dans DepArrDur qui est une liste contenant des tableaux de chaines de caracteres
								}
		    					else {
		    						System.err.println("trajet "+(i+1)+" incorrecte");	//ici on fait la gestion d'erreur et on indique quel trajet est defectueux
		    						System.exit(0); //et on stop le programme
		    					}
		    					i++;
		    				}
		    			}
		    			else {
		    				System.err.println("Veuillez remplacer la ligne 6 par %depart arrivee duree "); //test de conformité du fichier
		    				System.exit(0);
		    			}
		    		}
		    		else if(ligne.startsWith("%à partir de")) { //test de conformité du fichier
		    			ligne = buffer.readLine();
		    			if (isInt(ligne)) {
		    				heureDebut = new Heure(ligne);	//on stocke l'heure de mise en service du premier métro						
						}
		    			else {
		    				System.err.println("L'heure du premier départ n'est pas correcte veuillez entrer un entier sous la forme : hhmm "); //Gestion d'erreur
		    				System.exit(0);
		    			}
		    		}
		    		else if(ligne.startsWith("%toutes les x minutes")) { //test de conformité du fichier 
		    			ligne = buffer.readLine();
		    			if (isInt(ligne)) {
			    			intervaleDepart = Integer.parseInt(ligne); //on stocke l'intervalle de départ entre chaque métro
						}
		    			else {
		    				System.err.println("L'intervalle entre chaque départ n'est pas correcte veuillez entrer un entier sous la forme : mm "); //Gestion d'erreur
		    				System.exit(0);
		    			}
		    		}
		    		else if(ligne.startsWith("%dernier départs de Gare")) { //test de conformité du fichier
		    			ligne = buffer.readLine();
		    			if (isInt(ligne)) {
			    			heureFin = new Heure(ligne); //on stocke l'heure de dernier départ des métros
						}
		    			else {
		    				System.err.println("L'heure du dernier départ n'est pas correcte veuillez entrer un entier sous la forme : hhmm "); //Gestion d'erreur
		    				System.exit(0);
		    			}
		    			break;
		    		}
		    		else if(ligne.isBlank()) {
		    			continue;
		    		}
		    		else {
		    			System.err.println("Erreur de syntax veuillez vérfiier les différents commentaires du fichier metro.txt "); //Gestion d'erreur
		    			System.exit(0);
		    		}
		    	}
		   		while(ligne != null);
		   		for(int i=0; i<DepArrDur.size(); i++) { //dans cette boucle on stocke les stations presente dans DepArrDur
		   			sommetDepart = new Sommet(DepArrDur.get(i)[0]);   
		   			sommetArrivee = new Sommet(DepArrDur.get(i)[1]);
		   			Reseau.addSommet(sommetDepart);
		   			Reseau.addSommet(sommetArrivee);
		   			Heure heureDepart = heureDebut;
		   			Heure heureArrivee;
		   			while(!heureDepart.plusGrandQue(heureFin)) { //dans cette boucle on calcule toutes les horaires possibles pour chaque trajets possible
		   				heureArrivee = Heure.addDuree(heureDepart, Integer.parseInt(DepArrDur.get(i)[2]));
		   				horaire = new Horaire(heureDepart, heureArrivee);
		   				arc = new Arc(sommetDepart, sommetArrivee, horaire, Transport.METRO); //on créer les differents arcs a partire des horaires calculer et des sommets 
		   				Reseau.addArc(arc); //on stocke les différents arcs
		   				heureDepart = Heure.addDuree(heureDepart, intervaleDepart);
		   			}
		   		}
		    }
		    else {
		    	System.err.println("Veuillez remplacer la ligne 1 par : % métro "); //Gestion d'erreur
		    	System.exit(0);
		   	}		    	
		    buffer.close();
		    System.out.println("Fichier métro.txt valide"); //si le fichier est bien conforme on affiche fichier valide
		}	
		
		//Lecture du fichier InterCites
		else if(exploitant.equals(Transport.CAR)) {
			//Initialisation des différentes données que l'on va devoir stocker
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
	    	//debut de la lecture
	    	ligne = buffer.readLine();
	    		if(ligne.startsWith("% Car Inter-Cité")) { //test conformité du fichier
	    			int j = 0;
	    			while(!(ligne = buffer.readLine()).startsWith("//")) {
	    				if(ligne.startsWith("%")) { 													//test conformité du fichier
	    					if((ligne.startsWith("% tables horaires") && j == 0)  						//test conformité du fichier
	    							|| (ligne.equals("%") && j == 1) 									//test conformité du fichier
	    							|| (ligne.equals("% liste des liaisons :") && j == 2) 				//test conformité du fichier
	    							|| (ligne.equals("% depart arrivee duree-trajet") && j == 3) 		//test conformité du fichier
	    							|| (ligne.equals("%") && j == 4) 									//test conformité du fichier
	    							|| (ligne.equals("% liste d'horaires :") && j == 5) 				//test conformité du fichier
	    							|| (ligne.equals("% depart arrivee heure-depart") && j == 6)   		//test conformité du fichier
	    							|| (ligne.equals("%") && j == 7) 									//test conformité du fichier
	    							) 
	    					{}
	    					else {
	    						System.out.println("Erreur dans les lignes 2 à 9 veuillez les remplacer par : "); 	//gestion d'erreur
	    		    			System.out.println("% Car Inter-Cité");												//gestion d'erreur
	    		    			System.out.println("% tables horaires");											//gestion d'erreur
	    		    			System.out.println("%");															//gestion d'erreur
	    		    			System.out.println("% liste des liaisons :");										//gestion d'erreur
	    		    			System.out.println("% depart arrivee duree-trajet");								//gestion d'erreur
	    		    			System.out.println("%");															//gestion d'erreur
	    		    			System.out.println("% liste d'horaires :");											//gestion d'erreur
	    		    			System.out.println("% depart arrivee heure-depart");								//gestion d'erreur
	    		    			System.out.println("%");															//gestion d'erreur
	    						System.exit(0);
	    					}
	    					j++;
	    				}
	    				else {
    						String[] det = ligne.replaceAll("\\s+", " ").split(" ");
    						if(isInt(det[2])) {	//test si la durée donnée est bien un entier
    							DepArrDur.add(det);
    							if(!(stationsCar.contains(det[0]))){ // test si la liste stationsCar ne contient pas la stations donnée
    								stationsCar.add(det[0]); // si c'est le cas on l'ajoute à stationsCar
    							}
    							if(!(stationsCar.contains(det[1]))){ // test si la liste stationsCar ne contient pas la stations donnée
    								stationsCar.add(det[1]); // si c'est le cas on l'ajoute à stationsCar
    							}
    						}
	    					else {
	    						System.out.println("Veuillez entrer un trajet de car inter-cité et une durée sous la forme : mm"); //gestion d'erreur
	    						System.exit(0);
	    					}	    					
	    				}
	    			}
	    			System.out.println(" ");
	    			while((ligne = buffer.readLine()) != null){
	    				String[] det = ligne.replaceAll("\\s+", " ").split(" ");
	    				if (stationsCar.contains(det[0]) && stationsCar.contains(det[1]) && isInt(det[2])) { //test si le trajet donnée est constitué de stations existante dans stationsCar et si la durée donnée est bien un entier
	    					sommetDepart = new Sommet(det[0]); //on crée les sommets
	    					sommetArrivee = new Sommet(det[1]); //on crée les sommets
	    					heureDepart = new Heure(det[2]); //on stock l'heure de départ
	    					for(int i =0; i<DepArrDur.size(); i++) {
	    						if((sommetDepart.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[0]) && sommetArrivee.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[1])) || (sommetDepart.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[1]) && sommetArrivee.getNomStation().equalsIgnoreCase(DepArrDur.get(i)[0]))) {
	    							duree = Integer.parseInt(DepArrDur.get(i)[2]); //on stocke la durée du trajet
	    						}
	    					}
	    					heureArrivee = Heure.addDuree(heureDepart, duree); //on calcule l'heure de fin et on la stocke
	    					horaire = new Horaire(heureDepart, heureArrivee, duree);
	    					arc = new Arc(sommetDepart, sommetArrivee, horaire, Transport.CAR); //on crée l'arc avec les stations de départ, d'arrivée l'horaire et le moyen de transport 
	    					Reseau.addSommet(sommetDepart); //on stocke les différents sommets dans Reseau
	    					Reseau.addSommet(sommetArrivee); //on stocke les différents sommets dans Reseau
	    					Reseau.addArc(arc); //on stocke les différents arcs
	    				}
	    				else {
	    					System.out.println("Veuillez entrer un trajet de car inter-cité conforme et une horaire sous la forme : hhmm"); //gestion d'erreur
    						System.exit(0);
	    				}
    				}
	    		}
	    		else {
	    			System.out.println("Veuillez remplacer la ligne 1 par : % Car Inter-Cité "); //gestion d'erreur
	    			System.exit(0);
	    		}
	    	buffer.close();
	    	System.out.println("Fichier InterCites.txt valide"); //Si le fichier est conforme on affiche fichier valide
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
