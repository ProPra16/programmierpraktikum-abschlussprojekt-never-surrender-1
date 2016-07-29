package tde.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tde.file.XMLParser;
import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;
import vk.core.internal.InternalResult;



public class Test {
	
	JavaStringCompiler comp;
	InternalResult results;
	CompilationUnit[] gesamt;
	//ArrayList<CompilationUnit> ret = new ArrayList<CompilationUnit>(0);
	
	/**
	 * 
	 * Bekommt den Dateipfad vom dem Projeckt uebergeben
	 * 
	 * 
	 * 
	*/
	/*public void init(TDEDataStore dataStore) {
		String filePath = dataStore.getWorkspace() + TDEDataStore.separator + dataStore.getProjectName();

		File f = new File(filePath);
		File[] files = f.listFiles();
		ArrayList<String> list;
		ArrayList<CompilationUnit> ret = new ArrayList<>();//initialisiert eine ArrayList con CompilationUnit

		if (files != null) {
			for (File file : files) {//durchlaeuft alle datein im angegeben Pfad
                dataStore.setAktivFile(file);
                list = XMLParser.dataToCode(dataStore);

                for (int temp = 1; temp < list.size(); temp++) {
					String type = temp == 1?
							"Classe" : "Test";

					ret.add(new CompilationUnit(
									list.get(0).replace("Exercise Name :", ""),
									list.get(temp).replace(type + " : ", ""),
									temp == 1
					));
                }

            }
			if(!ret.isEmpty()) gesamt = ret.toArray(new CompilationUnit[ret.size()]);
		}
		else System.err.println(String.format("No Files in: %s!", f.getPath()));


	}*/
	
	public void init(String filePath){
		
		File f = new File(filePath);//muss mit dem richtigen Verzeichnis ersetzt werden
		File[] files = f.listFiles();
		
		int n = files.length;
		String s;
		int zaehler = 0;
		
		CompilationUnit[] classCode = new CompilationUnit[n];
		CompilationUnit[] test = new CompilationUnit[n];
		
		String[] classCodeName = new String[n];
		String[] testName = new String[n];
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Document doc = null;
		
		NodeList classCodelist;
		NodeList testlist;
		
		Node classCodeNode;
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
			
			testlist = doc.getElementsByTagName("test");//sucht nach Stichwoertern
			classCodelist = doc.getElementsByTagName("class");
			
			for(int temp = 0; temp < classCodelist.getLength(); temp++){

				classCodeNode = classCodelist.item(temp);
				
				s = null;
				el = null;
				
				if (classCodeNode.getNodeType() == Node.ELEMENT_NODE) {
					
					el = (Element) classCodeNode;
					s = el.getTextContent();//erstellt einen String der alles aus code enthaelt 
					
				}

				if(el.getAttribute("name") != null){// id soll class Name sein
					classCode[i] = new CompilationUnit(el.getAttribute("name"), s, false);//wenn die Klasse existiert wird mit ihr eine CompilationUnit erstellt
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
				
				if(el.getAttribute("name") != null){
					test[i] = new CompilationUnit(el.getAttribute("name"), s, true);//wenn der Test existiert wird mit ihr eine CompilationUnit erstellt
					zaehler++;
				}
					
			}
			
		}

		gesamt = new CompilationUnit[zaehler];// erstellt ein neues Array in der groesse der existierenden Klassen und Tests
		int i = 0;
		
		for(int temp = 0; temp < classCode.length; temp++){//befuellt gesamt mit Klassen
			if(classCode[temp] != null){
				gesamt[i] = classCode[temp];
				i++;
			}
		}
		
		for(int temp = 0; temp < test.length; temp++){//befuellt gesamt mit Tests
			if(test[temp] != null){
				gesamt[i] = test[temp];
				i++;
			}
		}
	}

	/**
	 * 
	 * 
	 * @return gibt die Anzahl der fehlgeschlagenen und ignorierten Tests zurueck 
	 */
	public int run(){
		
		results = new InternalResult();


		comp = CompilerFactory.getCompiler(gesamt);//erstellt einen neuen InternalCompiler initialisierten Array

		int n;
		
		comp.compileAndRunTests();
		n = results.getNumberOfFailedTests() + results.getNumberOfIgnoredTests();
		return n;
	}
	
	/**
	 * 
	 * @return gibt alle CompileErrors und TestFailures ueber Errors weiter
	 */
	public Errors getErrors(){
		
		Errors err = new Errors();
		
		err.setTestErrors(results.getTestFailures());

		for (CompilationUnit aGesamt : gesamt) {
			err.addCompileError(results.getCompilerErrorsForCompilationUnit(aGesamt));
		}
		
		return err;
	}
}