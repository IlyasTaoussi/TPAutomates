package process;



public class MainTxt {
	// Intercité , il faut eliminer tout l'espace à part un seul pour realiser le split(" ") et parcourir le tableau
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	//	System.err.println("Lecture Metro !!!!");
	//	LectureTXT metro = new LectureTXT("src/resource/metro.txt", Transport.METRO);
	//	System.err.println("Lecture Car !!!!!");
	//	LectureTXT car = new LectureTXT("src/resource/InterCites.txt", Transport.CAR);

		//System.out.println("Liste d'arc : ");
		for (Arc a : Reseau.getListArc()) {
			System.out.println(a);
		}
		//System.out.println("Liste des sommets : ");
		for (Sommet s : Reseau.getListSommet()) {
			System.out.println(s);
		}
	  }
}
