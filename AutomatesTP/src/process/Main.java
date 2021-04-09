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
		
			Sommet sommetDepart = Reseau.getSommet("Syen");
			Sommet sommetArrive = Reseau.getSommet("Gare");
			Heure heureDepart = new Heure("0800");
			ArrayList<ArrayList<Arc>> chemins = Trajet.setAllChemins(sommetDepart, sommetArrive, new ArrayList<String>(),  heureDepart);
	//		System.out.println(chemins);
	//		System.out.println(chemins.size());
	/*		for(int j = 0; j<chemins.size(); j++) {
				for(int i = chemins.get(j).size()-1; i>=0; i--) {
					System.out.println(chemins.get(j).get(i));}
				System.err.println("  ");
			}
	*/		System.out.println(Trajet.plusCourtChemin(chemins));
	  	} catch (Exception e) {
	    	 e.printStackTrace();
	     }
	}
}

