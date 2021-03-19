package process;
import java.util.ArrayList;

public class Sommet {
	private String nomStation;
	private ArrayList<Arc> arcs;
	
	public Sommet() {
		super();
	}
	
	public Sommet(String nomStation) {
		super();
		this.nomStation = nomStation;
		arcs = new ArrayList<>();
	}

	public String getNomStation() {
		return nomStation;
	}

	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}

	public ArrayList<Arc> getTrajets() {
		return arcs;
	}

	public void setTrajets(ArrayList<Arc> trajets) {
		this.arcs = trajets;
	}
	
	public void addTrajet(Arc arc) {
		if(arc.getStationDepart().getNomStation().equals(this.nomStation))
			arcs.add(arc);
		else
			System.err.println("Station de Depart non valide !!!!!");
	}
	
	public ArrayList<Arc> getTrajet(Sommet arrivee) {
		ArrayList<Arc> listArcs = new ArrayList<>();
		for(Arc a : arcs) {
			if(a.getStationArrivée().getNomStation().equals(arrivee.getNomStation()))
					listArcs.add(a);
		}
		return listArcs;
	}
}
