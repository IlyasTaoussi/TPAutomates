package process;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class LectureXML {
	private String path;
	private DefaultHandler handlerXML;
	//txt reader
	
	public LectureXML(String path, String exploitant) {
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
	
	public void setDefaultHandler(String exploitant) {
		if(exploitant.equalsIgnoreCase("train")) {
			handlerXML = new DefaultHandler(){
				 
		        boolean line = false;
		        boolean junction = false;
		        boolean startStation = false;
		        boolean arrivalStation = false;
		        boolean startHour = false;
		        boolean arrivalHour = false;
		 
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise ouvrante '<' */
		        public void startElement(String uri, String localName,
		               String qName,Attributes attributes) throws SAXException{
		 
		           if (qName.equalsIgnoreCase("line")) {
		             line = true;
		           }
		 
		           if (qName.equalsIgnoreCase("junction")) {
		             junction = true;
		           }
		 
		           if (qName.equalsIgnoreCase("start-station")) {
		             startStation = true;
		           }
		 
		           if (qName.equalsIgnoreCase("arrival-station")) {
		             arrivalStation = true;
		           }
		           
		           if (qName.equalsIgnoreCase("start-hour")) {
		             startHour = true;
		           }
			 
		           if (qName.equalsIgnoreCase("arrival-hour")) {
	        	   	arrivalHour = true;
		           }
		        }
		 
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise fermante '>' */
		        public void endElement(String uri, String localName,String qName) throws SAXException {
		        	if (qName.equalsIgnoreCase("line")) {
			             line = false;
			           }
			 
			           if (qName.equalsIgnoreCase("junction")) {
			             junction = false;
			           }
			 
			           if (qName.equalsIgnoreCase("start-station")) {
			             startStation = false;
			           }
			 
			           if (qName.equalsIgnoreCase("arrival-station")) {
			             arrivalStation = false;
			           }
			           
			           if (qName.equalsIgnoreCase("start-hour")) {
			             startHour = false;
			           }
				 
			           if (qName.equalsIgnoreCase("arrival-hour")) {
		        	   	arrivalHour = false;
			           }
		        }

		        /*imprime les données stockées entre '<' et '>' */
		        public void characters(char ch[], int start, int length) throws SAXException {
		        	String numLigne="";
		        	String depart="";
		        	String arrivee="";
		        	String horaireDepart="";
		        	String horaireArrivee="";
		        	if (line) {
		        		 numLigne = new String(ch, start, length);
			             System.out.println("Line : " +  numLigne);
			             line = false;
		        	}
			   
		        	if (junction) {
		        		System.out.println("Junction : " + new String(ch, start, length));
			             junction = false;
		        	}
		        	
		        	if (startStation) {
		        		depart = new String(ch, start, length);
		        		System.out.println("Start Station : " + depart);
		        		line = false;
		           	}
		   
		           	if (arrivalStation) {
		        		arrivee = new String(ch, start, length);
		        		System.out.println("Arrivée : " + arrivee);
		        		arrivalStation = false;
		           	}
		 
		           	if (startHour) {
		        		horaireDepart = new String(ch, start, length);
		           		System.out.println("heure Depart : " + horaireDepart);
		           		startHour = false;
		           	}
		 
		           	if (arrivalHour) {
		           		horaireArrivee = new String(ch, start, length);
		           		System.out.println("heures Arrivée : " + horaireArrivee);
		           		arrivalHour = false;
		           	} 
		           	Sommet nomStation = new Sommet(depart);
		        }
		 
		    };
		}
		// si on trouve tram
		else { if(exploitant.equalsIgnoreCase("reseau")) {
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
}
