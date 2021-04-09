package process;

/*
 * @author Ilyas Taoussi , Lilian Tantot
 * @version 1.0 
 * 
 */

public class Sommet {
	private String nomStation;
	
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

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((nomStation == null) ? 0 : nomStation.hashCode());
		return result;
	}
	
	//Méthode servant à dire si 2 Sommets sont égaux
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sommet other = (Sommet) obj;
		
		if (nomStation == null) {
			if (other.nomStation != null)
				return false;
		} else if (!nomStation.equals(other.nomStation))
			return false;
		return true;
	}
	
	//Méthode d'affichage d'une Horaire
	@Override
	public String toString() {
		return "Sommet [nomStation=" + nomStation +"]";
	}
	
	
}
