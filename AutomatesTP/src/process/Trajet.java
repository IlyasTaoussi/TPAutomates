package process;

import java.util.ArrayList;

public class Trajet {
	
	ArrayList<ArrayList<Arc>> trajets = new ArrayList<>();
	
	public Trajet() {
		super();
	}
	
	
	
/*	public ArrayList<ArrayList<Arc>> setAllChemins(Sommet stationDepart, Sommet stationArrivee, ArrayList<Arc> visited, String heureDepart) {
		ArrayList<ArrayList<Arc>> chemins ;
		Heure heureDep = new Heure(heureDepart);
		if(stationDepart.getNomStation().equals(stationArrivee.getNomStation())) {
			return this.trajets;
		}
		
		for(Arc a : Reseau.getListArc()) {
			if(a.getStationDepart().getNomStation().equals(stationDepart.getNomStation())) {
				
			}
		}
		
	}*/
}
