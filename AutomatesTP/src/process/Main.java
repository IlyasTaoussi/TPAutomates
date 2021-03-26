package process;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	        //Obtenir la configuration du sax parser
	        SAXParserFactory spfactory = SAXParserFactory.newInstance();
	        //Obtenir une instance de l'objet parser
	        SAXParser saxParser = spfactory.newSAXParser();
	 
	        /*les trois méthodes sont déclarées dans le
	         corp du DefaltHandler*/
	        DefaultHandler handler = new DefaultHandler() {
	        //booléen train
	        boolean line = false;
	        boolean junction = false;
	        boolean startStation = false;
	        boolean arrivalStation = false;
	        boolean startHour = false;
	        boolean arrivalHour = false;
	        // booléen tram
	        boolean stations = false;
	        boolean ligne = false;
	        boolean heuresPassage = false;
	        
	 
	        /*cette méthode est invoquée à chaque fois que parser rencontre
	          une balise ouvrante '<' */
	        public void startElement(String uri, String localName,
	               String qName,Attributes attributes) throws SAXException{
	        	//train
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
	           //tram
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
	        	//train
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
		           //tram
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
	        	//train
	        	if (line) {
		             System.out.println("Line : " + 
		                    new String(ch, start, length));
		             line = false;
	        	}
		   
	        	if (junction) {
	             System.out.println("Junction : " +
	                     new String(ch, start, length));
		             junction = false;
	        	}
	        	if (startStation) {
	        		System.out.println("Start Station : " + 
	            		 new String(ch, start, length));
	        		line = false;
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
	             System.out.println(" ");
	           } 
	           //tram
	           if (stations) {
		             System.out.println("Stations : " + 
		                    new String(ch, start, length));
		             stations = false;
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
	 
	       saxParser.parse("src/resource/tram.xml", handler);
	       
	       
	 
	     } catch (Exception e) {System.err.println("Il Faut Vraiemnt trouver une solution pour le xml goddamnit");
	     }
	}

}
