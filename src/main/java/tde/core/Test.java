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
import vk.core.internal.InternalCompiler;
import vk.core.internal.InternalResult;



public class Test {
	private InternalResult results = new InternalResult();
	
	/**
	 *
	 * @param workspace Der workspace der Anwendung
	 * @return gibt ein CompilationUnit Array mit allen Tests und Klassen zurueck
	 */
	public static CompilationUnit[] init(String workspace){
		File f = new File(workspace);
		File[] files = f.listFiles();
		
		int n = files.length;
		String s;
		int zaehler = 0;
	
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
				doc = dBuilder.parse(files[i]);//lade die xml-Dateien
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			doc.getDocumentElement().normalize();
			
			testlist = doc.getElementsByTagName("code");//sucht nach Stichwoertern
			codelist = doc.getElementsByTagName("test");
			
			for(int temp = 0; temp < codelist.getLength(); temp++){

				codeNode = codelist.item(temp);
				
				s = null;
				el = null;
				
				if (codeNode.getNodeType() == Node.ELEMENT_NODE) {
					
					el = (Element) codeNode;
					s = el.getTextContent();//erstellt einen String der alles aus code enthaelt 
					
				}

				if(el.getAttribute("id") != null){// id soll class Name sein
					code[i] = new CompilationUnit(el.getAttribute("id"), s, false);//wenn die Klasse existiert wird mit ihr eine CompilationUnit erstellt
					zaehler++;
				}
			}
			
			for(int temp = 0; temp < testlist.getLength(); temp++){

				testNode = testlist.item(temp);
				
				s = null;
				el = null;
				
				if (testNode.getNodeType() == Node.ELEMENT_NODE) {

					el = (Element) testNode;
					s = el.getTextContent();//erstellt einen String der alles aus test enthaelt 
					
				}
				
				if(el.getAttribute("id") != null){
					test[i] = new CompilationUnit(el.getAttribute("id"), s, true);//wenn der Test existiert wird mit ihr eine CompilationUnit erstellt
					zaehler++;
				}
					
			}
			
		}

		CompilationUnit[] gesamt = new CompilationUnit[zaehler];// erstellt ein neues Array in der groesse der existierenden Klassen und Tests
		int i = 0;
		
		for(int temp = 0; temp < code.length; temp++){//befuellt gesamt mit Klassen
			if(code[temp] != null){
				gesamt[i] = code[temp];
				i++;
			}
		}
		
		for(int temp = 0; temp < test.length; temp++){//befuellt gesamt mit Tests
			if(test[temp] != null){
				gesamt[i] = test[temp];
				i++;
			}
		}
		
		return gesamt;
	}

	/**
	 * 
	 * @param cUnit bekommt ein CompilationUnit Array, welches es testen soll
	 * @return gibt die Anzahl der fehlgeschlagenen und ignorierten Tests zurueck 
	 */
	public int run(CompilationUnit[] cUnit){
		InternalCompiler comp = new InternalCompiler(cUnit);//erstellt einen neuen InternalCompiler mit dem gegebenen Array
		int n;
		
		comp.compileAndRunTests();
		n = results.getNumberOfFailedTests();
		return n + results.getNumberOfIgnoredTests();
	}
}