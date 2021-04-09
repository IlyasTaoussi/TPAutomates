package process;



import java.io.File;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;



public class LectureXML {
	private String path;
	private DefaultHandler handlerXML;
	private File schemaFile ;
	static StringBuffer buffer;
	static Sommet sommetDepart;
	static Sommet sommetArrivee;
	static Arc arc;
	static Heure heureDepart;
	static Heure heureArrivee;
	static Horaire horaire;
	static int line ;
	static List<Arc> listArcTram;
	static String[] ListStation;
	public LectureXML(String path, Transport exploitant) {
		super();
		try {
			
			this.path = path;
			if(path.equals("src/resource/train.xml")) {
				schemaFile = new File("src/resource/train.xsd");
			}
			else if(path.equals("src/resource/tram.xml")){
				schemaFile = new File("src/resource/tram.xsd");
			}
			SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(path)));
			System.out.println("Fichier " + path.replace("src/resource/", "") + " valide");
			this.setDefaultHandler(exploitant);
			
	        SAXParserFactory spfactory = SAXParserFactory.newInstance();
	        SAXParser saxParser = spfactory.newSAXParser();
	        
	        saxParser.parse(path, handlerXML);
	 
	     } catch (Exception e) {
	    	 System.err.println("Probleme de Lecture ou Parsing !! Voir le StackTrace pour la source du probléme :");
	    	 e.printStackTrace();
	    	 System.exit(0);
	     }
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
				
		        
		        @SuppressWarnings("unused")
				boolean junction = false;
		        @SuppressWarnings("unused")
		        boolean startStation = false;
		        @SuppressWarnings("unused")
		        boolean arrivalStation = false;
		        @SuppressWarnings("unused")
		        boolean startHour = false;
		        @SuppressWarnings("unused")
		        boolean arrivalHour = false;
		 
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise ouvrante '<' */
		        public void startElement(String uri, String localName,
		               String qName,Attributes attributes) throws SAXException{
		     
		 
		           if (qName.equalsIgnoreCase("junction")) {
		        	   	junction = true;
		           }
		           else {
		        	   	buffer = new StringBuffer();
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
		        }
		        /*cette méthode est invoquée à chaque fois que parser rencontre
		          une balise fermante '>' */
		        public void endElement(String uri, String localName,String qName) throws SAXException {
		        	
			           if (qName.equalsIgnoreCase("junction")) {
			        	   arc = new Arc(sommetDepart, sommetArrivee,horaire, Transport.TRAIN);
			        	   
			        	   Reseau.addSommet(sommetDepart);
			        	   Reseau.addArc(arc);
			        	   
			        	   
			        	   heureDepart = null;
			        	   heureArrivee = null;
			        	   horaire = null;
			        	   sommetArrivee = null;
			        	   sommetDepart = null;
			        	   arc = null;
			        	   junction = false;
			           }
			 
			           if (qName.equalsIgnoreCase("start-station")) {
			        	   sommetDepart = new Sommet(buffer.toString());
			        	   
			        	   buffer = null;
			        	   startStation = false;
			           }
			 
			           if (qName.equalsIgnoreCase("arrival-station")) {
			        	   sommetArrivee = new Sommet(buffer.toString());
			        	   
			        	   
			        	   buffer = null;
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
		        	String lecture = new String(ch,start,length);
		        	if(buffer != null) buffer.append(lecture); 
		        
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
		        	buffer = new StringBuffer();
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
		        	if (qName.equalsIgnoreCase("stations")) {
		        		if(line != 0) {
		        			ListStation = null;
		        			ListStation = buffer.toString().split(" ");
		        			
		        			stations = false;
		        		}
		        	}
			 
		        	if (qName.equalsIgnoreCase("ligne")) {
		        		ligne = false;
		        	}
			 
		        	if (qName.equalsIgnoreCase("heures-passage")) {
		        		String[] ListHeuresPassage = buffer.toString().split(" ");
		        		int firstTemp =0;
		        		for(int i = 0; i<ListStation.length; i++) {
		        			if(i != (ListStation.length - 1 )){
		        				sommetDepart = new Sommet(ListStation[i]);
	        					sommetArrivee = new Sommet(ListStation[(i+1)]);
		        				horaire = new Horaire(new Heure(ListHeuresPassage[i]), new Heure(ListHeuresPassage[i+1]));
	        					if(i == 0) {
	        						firstTemp = horaire.getDuree();
	        					}
		        				arc = new Arc(sommetDepart, sommetArrivee, horaire, Transport.TRAM);
		        				Reseau.addArc(arc);
		        				Reseau.addSommet(sommetDepart);
		        				Reseau.addSommet(sommetArrivee);
		        			}
		        			else {
		        				sommetDepart = new Sommet(ListStation[i]);
	        					sommetArrivee = new Sommet(ListStation[1]);
		        				horaire = new Horaire(new Heure(ListHeuresPassage[i]), firstTemp);
		        				
		        			}
		        		}
		        		heuresPassage = false;
		        	}
		        }

		        /*imprime les données stockées entre '<' et '>' */
		        public void characters(char ch[], int start, int length) throws SAXException {
		        	 
		        	
		        	if (ligne) {
		        		line = Integer.parseInt(new String(ch, start, length).replaceAll("\\s+", ""));
		        		ligne = false;
		           	}
		        	else {
		        		String lecture = new String(ch,start,length);
			        	if(buffer != null) buffer.append(lecture);
		        	}
		        	
		        }
		 
		    };
		
			
		}
	}
}
