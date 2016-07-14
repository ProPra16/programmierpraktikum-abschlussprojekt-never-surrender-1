package tde.file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
        String lol = "lel";
        //createOptions(lol);
        lol = loadPath();
        System.out.println(lol);
    }
    */
    //Das ist die main methode zum testen der anderen methoden

    /**
     * Generiert die options.xml mit dem filePath eintrag
     * @param workSpace ist der filepath eintrag
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

    /**
     * Liest den path zur workspace aus der options.xml datei heraus und gibt diesen als string wieder
     * @param
     * @return string der den pfad zur workspace enthält
     */
    public static String loadPath () {
        String path = "";

        try {
        	File file = new File("src\\main\\resources\\options.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
        	document.getDocumentElement().normalize();
            //NodeList nodeList = document.getElementsByTagName("option");
        	path = document.getElementsByTagName("filePath").item(0).getTextContent();
            } 
        catch (Exception e) {
            	e.printStackTrace();
        }

        return path;
    }
}
