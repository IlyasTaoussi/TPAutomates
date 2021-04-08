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
	
}
