package br.com.nmsalone.parser.content.sql;

import br.com.nmsalone.parser.iomanager.FileManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Parser to xml document. The file contains sql queryes and this class
 * is a utility to get content from this file
 */
public class ParserDatabaseSQL {

    private static final Logger logget = LogManager.getLogger(ParserDatabaseSQL.class);
    private static final String INSERT_GAME = "add_new_game";

    private FileManager fileManager;
    private InputStream xmlFile;


    public ParserDatabaseSQL() {
        fileManager = new FileManager();
        xmlFile = fileManager.loadXmlInsertFiles();
    }

    public String loadInsert() throws ParserConfigurationException, IOException, SAXException {
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        final Document document = documentBuilder.parse(xmlFile);

        document.getDocumentElement().normalize();

        final NodeList nodeList = document.getElementsByTagName("insert");

        for(int i=0;i<nodeList.getLength();i++) {
            final Node node = (Node) nodeList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                final NamedNodeMap nodeMap = node.getAttributes();
                for(int attrId = 0;i<nodeMap.getLength();attrId++) {
                    final Node nodeAttr = nodeMap.item(attrId);
                    if(nodeAttr.getNodeName().equals("id") && nodeAttr.getNodeValue().equals("add_new_game")) {
                        final String strContent = node.getTextContent();
                        logget.info("Get insert from file: " + strContent);
                        return strContent;
                    }
                }

            }

        }

        return "";
    }

}
