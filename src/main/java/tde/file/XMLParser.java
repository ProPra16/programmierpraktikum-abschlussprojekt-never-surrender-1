package tde.file;

import java.io.File;
import java.lang.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import tde.core.TDEDataStore;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public abstract class XMLParser {

    /*public static void main(String[] args) {
        ArrayList<String> lol = new ArrayList<String>();
        String project = "project";
        String name = "RandomNumberGeneratorlies";
        int isTest = 0;
        String code =  "liel {adad \n}";
        String className = "RomanNumberConverter";
        //codeToData(project, name, code, isTest);
        lol = dataToCode(project, name);
        System.out.println(lol.get(1));
        //codeToData();
    }*/

    //Dies ist die main methode die man zum testen benutzen kann

    /**
     * Ließt geschriebenen Code und parst zu xml in ein File, kann nun programmcode nehmen und in neuer xml file ablegen, test code ist ebenfalls möglich. muss immer zuerst den Programmcode speichern sonst funktioniert es nicht!
     * @param dataStore //TODO: Bessere Docu.  ist der name des project ordners, name ist der name des elements vom project ordner als auch von der klasse an sich, code ist der klassen oder testcode, istest gibt an ob klassencode (0) oder testcode(1) abgespeichert werden soll
     */
    public static void codeToData(TDEDataStore dataStore, String code1, /*boolean isTest*/ String code2) {
        String filePathes;
        filePathes = getFilePath();
        
        try {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.newDocument();
	        
	        Element exercise = document.createElement("exercise");
	        document.appendChild(exercise);
	        
	        Element test = document.createElement("test");
	        test.appendChild(document.createTextNode(" "));
	        exercise.appendChild(test);
	        
	        Element classe = document.createElement("class");
	        classe.appendChild(document.createTextNode(" "));
	        exercise.appendChild(classe);
	        	
	    	String[] zwischen = code1.split("class ");
	    	String[] end = zwischen[1].split(" ");
	    	System.out.println(end[0]);

            classe.setAttribute("name", end[0]);

            File inputFile = new File(dataStore.getFilePathAsString());
                
	        NodeList list1 = document.getElementsByTagName("class");
	        list1.item(0).setTextContent(code1);
	
	        File path = new File(dataStore.getFilePathAsString());
	        	
	    	zwischen = code2.split("class ");
	    	end = zwischen[1].split(" ");
	    	System.out.println(end[0]);

            test.setAttribute("name", end[0]);
	
	        NodeList list2 = document.getElementsByTagName("test");
	        list2.item(0).setTextContent(code2);
	
	        if (path.exists()) {
	
	           TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(new FileOutputStream(inputFile)));
	
	        }
	        else {
	            System.out.println("Der Pfad oder die Datei existiert nicht");
	        }
        }
	    catch(Exception e){
	        e.printStackTrace();
	    }
    }
    
    /*public static void codeToData(String project, String name, String code, boolean isTest, String ctName) {  
        String filePathes = new String();
        filePathes = getFilePath();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            
                
			Element execise = document.createElement("execise");
            document.appendChild(execise);
                
            if(!isTest) {   
                Element eclass = document.createElement("class");
                eclass.setAttribute("name", ctName);
				execise.appendChild(eclass);
				
				Element ccode = document.createElement("code");
				ccode.setTextContent(code);
				eclass.appendChild(ccode);
			}
			else{
				
				Element test = document.createElement("test");
                test.setAttribute("name", ctName);
				execise.appendChild(test);
				
				Element tcode = document.createElement("code");
				tcode.setTextContent(code);
				test.appendChild(tcode);
			}
				
				
				
				
				
				
				
				
				DOMSource domSource = new DOMSource(document);
				File fileOutput = new File(filePathes + "/" + project + "/" + name + ".xml");
				
				StreamResult streamResult = new StreamResult(fileOutput);
				TransformerFactory tf = TransformerFactory.newInstance();

				Transformer serializer = tf.newTransformer();
				serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				serializer.setOutputProperty(OutputKeys.INDENT, "yes");
				serializer.transform(domSource, streamResult);
			}
			catch(Exception e){
				e.printStackTrace();
			}

    }*/
    
    /**
     * Parst Text von xml aus einer File zu Quellcode
     * @param //TODO: DOCU! project ist der name des project ordners, name ist der name der klasse
     * @return Die Zeilen als String in einer ArrayList
     */
    public static ArrayList<String> dataToCode(TDEDataStore dataStore) {
        ArrayList<String> classCodeList = new ArrayList<String>();
        //Diese ArrayList wird zurückgegeben und beinhaltet alle Classen in folgender Reihenfolge: Name, KlassenCode, Test
        String filePathes = new String();
        //System.out.println("ich bin hier");
        filePathes = getFilePath();
        //System.out.println("nicht hier");
        File path = new File(dataStore.getFilePathAsString());
        if (path.exists()) {

            try {
                //with DOM Parser
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(dataStore.getFilePathAsString()));
                document.getDocumentElement().normalize();
                NodeList nodeList = document.getElementsByTagName("exercise");
                //nun hat man eine NodeList mit der man die einzelnen Elemente von dieser jeweils über Befehle ansprechen kann
                int nodeListLength = nodeList.getLength();
                //gibt die Anzahl (n) der einzelnen Nodes in der nodeList(0,1,2,...,n-1) an
                for (int zaehler = 0; zaehler < nodeListLength; zaehler++) {
                    //Läuft die Liste über die Elemente namens exercise durch
                    Node node = nodeList.item(zaehler);
                    //Der Befehl um die einzelnen exercise(s) anzusprechen
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        //weiter aufbröseln um an die einzelnen Einträge eines Elements zu kommen und diese auch ansprechen zu können

                        classCodeList.add(0, element.getAttribute("name") + "\n");


                        classCodeList.add(1, element.getElementsByTagName("class").item(0).getTextContent() + "\n");


                        classCodeList.add(2, element.getElementsByTagName("test").item(0).getTextContent() + "\n");

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return classCodeList;
        }
        else {
            System.out.println("Der Zielordner wurde nicht gefunden!");
        }

        return classCodeList;
    }

    /**
     * Parst Text von xml aus einer File zu Quellcode für die Kataloge
     * @param name ist der name des jeweiligen katalog elements
     * @return Die Zeilen als String in einer ArrayList
     */
    public static ArrayList<String> catalogeToCode(String name) {
        ArrayList<String> classCodeList = new ArrayList<String>();
        String filePathes = new String();
        filePathes = new File("").getAbsolutePath() + "/src/main/resources/Katalog";
        File path = new File(filePathes);
        if (path.exists()) {
            try {
                //with DOM
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(path + "/" + name + ".xml"));
                document.getDocumentElement().normalize();
                classCodeList.add(0,""  + "\n");
                classCodeList.add(1, document.getElementsByTagName("class").item(0).getTextContent() + "\n");
                classCodeList.add(2, document.getElementsByTagName("test").item(0).getTextContent() + "\n");
                 } catch (Exception e) {
                e.printStackTrace();
            }
            return classCodeList;
        }
        else {
            System.out.println("Der Zielordner wurde nicht gefunden!");
        }

        return classCodeList;
    }

    /**
     * Holt sich den filePath zur workspace aus der options.xml und übergibt diesen als string
     * @param
     * @return den filepath als string
     */
    private static String  getFilePath()

    {
        String filePathes = "";
        try {
            DocumentBuilderFactory optionsFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder optionsBuilder = optionsFactory.newDocumentBuilder();
            //System.getProperty("java.class.path");
            String string = new File("").getAbsolutePath() + "/src/main/resources/options.xml";
           // string.toString().getClass().getResource("/options.xml");
            //System.out.println(string);
           // string.getClass().getResource("/options.xml");

          //  System.out.println(string+"lol");
            Document optionsDocument = optionsBuilder.parse(string);
            optionsDocument.getDocumentElement().normalize();
            NodeList nodeList = optionsDocument.getElementsByTagName("option");
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                filePathes = element.getElementsByTagName("filePath").item(0).getTextContent();
                //System.out.println(filePathes);
                return filePathes;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return filePathes;
    }

}
