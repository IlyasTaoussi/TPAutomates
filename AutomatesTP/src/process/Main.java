package process;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	        
			LectureXML train = new LectureXML("src/resource/train.xml", Transport.TRAIN);
			LectureXML tram = new LectureXML("src/resource/tram.xml", Transport.TRAM);
			LectureTXT car = new LectureTXT("src/resource/InterCites.txt", Transport.CAR);
			LectureTXT metro = new LectureTXT("src/resource/metro.txt", Transport.METRO);
			
			System.out.println("Liste d'arc : ");
			for (Arc a : Reseau.getListArc()) {
				System.out.println(a);
			}
			System.out.println("Liste des sommets : ");
			for (Sommet s : Reseau.getListSommet()) {
				System.out.println(s);
			}
	 	
	     } catch (Exception e) {System.err.println("Il Faut Vraiemnt trouver une solution pour le xml goddamnit");
	     }
	}

}
