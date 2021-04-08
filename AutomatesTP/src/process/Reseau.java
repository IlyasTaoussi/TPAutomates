package process;

import java.util.ArrayList;

public class Reseau {
	private static ArrayList<Sommet> ListSommet = new ArrayList<>();
	private static ArrayList<Arc> ListArc = new ArrayList<>();
	
	
	
	public static ArrayList<Sommet> getListSommet() {
		return ListSommet;
	}
	public static void setListSommet(ArrayList<Sommet> listSommet) {
		ListSommet = listSommet;
	}
	public static ArrayList<Arc> getListArc() {
		return ListArc;
	}
	public static void setListArc(ArrayList<Arc> listArc) {
		ListArc = listArc;
	}
	
	public static void addSommet(Sommet sommet) {
		int i = 0;
		for(Sommet s : ListSommet) {
			if(s.equals(sommet)) {
				s.updateTrajets(sommet.getTrajets());
				break;
			}
			i++;
		}
		if((i == (ListSommet.size())) || (i == 0)) {
			ListSommet.add(sommet);
		}
	}
	
	public static void addArc(Arc arc) {
		int i = 0;
		for(Arc a : ListArc) {
			if(a.equals(arc)) {
				a.updateHoraires(arc.getHoraires());
			}
			i++;
		}
		if((i == (ListArc.size()) ) || (i == 0)) {
			ListArc.add(arc);
		}
	}
	
	/*
	public static Sommet addSommet(Sommet sommet) {
		for(Sommet s : ListSommet) {
			if(s.equals(sommet)) {
				s.updateTrajets(sommet.getTrajets());
				return s;
			}
		}
		ListSommet.add(sommet);
		return sommet;
	}
	
	public static Arc addArc(Arc arc) {
		for(Arc a : ListArc) {
			if(a.equals(arc)) {
				a.updateHoraires(arc.getHoraires());
				return a;
			}
		}
		ListArc.add(arc);
		return arc;
		
	}
	*/
	public static boolean listArcsContains(Arc arc) {
		for(Arc a : ListArc) {
			if(a.equals(arc)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean listSommetsContains(Sommet sommet) {
		for(Sommet s : ListSommet) {
			if(s.equals(sommet)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static ArrayList<Arc> cloneListArc() throws CloneNotSupportedException {
		ArrayList<Arc> copie = new ArrayList<>();
		for(Arc a : ListArc) {
			copie.add((Arc) a.clone());
		}
		return copie;
	}
	
}
