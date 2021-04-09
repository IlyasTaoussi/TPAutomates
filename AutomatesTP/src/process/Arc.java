package process;

/*
 * @author Ilyas Taoussi , Lilian Tantot
 * @version 1.0 
 * 
 */

public class Arc implements Cloneable{
	
	private Sommet stationDepart; //Station Depart
	private Sommet stationArrivee; // Station Arrivee
	private Horaire horaire; // Horaire du trajet (arc)
	private Transport transport; // Exploitant du trajet
	
	/*
	 * Constructeurs (Adaptés selon l'utilisation dans le traitement
	 */
	public Arc() {
		super();
	}
	
	public Arc(Sommet stationDepart, Sommet stationArrivee, Transport transport) {
		super();
		this.stationDepart = stationDepart;
		this.stationArrivee = stationArrivee;
		this.transport = transport;
	}
	
	public Arc(Sommet stationDepart, Sommet stationArrivee, Horaire horaire, Transport transport) {
		super();
		this.stationDepart = stationDepart;
		this.stationArrivee = stationArrivee;
		this.horaire = horaire;
		this.transport = transport;
	}
	
	public Arc(Sommet stationDepart, Sommet stationArrivee) {
		super();
		this.stationDepart = stationDepart;
		this.stationArrivee = stationArrivee;
		this.horaire = new Horaire(new Heure(), new Heure());
	}
	
	/*
	 * Getters & Setters
	 */
	
	public Sommet getStationDepart() {
		return stationDepart;
	}

	public void setStationDepart(Sommet stationDepart) {
		this.stationDepart = stationDepart;
	}

	public Sommet getStationArrivee() {
		return stationArrivee;
	}

	public void setStationArrivee(Sommet stationArrivee) {
		this.stationArrivee = stationArrivee;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setHoraires(Horaire horaire) {
		this.horaire = horaire;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horaire == null) ? 0 : horaire.hashCode());
		result = prime * result + ((stationArrivee == null) ? 0 : stationArrivee.hashCode());
		result = prime * result + ((stationDepart == null) ? 0 : stationDepart.hashCode());
		result = prime * result + ((transport == null) ? 0 : transport.hashCode());
		return result;
	}
	
	/*
	 * Methode Override Equals à specifier 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arc other = (Arc) obj;
		if (horaire == null) {
			if (other.horaire != null)
				return false;
		} else if (!horaire.equals(other.horaire))
			return false;
		if (stationArrivee == null) {
			if (other.stationArrivee != null)
				return false;
		} else if (!stationArrivee.equals(other.stationArrivee))
			return false;
		if (stationDepart == null) {
			if (other.stationDepart != null)
				return false;
		} else if (!stationDepart.equals(other.stationDepart))
			return false;
		if (transport != other.transport)
			return false;
		return true;
	}
	
	/*
	 * Clonage
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		return "Arc [stationDepart=" + stationDepart.getNomStation() + ", stationArrivee=" + stationArrivee.getNomStation() + ", horaire=" + horaire
				+ ", transport=" + transport + "]";
	}
}
