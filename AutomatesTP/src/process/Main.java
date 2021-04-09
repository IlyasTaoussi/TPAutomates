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
			Sommet sommetArrive = Reseau.getSommet("Gare");
			Heure heureDepart = new Heure("0759");
			ArrayList<ArrayList<Arc>> chemins = Trajet.setAllChemins(sommetDepart, sommetArrive, new ArrayList<String>(),  heureDepart);
			
			System.out.println(chemins.size());
			for(int i = chemins.get(0).size()-1; i>=0; i--) {
				System.out.println(chemins.get(0).get(i));
			}
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
