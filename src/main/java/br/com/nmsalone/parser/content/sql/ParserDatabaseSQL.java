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

    private static ParserDatabaseSQL instance;

    public static ParserDatabaseSQL getInstance() {
        if(instance == null) {
            try {
                instance = new ParserDatabaseSQL();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    private FileManager fileManager;
    private InputStream xmlFile;
    private Document document;

    private ParserDatabaseSQL() throws IOException, SAXException, ParserConfigurationException {
        fileManager = new FileManager();
        xmlFile = fileManager.loadXmlInsertFiles();

        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(xmlFile);
    }

    public String findQuery(final String tag, final String id) throws ParserConfigurationException, IOException, SAXException {
        document.getDocumentElement().normalize();

        final NodeList nodeList = document.getElementsByTagName(tag);

        for(int i=0;i<nodeList.getLength();i++) {
            final Node node = (Node) nodeList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                final NamedNodeMap nodeMap = node.getAttributes();
                for(int attrId = 0;i<nodeMap.getLength();attrId++) {
                    final Node nodeAttr = nodeMap.item(attrId);
                    if(nodeAttr.getNodeName().equals("id") && nodeAttr.getNodeValue().equals(id)) {
                        final String strContent = node.getTextContent();
                        logget.info("Get " + tag + " from file: " + strContent);
                        return strContent;
                    }
                }

            }

        }

        return "";
    }

}
