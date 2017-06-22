package com.dmz.service.utils;

import org.w3c.dom.Document;
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
 * @date 2016/12/21
 */
public class XmlDomParseUtil {

    private Document document;

    private InputStream inputStream;

    public XmlDomParseUtil(String resource) throws ParserConfigurationException, IOException, SAXException {
        inputStream = ClassLoaderUtil.getClassLoader().getResourceAsStream(resource);
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuild = docFactory.newDocumentBuilder();
        document = docBuild.parse(inputStream);
        document.getDocumentElement().normalize();
    }

    public void parseXml(Node nodeParm) {

         System.out.println(nodeParm.getNodeName());
        if (nodeParm.hasChildNodes()) {
            NodeList nodeList = nodeParm.getChildNodes();
            for (int index=0;index<nodeList.getLength();index++) {
                Node node = nodeList.item(index);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.hasChildNodes()) {
                        parseXml(node);
                    }
                }
            }
        }
    }
    public void parseXml(String nodeName) {
        Node node = document.getFirstChild();
        parseXml(node);
    }

    public void classParseXml() {

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
