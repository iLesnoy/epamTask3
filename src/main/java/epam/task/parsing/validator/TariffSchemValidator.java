package epam.task.parsing.validator;


import epam.task.parsing.handler.TariffErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class TariffSchemValidator{

    public static final Logger logger = LogManager.getLogger();
    private static final String schemaName = "resources/schema.xsd";

    public TariffSchemValidator(){
    }


    public boolean xmlFileCheck(String fileName){
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);

        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
        } catch (IOException e ) {
            logger.error("File "+fileName + "doesn't exist");
            return false;
        } catch (SAXException e) {
            logger.warn("Validation failed ",e);
        }
        return true;
    }
}

