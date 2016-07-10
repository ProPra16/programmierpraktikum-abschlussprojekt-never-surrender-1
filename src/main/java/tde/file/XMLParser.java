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

public class XMLParser {

    public static void main(String[] args) {
        ArrayList<String> lol = new ArrayList<String>();
        String className = "RomanNumberConverter";
        lol = dataToCode(className, className);
    }
    /**
     * Ließt geschriebenen Code und parst zu xml in ein File
     * @param
     * @return nüx
     */
    public void codeToData(String code, String location, String name) {

    }
    /**
     * Parst Text von xml aus einer File zu Quellcode
     * @param filePath Dateipfad
     * @return Die Zeilen als String[]
     */
    public static ArrayList<String> dataToCode(String filePath, String className) {
        ArrayList<String> classCodeList = new ArrayList<String>();
        try {
            //with DOM
            //später Options file über workspace implementieren
            //File file = new File("exercises.txt");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse( new File("exercises.txt"));
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
                    System.out.println("Exercise Name : "+ element.getAttribute("name"));
                    classCodeList.add("Exercise Name : "+ element.getAttribute("name"));


                    System.out.println("Classes : "+ element.getElementsByTagName("classes").item(0).getTextContent());
                    classCodeList.add("Classes : "+ element.getElementsByTagName("classes").item(0).getTextContent());


                    System.out.println("Tests : "+ element.getElementsByTagName("tests").item(0).getTextContent());
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
