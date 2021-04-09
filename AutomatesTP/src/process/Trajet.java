package process;

import java.util.ArrayList;

public class Trajet {
	
	ArrayList<ArrayList<Arc>> trajets = new ArrayList<>();
	
	public Trajet() {
		super();
	}
	
	public static Arc getClosestPath(Sommet stationDepart, Sommet stationArrivee, Heure heureDepart) {
		Arc plusCourt = null;
		for(Arc a : Reseau.getListArc()) {
			if(a.getStationDepart().equals(stationDepart) && a.getStationArrivee().equals(stationArrivee) && ((a.getHoraire().getHeureDepart().plusGrandQue(heureDepart)) || a.getHoraire().getHeureDepart().equals(heureDepart))) {
				plusCourt = a;
				break;
			}
		}
		if(plusCourt != null) {
		for(Arc a : Reseau.getListArc()) {
			if(a.getStationDepart().equals(plusCourt.getStationDepart()) && a.getStationArrivee().equals(plusCourt.getStationArrivee())) {
				if((!a.getHoraire().getHeureDepart().plusGrandQue(plusCourt.getHoraire().getHeureDepart())) && (a.getHoraire().getHeureDepart().plusGrandQue(heureDepart))) {
					plusCourt = a;
				}
			}
		}
		}
		return plusCourt;
	}
	
	public static ArrayList<Arc> getAllClosestPaths(Sommet stationDepart, Heure heureDepart){
		ArrayList<Sommet> reachable = new ArrayList<>();
		ArrayList<Arc> closest = new ArrayList<>();
		for(Arc a: Reseau.getListArc()) {
			if(a.getStationDepart().equals(stationDepart)) {
				if(!reachable.contains(a.getStationArrivee())) {
					reachable.add(a.getStationArrivee());
				}
			}
		}
		for(Sommet s : reachable) {
			Arc close = getClosestPath(stationDepart, s, heureDepart);
			if(close != null) closest.add(close);
		}
		return closest;
	}
	
	public static ArrayList<ArrayList<Arc>> setAllChemins(Sommet stationDepart, Sommet stationArrivee, ArrayList<String> visited, Heure heureDepart) {
		ArrayList<ArrayList<Arc>> chemins = new ArrayList<>() ;
		ArrayList<ArrayList<Arc>> chemin = new ArrayList<>();
		
		if(stationDepart.equals(stationArrivee)) {
			return chemin;
		}
		
		ArrayList<Arc> closest = getAllClosestPaths(stationDepart, heureDepart);
		for(Arc a : closest) {
		
			if(!visited.contains(stationDepart.getNomStation())) {
				visited.add(stationDepart.getNomStation());
			}
			
			if(!visited.contains(a.getStationArrivee().getNomStation())) {
				
				visited.add(a.getStationArrivee().getNomStation());
				Heure nextDepart = a.getHoraire().getHeureArrivee();
				chemin = setAllChemins(a.getStationArrivee(), stationArrivee, visited, nextDepart);
				chemin = Reseau.append(chemin, a);
				
				if(chemin != null) {
					chemin.forEach(chemins::add);
				}
			}
			
		}
		if(chemins.size() == 0) {
			return chemins;
		}
		return chemins;
	}
	
	public static ArrayList<Arc> plusCourtChemin(ArrayList<ArrayList<Arc>> chemins){
		int dureeMin = Integer.MAX_VALUE ,duree ;
		Heure limite = new Heure("2330");
		ArrayList<Arc> meilleur = new ArrayList<>();
		for(var chemin: chemins) {
			duree = 0;
			for(Arc arc : chemin) {
				if(!arc.getHoraire().getHeureArrivee().plusGrandQue(limite)) {
					duree = duree + arc.getHoraire().getDuree();
				}
				else {
					break;
				}
			}
			if(duree < dureeMin) {
				dureeMin = duree;
				meilleur = chemin;
			}
		}
		return meilleur;
	}
}
