package dmztest;

import com.dmz.service.utils.ClassLoaderUtil;
import com.dmz.service.utils.XmlDomParseUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author dmz
 * @date 2016/12/16
 */
public class DOMParseTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        InputStream xmlStream = ClassLoaderUtil.getClassLoader().getResourceAsStream("xml-test.xml");
        DocumentBuilderFactory documentBuilderF = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentB = documentBuilderF.newDocumentBuilder();
        Document document = documentB.parse(xmlStream);
        document.getDocumentElement().normalize();
        Element rootElement = document.getDocumentElement();
        System.out.println("Root Element:" + rootElement.getNodeName());
        String clazz = rootElement.getAttribute("class");
        System.out.println("class:" + clazz);
        NodeList nodeList = rootElement.getElementsByTagName("student");
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Element:" + element.getNodeName());
                System.out.println("rollno:" + element.getAttribute("rollno"));
                System.out.println("FirstName:" + element.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("lastname:" + element.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("nickname:" + element.getElementsByTagName("nickname").item(0).getTextContent());
                System.out.println("marks:" + element.getElementsByTagName("marks").item(0).getTextContent());
            }
        }
        System.out.println("=============================================================================");
        XmlDomParseUtil xmlDomParse = new XmlDomParseUtil("xml-test.xml");
        xmlDomParse.parseXml("class");
        xmlDomParse.classParseXml();

    }
}
