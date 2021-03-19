package process;

public class Horaire {
	private Heure heureDepart;
	private Heure heureArrivee;
	private Heure duree;
	
	public Horaire(Heure heureDepart, Heure heureArrivee, Heure duree) {
		super();
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
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
	public Heure getDuree() {
		return duree;
	}
	public void setDuree(Heure duree) {
		this.duree = duree;
	}
	
}
