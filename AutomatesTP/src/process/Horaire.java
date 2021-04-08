package process;

public class Horaire {
	private Heure heureDepart;
	private Heure heureArrivee;
	private int duree;
	
	
	public Horaire() {
		super();
	}

	public Horaire(Heure heureDepart, Heure heureArrivee, int duree) {
		super();
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.duree = duree;
	}
	
	public Horaire(Heure heureDepart, Heure heureArrivee) {
		super();
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.duree = this.heureDepart.getDuree(this.heureArrivee);
	}
	
	public Horaire(Heure heureDepart, int duree) {
		super();
		this.heureDepart = heureDepart;
		this.duree = duree;
		this.heureArrivee = Heure.addDuree(heureDepart, duree);
		
	}
	
	public Horaire(int duree) {
		super();
		this.duree = duree;
	}
	
	public Heure getHeureDepart() {
		return heureDepart;
	}
	public void setHeureDepart(Heure heureDepart) {
		this.heureDepart = heureDepart;
	}
	public Heure getHeureArrivee() {
		return heureArrivee;
	}
	public void setHeureArrivee(Heure heureArrivee) {
		this.heureArrivee = heureArrivee;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}

	@Override
	public String toString() {
		return "Horaire [heureDepart=" + heureDepart + ", heureArrivee=" + heureArrivee + ", duree=" + duree + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duree;
		result = prime * result + ((heureArrivee == null) ? 0 : heureArrivee.hashCode());
		result = prime * result + ((heureDepart == null) ? 0 : heureDepart.hashCode());
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
		Horaire other = (Horaire) obj;
		if (duree != other.duree)
			return false;
		if (heureArrivee == null) {
			if (other.heureArrivee != null)
				return false;
		} else if (!heureArrivee.equals(other.heureArrivee))
			return false;
		if (heureDepart == null) {
			if (other.heureDepart != null)
				return false;
		} else if (!heureDepart.equals(other.heureDepart))
			return false;
		return true;
	}
	
	
}
