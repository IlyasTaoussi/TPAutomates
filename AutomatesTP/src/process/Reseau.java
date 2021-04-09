package process;

/*
 * @author Ilyas Taoussi , Lilian Tantot
 * @version 1.0 
 * 
 */

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
	
	//Méthode permettant d'ajouter un Sommet en testant avant si ce sommet est déjà présent dans ListSommet
	public static Sommet addSommet(Sommet sommet) {
		for(Sommet s : ListSommet) {
			if(s.equals(sommet)) {
				return s;
			}
		}
		ListSommet.add(sommet);
		return sommet;
	}
	
	//Méthode permettant d'ajouter un Arc en testant avant si cet arc est déjà présent dans ListArc
	public synchronized static Arc addArc(Arc arc) {
		for(Arc a : ListArc) {
			if(a.equals(arc)) {
				return a;
			}
		}
		ListArc.add(arc);
		return arc;
	}

	public static Sommet getSommet(String sommet) {
		for(Sommet s : ListSommet) {
			if(s.getNomStation().equals(sommet)) {
				return s;
			}
		}
		return null;
	}
	
	//Méthode testant si ListArc contient un arc donnée en argument
	public static boolean listArcsContains(Arc arc) {
		for(Arc a : ListArc) {
			if(a.equals(arc)) {
				return true;
			}
		}
		
		return false;
	}
	
	//Méthode testant si ListSommet contient un sommet donnée en argument
	public static boolean listSommetsContains(Sommet sommet) {
		for(Sommet s : ListSommet) {
			if(s.equals(sommet)) {
				return true;
			}
		}
		
		return false;
	}
	
	//Méthode servant a faire une copie d'une liste d'arcs
	public static ArrayList<Arc> cloneListArc() throws CloneNotSupportedException {
		ArrayList<Arc> copie = new ArrayList<>();
		for(Arc a : ListArc) {
			copie.add((Arc) a.clone());
		}
		return copie;
	}
	
	//Méthode servant à ajouter des arcs dans une liste de liste d'arcs
	public static ArrayList<ArrayList<Arc>> append(ArrayList<ArrayList<Arc>> chemins, Arc arc){
        if(chemins == null) {
        	return null;
        }
        if(chemins.size() == 0){
        	ArrayList<Arc> n = new ArrayList<Arc>();
        	n.add(arc);
            chemins.add(n);
        }
        else{
            for(ArrayList<Arc> c : chemins){
                c.add(arc);
            }
        }
        return chemins;
    }
}
