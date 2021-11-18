package epam.task.parsing.builder;

import epam.task.parsing.handler.TariffErrorHandler;
import epam.task.parsing.handler.TariffHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class TariffSaxBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();
    private final SAXParserFactory factory;

    public TariffSaxBuilder(){
        factory = SAXParserFactory.newInstance();
    }

    @Override
    public void buildTariffs(String filename) {
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            TariffHandler handler = new TariffHandler();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new TariffErrorHandler());
            reader.parse(filename);
            tariffSet = handler.getTariffs();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(e.getMessage());
        }

    }


}
