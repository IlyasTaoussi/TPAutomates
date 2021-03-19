package process;

import java.util.ArrayList;
import java.util.List;

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

	public List<Horaire> getHoraires() {
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
}
