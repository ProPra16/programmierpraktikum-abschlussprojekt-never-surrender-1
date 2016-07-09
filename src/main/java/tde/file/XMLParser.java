package tde.file;

import java.nio.file.Path;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLParser {
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
    public String dataToCode(Path filePath) {
        try {
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
            //Node node = nList.item(n);
            //Der Befehl um die einzelnen exercises anzusprechen
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String xmlCode = new String();
        //wie kann ich readFromFile aus Loader.class ansprechen?
        //xmlCode.readFromFile(filePath);

        String Code = new String();
        return Code;
    }
}