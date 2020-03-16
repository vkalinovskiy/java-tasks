package com.tasks.yandexgeocoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class ResponseHandler {
    protected String responseString;

    ResponseHandler(String responseString) {
        this.responseString = responseString;
    }

    protected Document loadXMLFromString(String xml)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    protected String parseAddressFromXml(Document doc) {
        doc.getDocumentElement().normalize();
        Node node = doc.getElementsByTagName("GeocoderMetaData").item(0);
        Element eElement = (Element) node;

        return eElement.getElementsByTagName("text").item(0).getTextContent();
    }

    public String getAddress() throws IOException, SAXException, ParserConfigurationException {
        Document xmlDoc = loadXMLFromString(responseString);
        String address = parseAddressFromXml(xmlDoc);

        return  address;
    }
}
