package process;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Lecture {
	private String path;
	private DefaultHandler handlerXML;
	//txt reader
	
	public Lecture(String path) {
		super();
		this.path = path;
		handlerXML = new DefaultHandler() {
	 
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
	           	} 
	        }
	 
	        };
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
	}
