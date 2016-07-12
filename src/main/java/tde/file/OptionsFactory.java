package tde.file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class OptionsFactory {

/*
    public static void main (String[] args){
        String lol = "lol";
        createOptions(lol);
    }
*/
    /**
     * Generiert die options.xml mit dem filePath eintrag
     * @param workSpace
     * @return nüx
     */

    public static void createOptions (String workSpace) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element option = document.createElement("option");
            document.appendChild(option);
            Element filePath = document.createElement("filePath");
            filePath.appendChild(document.createTextNode(workSpace));
            option.appendChild(filePath);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("src\\main\\resources\\options.xml"));
            transformer.transform(domSource, streamResult);
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String loadPath () {
        try {
        	File file = new File("src\\main\\resources\\options.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
        	document.getDocumentElement().normalize();
        	String path = document.getElementsByTagName("filePath").item(0).getTextContent();
        	return path;
            } 
        catch (Exception e) {
            	e.printStackTrace();
        }
    }
}
