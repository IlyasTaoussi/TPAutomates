package process;

/*
 * @author Ilyas Taoussi , Lilian Tantot
 * @version 1.0 
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	        
			LectureXML train = new LectureXML("src/resource/train.xml", Transport.TRAIN);
			LectureXML tram = new LectureXML("src/resource/tram.xml", Transport.TRAM);
			LectureTXT car = new LectureTXT("src/resource/InterCites.txt", Transport.CAR);
			LectureTXT metro = new LectureTXT("src/resource/metro.txt", Transport.METRO);
			
			Scanner scan = new Scanner(System.in);
			int choix;
			System.out.println("Choisir Une Operation :");
			System.out.println("1 - Plus Court Chemin");
			System.out.println("2 - Exit");
			choix = scan.nextInt();
			if(choix == 1) {
				scan = new Scanner(System.in);
				System.out.println("Entrer Votre Station de Depart :");
				String depart = scan.nextLine();
				Sommet sommetDepart = Reseau.getSommet(depart);
				if(sommetDepart == null) {
					System.err.println("Station Introuvable !! ");
					System.exit(0);
				}
				System.out.println("Entrer Votre Station d'arrivée :");
				String arrivee = scan.nextLine();
				Sommet sommetArrivee = Reseau.getSommet(arrivee);
				if(sommetArrivee == null) {
					System.err.println("Station Introuvable !! ");
					System.exit(0);
				}
				System.out.println("Entrer Votre Horaire de Depart (sous la forme hhmm) :");
				String heure = scan.nextLine();
				Heure heureDepart = new Heure(heure);
				scan.close();
				ArrayList<ArrayList<Arc>> chemins = Trajet.setAllChemins(sommetDepart, sommetArrivee, new ArrayList<String>(), heureDepart);
				ArrayList<Arc> plusCourt = Trajet.plusCourtChemin(chemins);
				System.out.println("Chemin à Suivre : ");
				for(int i = plusCourt.size()-1 ; i>= 0; i--) {
					System.out.println(plusCourt.get(i));
				}
				
			}
			if(choix != 1 && choix != 2) {
				System.out.println("Choix Introuvable ");
				System.exit(0);
			}
			
			System.out.println("Bonne Journée :) ");
			scan.close();
			} catch (Exception e) {
	    	 e.printStackTrace();
	     }
	}
}

