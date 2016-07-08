package tde.file;

import java.nio.file.Path;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class fxmlParser {

    public void codeToData(String code, String location, String name) {

    }

    public String dataToCode(Path filePath) {
        try {
            //File file = new File("exercises.txt");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse( new File("exercises.txt"));
            document.getDocumentElement().normalize();

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
