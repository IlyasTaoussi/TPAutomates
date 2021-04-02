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
		if(i == (ListSommet.size()-1)) {
			ListSommet.add(sommet);
		}
	}
}
