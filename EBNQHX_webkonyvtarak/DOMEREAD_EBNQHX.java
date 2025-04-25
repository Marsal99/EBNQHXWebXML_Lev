import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadKPRLNB {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        // Fájlból olvas
        File xmlFile = new File("XY_XML.xml");

        // DocumentBuilderFactory és DocumentBuilder inicializálása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // Parse-oljuk a dokumentumot
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Gyökérelem kiíratása
        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        // === Étterem elemek bejárása ===
        NodeList nList = doc.getElementsByTagName("etterem");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("ekod");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("varos").item(0);
                String city = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("utca").item(0);
                String street = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("hazszam").item(0);
                String number = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("csillag").item(0);
                String stars = node5.getTextContent();

                String adr = city + ", " + street + " utca " + number + ".";

                System.out.println("Étterem ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Cím: " + adr);
                System.out.println("Csillag: " + stars);
            }
        }

        // === Főszakács elemek bejárása ===
        nList = doc.getElementsByTagName("foszakacs");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("fkod");
                String eid = elem.getAttribute("e_f");

                String work = "Ez a főszakács a(z) " + eid + " azonosítójú étteremben dolgozik.";

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("eletkor").item(0);
                String age = node2.getTextContent();

                // Feltételes oktatás mező
                Node node3;
                String edu = "";
                if (elem.getElementsByTagName("vegzettseg").getLength() > 0) {
                    node3 = elem.getElementsByTagName("vegzettseg").item(0);
                    edu = node3.getTextContent();
                }

                System.out.println("Főszakács ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Életkor: " + age);
                System.out.println("Végzettség: " + edu);
                System.out.println(work);
            }
        }
    }
}
