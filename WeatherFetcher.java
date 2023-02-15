package WeatherPackage;


import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherFetcher {

	public static void main (String[]args) throws Exception{
		
		System.out.println("Für welche Stadt soll das Wetter abgefragt werden?");
		Scanner input = new Scanner(System.in);
		String city = input.next();
		
		System.out.println("Wetter wird für folgende Stadt abgefragt: " + city);
		
		Weather w = Weather.getInstance();
		
		WeatherInfo[] weatherInfos = w.fetch(city);
		
		for (int x = 0; x < weatherInfos.length; x++) {
			WeatherInfo weatherInfo = weatherInfos[x];
			System.out.println(weatherInfo.getTimestamp() + ": " + weatherInfo.getTemperature());
		}
		
	}
	
}