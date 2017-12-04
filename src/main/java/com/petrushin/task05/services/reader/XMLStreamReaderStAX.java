package com.petrushin.task05.services.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XMLStreamReaderStAX {


    private static final Logger LOGGER = LogManager.getLogger(XMLStreamReaderStAX.class);

    /**
     * This method creates XMLStreamReader {@link XMLStreamReader}
     * and returns it.
     *
     * @param xmlLocation - Path to xml File
     * @return - Returns XMLStreamReader or can returns {@code null};
     */

    public XMLStreamReader getXmlStreamReader(String xmlLocation) {
        try {
            File xmlFile = new File(xmlLocation);
            InputStream inputStream = new FileInputStream(xmlFile);
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            return xmlStreamReader;
        } catch (IOException | XMLStreamException e) {
            LOGGER.error("Error returning XMLStreamReader");
        }
        return null;
    }

}
