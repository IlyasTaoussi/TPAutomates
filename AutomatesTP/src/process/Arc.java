package process;

import java.util.List;

public class Arc {
	private Sommet stationDepart;
	private Sommet stationArrivée;
	private List<Horaire> horaires;
	private Transport transport;
	
	public Arc() {
		super();
	}
	
	public Arc(Sommet stationDepart, Sommet stationArrivée, Transport transport) {
		super();
		this.stationDepart = stationDepart;
		this.stationArrivée = stationArrivée;
		this.transport = transport;
	}

	public Sommet getStationDepart() {
		return stationDepart;
	}

	public void setStationDepart(Sommet stationDepart) {
		this.stationDepart = stationDepart;
	}

	public Sommet getStationArrivée() {
		return stationArrivée;
	}

	public void setStationArrivée(Sommet stationArrivée) {
		this.stationArrivée = stationArrivée;
	}

	public List<Horaire> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<Horaire> horaires) {
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
