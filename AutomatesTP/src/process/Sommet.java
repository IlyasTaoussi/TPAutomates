package process;
import java.util.ArrayList;

public class Sommet {
	private String nomStation;
	private ArrayList<Arc> trajets;
	
	public Sommet() {
		super();
	}
	
	public Sommet(String nomStation) {
		super();
		this.nomStation = nomStation;
	}

	public String getNomStation() {
		return nomStation;
	}

	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}

	public ArrayList<Arc> getTrajets() {
		return trajets;
	}

	public void setTrajets(ArrayList<Arc> trajets) {
		this.trajets = trajets;
	}
	
	public void addTrajet(Arc trajet) {
		if(trajet.getStationDepart().getNomStation().equals(this.nomStation))
			trajets.add(trajet);
		else
			System.err.println("Station de Depart non valide !!!!!");
	}
	
	
}
