package tde.file;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public abstract class XMLParser {

    public static void main(String[] args) {
        ArrayList<String> lol = new ArrayList<String>();
        String project = "project";
        String name = "RandomNumberGenerator";
        int isTest = 0;
        String code =  "lol";
        String className = "RomanNumberConverter";
        codeToData(project, name, code, isTest);
        //System.out.println(lol.get(1));
        //codeToData();

    }
    /**
     * Ließt geschriebenen Code und parst zu xml in ein File, kann nun programmcode nehmen und in neuer xml file ablegen, test code werde ich morgen implementieren(dienstag)
     * @param
     * @return nüx
     */

    public static void codeToData(String project, String name, String code, int isTest) {
        String filePathes = new String();
        filePathes = getFilePath();
        if (isTest == 0) {
            try {

                filePathes = "";
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();
                //Nun da man Zugriff auf document hat, kann man mit dessen Hilfe die xml Struktur aufbauen

                Element exercise = document.createElement("exercise");
                document.appendChild(exercise);
                //Adds the node newChild to the end of the list of children of this node. docs.oracle

                Element classe = document.createElement("class");

                Attr attributeClasse = document.createAttribute("name");
                attributeClasse.setValue(name);
                classe.setAttributeNode(attributeClasse);
                classe.appendChild(document.createTextNode(code));
                exercise.appendChild(classe);


                //System.out.println(filePathes);/*

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                File path = new File(filePathes + "\\" + project /*+ "\\" + name + ".xml"*/);
                //System.out.println(path);

                if (path.exists()) {
                    StreamResult streamResult = new StreamResult(path + "\\" + name + ".xml");

                    transformer.transform(domSource, streamResult);
                }
                //Dies alles wird benötigt um ein xml File zu erstellen
                else{
                    path.mkdirs();
                    StreamResult streamResult = new StreamResult(path + "\\" + name + ".xml");

                    transformer.transform(domSource, streamResult);
                }
                //eine abfrage ob diese Programm schon existiert muss noch!!!

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        else {
            try {

                File inputFile = new File(filePathes + "\\" + project + "\\" + name + ".xml");
                DocumentBuilderFactory testDocumentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder testDocumentBuilder = testDocumentFactory.newDocumentBuilder();
                Document testDocument = testDocumentBuilder.parse(inputFile);
                testDocument.getDocumentElement().normalize();

                Element exercise = testDocument.getDocumentElement();

                Element test = testDocument.createElement("test");

                test.appendChild(testDocument.createTextNode(code));
                exercise.appendChild(test);

                //eine abfrage ob dieser test schon existiert muss noch!!! oder nicht?
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * Parst Text von xml aus einer File zu Quellcode
     * @param filePath Dateipfad
     * @return Die Zeilen als String[]
     */
    public static ArrayList<String> dataToCode(String filePath) {
        ArrayList<String> classCodeList = new ArrayList<String>();
        //Diese ArrayList wird zurückgegeben und beinhaltet alle Classen in folgender Reihenfolge: Name, KlassenCode, Tests dazu
        try {
            //with DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse( new File(filePath));
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
                   // System.out.println("Exercise Name : "+ element.getAttribute("name"));
                    classCodeList.add(0, "Exercise Name : "+ element.getAttribute("name")+"\n");


                    //System.out.println("Classes : "+ element.getElementsByTagName("classes").item(0).getTextContent());
                    classCodeList.add(1, "Classes : "+ element.getElementsByTagName("classes").item(0).getTextContent()+"\n");


                    //System.out.println("Tests : "+ element.getElementsByTagName("tests").item(0).getTextContent());
                    classCodeList.add(2, "Tests : "+ element.getElementsByTagName("tests").item(0).getTextContent()+"\n");
                    //Hier noch schauen ob noch mehr tests existieren?!
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

       // ArrayList<String> classCode = new ArrayList<String>();
        //wie kann ich readFromFile aus Loader.class ansprechen?
        //xmlCode.readFromFile(filePath);


        return classCodeList;
    }

    //Holt sich den filePath zur workspace aus der options.xml und übergibt diesen als string
    private static String  getFilePath()

    {
        String filePathes = "";
        try {
            DocumentBuilderFactory optionsFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder optionsBuilder = optionsFactory.newDocumentBuilder();
            Document optionsDocument = optionsBuilder.parse(new File("src\\main\\resources\\options.xml"));
            optionsDocument.getDocumentElement().normalize();
            NodeList nodeList = optionsDocument.getElementsByTagName("option");
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                filePathes = element.getElementsByTagName("filePath").item(0).getTextContent();
                return filePathes;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return filePathes;
    }
    //Dieser Batzen wird benötigt um sich den filePath aus der options.xml zu besorgen

}
