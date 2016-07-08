package tde.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vk.core.api.CompilationUnit;



public class Test {
	
	/**
	 * 
	 * @return gibt ein CompilationUnit Array mit allen mit allen Tests und Classen zurück
	 */
	public static CompilationUnit[] init(){
		
		File f = new File("*/workspace");//muss mit dem richtigen Verzeichnis ersetzt werden
		File[] files = f.listFiles();
		
		int n = files.length;
		String s;
		int zähler = 0;
	
		CompilationUnit[] code = new CompilationUnit[n];
		CompilationUnit[] test = new CompilationUnit[n];
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Document doc = null;
		
		NodeList codelist;
		NodeList testlist;
		
		Node codeNode;
		Node testNode;
		
		Element el;
		
		
		for(int i = 0; i < n; i++){
			try {
				doc = dBuilder.parse(files[i]);//läde die xml-Dateien
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			doc.getDocumentElement().normalize();
			
			testlist = doc.getElementsByTagName("code");//sucht nach Stichwörtern
			codelist = doc.getElementsByTagName("test");
			
			for(int temp = 0; temp < codelist.getLength(); temp++){

				codeNode = codelist.item(temp);
				
				s = null;
				el = null;
				
				if (codeNode.getNodeType() == Node.ELEMENT_NODE) {
					
					el = (Element) codeNode;
					s = el.getTextContent();//erstellt einen String der alles aus code enthält 
					
				}

				if(el.getAttribute("id") != null){// id soll class Name sein
					code[i] = new CompilationUnit(el.getAttribute("id"), s, false);//wenn die Klasse existiert wird mit ihr eine CompilationUnit erstellt
					zähler++;
				}
			}
			
			for(int temp = 0; temp < testlist.getLength(); temp++){

				testNode = testlist.item(temp);
				
				s = null;
				el = null;
				
				if (testNode.getNodeType() == Node.ELEMENT_NODE) {

					el = (Element) testNode;
					s = el.getTextContent();//erstellt einen String der alles aus test enthält 
					
				}
				
				if(el.getAttribute("id") != null){
					test[i] = new CompilationUnit(el.getAttribute("id"), s, true);//wenn der Test existiert wird mit ihr eine CompilationUnit erstellt
					zähler++;
				}
					
			}
			
		}

		CompilationUnit[] gesamt = new CompilationUnit[zähler];// erstellt ein neues Array in der größe der existierenden Klassen und Tests
		int i = 0;
		
		for(int temp = 0; temp < code.length; temp++){//befüllt gesamt mit Klassen
			if(code[temp] != null){
				gesamt[i] = code[temp];
				i++;
			}
		}
		
		for(int temp = 0; temp < test.length; temp++){//befüllt gesamt mit Tests
			if(test[temp] != null){
				gesamt[i] = test[temp];
				i++;
			}
		}
		
		return gesamt;
	}
}