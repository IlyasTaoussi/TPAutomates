package process;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	        
			LectureXML train = new LectureXML("src/resource/train.xml", Transport.TRAIN);
			LectureXML tram = new LectureXML("src/resource/tram.xml", Transport.TRAM);
			LectureTXT car = new LectureTXT("src/resource/InterCites.txt", Transport.CAR);
			LectureTXT metro = new LectureTXT("src/resource/metro.txt", Transport.METRO);
		
			Sommet sommetDepart = Reseau.getSommet("Limo");
			Sommet sommetArrive = Reseau.getSommet("Avlon");
			Heure heureDepart = new Heure("0810");
			ArrayList<ArrayList<Arc>> chemins = Trajet.setAllChemins(sommetDepart, sommetArrive, new ArrayList<String>(),  heureDepart);
			System.out.println(chemins);
			System.out.println(chemins.size());
			for(int j = 0; j<chemins.size(); j++) {
				for(int i = chemins.get(j).size()-1; i>=0; i--) {
					System.out.println(chemins.get(j).get(i));}
				System.err.println("  ");
			}
		/*	for(Arc a : Reseau.getListArc()) {
				if(a.getStationDepart().equals(Reseau.getSommet("Syen")) && a.getStationArrivee().equals(Reseau.getSommet("Gare"))) {
					System.out.println(a);
				}
			}
		*/
		//	ArrayList<Arc> closest = Trajet.getAllClosestPaths(sommetDepart, heureDepart);
		//	System.out.println(closest);
	/*		for(Sommet s : Reseau.getListSommet()) {
				System.out.println(s);
			}
	 */ 	} catch (Exception e) {
	    	 e.printStackTrace();
	     }
	}
}

