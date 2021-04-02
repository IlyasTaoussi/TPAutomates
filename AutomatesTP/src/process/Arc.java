package process;

import java.util.ArrayList;

public class Arc {
	private Sommet stationDepart;
	private Sommet stationArrivee;
	private ArrayList<Horaire> horaires;
	private Transport transport;
	
	public Arc() {
		super();
	}
	
	public Arc(Sommet stationDepart, Sommet stationArrivee, Transport transport) {
		super();
		this.stationDepart = stationDepart;
		this.stationArrivee = stationArrivee;
		this.transport = transport;
	}

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

	public ArrayList<Horaire> getHoraires() {
		return horaires;
	}

	public void setHoraires(ArrayList<Horaire> horaires) {
		this.horaires = horaires;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public void addHoraire(Horaire horaire) {
		
	}
	
	public void updateHoraires(ArrayList<Horaire> horaires) {
		for(Horaire h : horaires) {
			this.horaires.add(h);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horaires == null) ? 0 : horaires.hashCode());
		result = prime * result + ((stationArrivee == null) ? 0 : stationArrivee.hashCode());
		result = prime * result + ((stationDepart == null) ? 0 : stationDepart.hashCode());
		result = prime * result + ((transport == null) ? 0 : transport.hashCode());
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
		Arc other = (Arc) obj;
		if (horaires == null) {
			if (other.horaires != null)
				return false;
		}
		if (stationArrivee == null) {
			if (other.stationArrivee != null)
				return false;
		} else if (!stationArrivee.getNomStation().equals(other.stationArrivee.getNomStation()))
			return false;
		if (stationDepart == null) {
			if (other.stationDepart != null)
				return false;
		} else if (!stationDepart.getNomStation().equals(other.stationDepart.getNomStation()))
			return false;
		if (transport != other.transport)
			return false;
		return true;
	}
	
	
}
