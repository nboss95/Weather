package WeatherPackage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Weather {
	
	private static Weather instance;
	
	private Weather() {}
	
	public static Weather getInstance(){
		if ( instance == null ) {
			instance = new Weather();
		}
		return instance;
	}
	
	
	public WeatherInfo[] fetch(String city) throws Exception{
		
		
		String uri = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=" + city + "&mode=xml&appid=11e5415dd6b8c1328f846cb244c06526";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // FactoryPattern, erstellen einer neuen Instance des Documents
		DocumentBuilder documentBuilder = factory.newDocumentBuilder(); // neues Dokument aus factory rausholen, erlaubt uns dass wir hier eine adresse unserer xml datei übergeben. Erlaubt uns gen so ein dokument zu bauen, lies es bitte von uri ein
		
		Document document = documentBuilder.parse(uri);  // parse markieren - right click - open declaration - check type 
		NodeList times = document.getElementsByTagName("time");
		
		WeatherInfo[] weatherInfos = new WeatherInfo[times.getLength()];
		
		//times.item(1); // gib mir das so und so vielte Element aus times
				
		for ( int x = 0; x < times.getLength(); x++) {
			
			Node time = times.item(x);
			NamedNodeMap timeAttributes = time.getAttributes();
			String timestamp = timeAttributes.getNamedItem("from").getNodeValue();
		
		    
			NodeList children = time.getChildNodes();
		    	
		    	for ( int y = 0; y < children.getLength(); y++) {
		    		Node child = children.item(y); // ein child ist eine zeile von time
		    		if (child.getNodeName() == "temperature"){ // gib mir die Namen der Children
		    			String temperature = child.getAttributes().getNamedItem("value").getNodeValue();
		    			weatherInfos[x] = new WeatherInfo(timestamp, temperature);
		    	}
		}
		
		 //System.out.println(times.getLength());
		//System.out.println(document.getDocumentElement().getTagName()); // gib mir den Namen vom ersten ELement/Hauptelement. : weatherdata
		
	}
	
	return weatherInfos;
	
	
		
}


	}
	