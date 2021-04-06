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
				System.out.println("test 1 avant");
				s.updateTrajets(sommet.getTrajets());
				System.out.println("test 1");
				break;
			}
			i++;
		}
		if(i == (ListSommet.size()-1)) {
			System.out.println("test 2 avant");
			ListSommet.add(sommet);
			System.out.println("test 2");
		}
	}
	
	public static void addArc(Arc arc) {
		int i = 0;
		for(Arc a : ListArc) {
			if(a.equals(arc)) {
				a.updateHoraires(arc.getHoraires());
				break;
			}
			i++;
		}
		if(i == (ListArc.size()-1)) {
			ListArc.add(arc);
		}
	}

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
	
	public static String test() {
        return "FAST AS FUCK BOIII!!!!!";
    }
}
