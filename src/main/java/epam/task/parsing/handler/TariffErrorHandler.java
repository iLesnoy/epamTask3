package epam.task.parsing.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class TariffErrorHandler implements ErrorHandler {

    private static Logger logger = LogManager.getLogger();


    @Override
    public void warning(SAXParseException e) {
        logger.warn(getLineError(e)+ " : " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        logger.error(getLineError(e)+ " : " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        logger.fatal(getLineError(e)+ " : " + e.getMessage());
    }

    private String getLineError(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}