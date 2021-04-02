package process;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class LectureXML {
	private String path;
	private DefaultHandler handlerXML;
	static StringBuffer buffer;
	static Sommet sommetDepart;
	static Sommet sommetArrivee;
	static Arc arc;
	static Heure heureDepart;
	static Heure heureArrivee;
	static Horaire horaire;
	
	public LectureXML(String path, Transport exploitant) {
		super();
		this.path = path;
		this.setDefaultHandler(exploitant);
		
	}
	
	public void lectureTram() {
		
		try {
	        SAXParserFactory spfactory = SAXParserFactory.newInstance();
	        SAXParser saxParser = spfactory.newSAXParser();
	        
	        saxParser.parse(path, handlerXML);
	 
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	   }
	
	
	public void setDefaultHandler(Transport exploitant) {
		
		
		if(exploitant.equals(Transport.TRAIN)) {
			handlerXML = new DefaultHandler(){
				
		        
		        boolean junction = false;
		        boolean startStation = false;
		        boolean arrivalStation = false;
		        boolean startHour = false;
		        boolean arrivalHour = false;
		 
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise ouvrante '<' */
		        public void startElement(String uri, String localName,
		               String qName,Attributes attributes) throws SAXException{
		     
		 
		           if (qName.equalsIgnoreCase("junction")) {
		        	   	arc = new Arc();
		        	   	junction = true;
		           }
		           else {
		        	   	buffer = new StringBuffer();
		        	   	if (qName.equalsIgnoreCase("start-station")) {
		        	   		sommetDepart = new Sommet();
		        	   		startStation = true;
		           		}
		 
		           		if (qName.equalsIgnoreCase("arrival-station")) {
		        	   		sommetArrivee = new Sommet();
		        	   		arrivalStation = true;
		           		}
		           
		           		if (qName.equalsIgnoreCase("start-hour")) {
		        	   		startHour = true;
		           		}
			 
		           		if (qName.equalsIgnoreCase("arrival-hour")) {
	        	   			arrivalHour = true;
		           		}
		           }
		        }
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise fermante '>' */
		        public void endElement(String uri, String localName,String qName) throws SAXException {
		        	
			           if (qName.equalsIgnoreCase("junction")) {
			        	   arc.setStationDepart(sommetArrivee);
			        	   Reseau.getListArc().add(arc);
			        	   arc = null;
			        	   junction = false;
			           }
			 
			           if (qName.equalsIgnoreCase("start-station")) {
			        	   sommetDepart.setNomStation(buffer.toString());
			        	   Reseau.getListSommet().add(sommetDepart);
			        	   buffer = null;
			        	   sommetDepart = null;
			        	   startStation = false;
			           }
			 
			           if (qName.equalsIgnoreCase("arrival-station")) {
			        	   sommetArrivee.setNomStation(buffer.toString());
			        	   Reseau.getListSommet().add(sommetArrivee);
			        	   buffer = null;
			        	   sommetArrivee = null;
			        	   arrivalStation = false;
			           }
			           
			           if (qName.equalsIgnoreCase("start-hour")) {
			        	   heureDepart = new Heure(buffer.toString());
			        	   startHour = false;
			           }
				 
			           if (qName.equalsIgnoreCase("arrival-hour")) {
			        	   heureArrivee = new Heure(buffer.toString());
			        	   horaire = new Horaire(heureDepart, heureArrivee, heureDepart.getDuree(heureArrivee));
			        	   arrivalHour = false;
			           }
		        }

		        /*imprime les données stockées entre '<' et '>' */
		        public void characters(char ch[], int start, int length) throws SAXException {
		        	
			   
		        	if (junction) {
		             System.out.println("Junction : " +
		                     new String(ch, start, length));
			             junction = false;
		        	}
		        	
		        	if (startStation) {
		             System.out.println("Start Station : " + 
		                    new String(ch, start, length));
		             startStation = false;
		           	}
		   
		           	if (arrivalStation) {
		             System.out.println("Arrivée : " +
		                     new String(ch, start, length));
		             arrivalStation = false;
		           	}
		 
		           	if (startHour) {
		             System.out.println("heure Depart : " + 
		                     new String(ch, start, length));
		             startHour = false;
		           	}
		 
		           	if (arrivalHour) {
		             System.out.println("heures Arrivée : " + 
		                     new String(ch, start, length));
		             arrivalHour = false;
		           	} 
		        }
		 
		    };
		}
		// si on trouve tram
		else if(exploitant.equals(Transport.TRAM)) {
			handlerXML = new DefaultHandler(){
				 
		        boolean stations = false;
		        boolean ligne = false;
		        boolean heuresPassage = false;
		        
		 
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise ouvrante '<' */
		        public void startElement(String uri, String localName,
		               String qName,Attributes attributes) throws SAXException{
		 
		           if (qName.equalsIgnoreCase("stations")) {
		             stations = true;
		           }
		 
		           if (qName.equalsIgnoreCase("ligne")) {
		             ligne = true;
		           }
		 
		           if (qName.equalsIgnoreCase("heures-passage")) {
		             heuresPassage = true;
		           }
		           
		        }
		 
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise fermante '>' */
		        public void endElement(String uri, String localName,String qName) throws SAXException {
		        	if (qName.equalsIgnoreCase("/stations")) {
			             stations = true;
			           }
			 
			           if (qName.equalsIgnoreCase("/ligne")) {
			             ligne = true;
			           }
			 
			           if (qName.equalsIgnoreCase("/heures-passage")) {
			             heuresPassage = true;
			           }
		        }

		        /*imprime les données stockées entre '<' et '>' */
		        public void characters(char ch[], int start, int length) throws SAXException {
		        	if (stations) {
			             System.out.println("Stations : " + 
			                    new String(ch, start, length));
			             stations = true;
		        	}
			   
		        	if (ligne) {
		             System.out.println("Ligne : " + 
		                    new String(ch, start, length));
		             ligne = false;
		           	}
		        	
		           	if (heuresPassage) {
		             System.out.println("Heures de passage : " + 
		                     new String(ch, start, length));
		             heuresPassage = false;
		           	}
		 
		        }
		 
		    };
		
			
		}
	}
}
