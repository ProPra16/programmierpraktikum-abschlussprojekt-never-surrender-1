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
import java.io.FileOutputStream;

public abstract class XMLParser {

    public static void main(String[] args) {
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

                Element test = document.createElement("test");
                test.appendChild(document.createTextNode(" "));
                exercise.appendChild(test);


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

                NodeList list = testDocument.getElementsByTagName("test");
                list.item(0).setTextContent(code);


                File path = new File(filePathes + "\\" + project /*+ "\\" + name + ".xml"*/);


                if (path.exists()) {

                    TransformerFactory.newInstance().newTransformer().transform(new DOMSource(testDocument), new StreamResult(new FileOutputStream(inputFile)));
                    //so verändert nun auch die test eingabe vom user in der xml datai
                }
                else {
                    System.out.println("Der Pfad oder die Datei existiert nicht");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * Parst Text von xml aus einer File zu Quellcode
     * @param
     * @return Die Zeilen als String[]
     */
    public static ArrayList<String> dataToCode(String project, String name) {
        ArrayList<String> classCodeList = new ArrayList<String>();
        String filePathes = new String();
        filePathes = getFilePath();
        File path = new File(filePathes + "\\" + project /*+ "\\" + name + ".xml"*/);
        if (path.exists()) {
            //  StreamResult streamResult = new StreamResult(path + "\\" + name + ".xml");
            //}

            //Diese ArrayList wird zurückgegeben und beinhaltet alle Classen in folgender Reihenfolge: Name, KlassenCode, Tests dazu
            try {
                //with DOM
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(path + "\\" + name + ".xml"));
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
                        classCodeList.add(0, "Exercise Name : " + element.getAttribute("name") + "\n");


                        //System.out.println("Classes : "+ element.getElementsByTagName("classes").item(0).getTextContent());
                        classCodeList.add(1, "Classe : " + element.getElementsByTagName("class").item(0).getTextContent() + "\n");


                        //System.out.println("Tests : "+ element.getElementsByTagName("tests").item(0).getTextContent());
                        classCodeList.add(2, "Test : " + element.getElementsByTagName("test").item(0).getTextContent() + "\n");
                        //Hier noch schauen ob noch mehr tests existieren?!
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

       // ArrayList<String> classCode = new ArrayList<String>();
        //wie kann ich readFromFile aus Loader.class ansprechen?
        //xmlCode.readFromFile(filePath);


        return classCodeList;
    }

    public static ArrayList<String> catalogeToCode(String name) {
        ArrayList<String> classCodeList = new ArrayList<String>();
        String filePathes = new String();
        filePathes = "src\\main\\resources\\Katalog";
        //Bin mir nicht sicher ob der Path funktioniert
        File path = new File(filePathes);
        if (path.exists()) {
            try {
                //with DOM
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(path + "\\" + name + ".xml"));
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
