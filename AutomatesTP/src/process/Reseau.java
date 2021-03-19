package process;

import java.util.ArrayList;

public class Reseau {
	private ArrayList<Sommet> ListSommet;
	private ArrayList<Arc> ListArc;
	
	public Reseau(ArrayList<Sommet> listSommet, ArrayList<Arc> listArc, ArrayList<Horaire> listHoraire) {
		super();
		ListSommet = listSommet;
		ListArc = listArc;
	}
	
	public ArrayList<Sommet> getListSommet() {
		return ListSommet;
	}
	public void setListSommet(ArrayList<Sommet> listSommet) {
		ListSommet = listSommet;
	}
	public ArrayList<Arc> getListArc() {
		return ListArc;
	}
	public void setListArc(ArrayList<Arc> listArc) {
		ListArc = listArc;
	}
	
}
