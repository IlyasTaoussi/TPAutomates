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
			if(a.getStationArrivee().getNomStation().equals(arrivee.getNomStation()))
					listArcs.add(a);
		}
		return listArcs;
	}

	public void updateTrajets(ArrayList<Arc> arcs) {
		for (Arc a : arcs) {
			this.arcs.add(a);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arcs == null) ? 0 : arcs.hashCode());
		result = prime * result + ((nomStation == null) ? 0 : nomStation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sommet other = (Sommet) obj;
		if (arcs == null) {
			if (other.arcs != null)
				return false;
		}
		if (nomStation == null) {
			if (other.nomStation != null)
				return false;
		} else if (!nomStation.equals(other.nomStation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sommet [nomStation=" + nomStation + ", arcs=" + arcs + "]";
	}
	
	
}
