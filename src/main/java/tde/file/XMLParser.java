package tde.file;

import java.nio.file.Path;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

public class XMLParser {

    public static void main(String[] args) {
        ArrayList<String> lol = new ArrayList<String>();
        String filePath =  "exercises.txt";
        String className = "RomanNumberConverter";
        lol = dataToCode(filePath, className);
        //System.out.println(lol.toString());

    }
    /**
     * Ließt geschriebenen Code und parst zu xml in ein File
     * @param
     * @return nüx
     */
    public void codeToData(String code, String location, String name) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            //Nun da man Zugriff auf document hat, kann man mit dessen Hilfe die xml Struktur aufbauen

            Element exercises = document.createElement("exercises");
            document.appendChild(exercises);
            //Adds the node newChild to the end of the list of children of this node. docs.oracle

                Element exercise = document.createElement("exercise");
                exercises.appendChild(exercise);

                Attr attribute = document.createAttribute("name");
                attribute.setValue("Roemische Zahlen");
                //Toemische Zahlen kann geändert werden zu einem Übergabewert
                exercise.setAttributeNode(attribute);

                    Element description = document.createElement("description");
                    description.appendChild(document.createTextNode("Konvertiert arabische in roemische Zahlen."));
                    exercise.appendChild(description);

                    Element classes = document.createElement("classes");
                    exercise.appendChild(classes);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Parst Text von xml aus einer File zu Quellcode
     * @param filePath Dateipfad
     * @return Die Zeilen als String[]
     */
    public static ArrayList<String> dataToCode(String filePath, String className) {
        ArrayList<String> classCodeList = new ArrayList<String>();
        //Diese ArrayList wird zurückgegeben und beinhaltet alle Classen in folgender Reihenfolge: Name, KlassenCode, Tests dazu
        try {
            //with DOM
            //später Options file über workspace implementieren
            //File file = new File("exercises.txt");
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
                    classCodeList.add("Exercise Name : "+ element.getAttribute("name"));


                    //System.out.println("Classes : "+ element.getElementsByTagName("classes").item(0).getTextContent());
                    classCodeList.add("Classes : "+ element.getElementsByTagName("classes").item(0).getTextContent());


                    //System.out.println("Tests : "+ element.getElementsByTagName("tests").item(0).getTextContent());
                    classCodeList.add("Tests : "+ element.getElementsByTagName("tests").item(0).getTextContent());

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
}
