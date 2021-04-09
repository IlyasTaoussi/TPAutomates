package process;


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
	
			for(Arc s : Reseau.getListArc()) {
				System.out.println(s);
			}
			for(Sommet a : Reseau.getListSommet()) {
				System.out.println(a);
			}
			} catch (Exception e) {
	    	 e.printStackTrace();
	     }
	}
}

