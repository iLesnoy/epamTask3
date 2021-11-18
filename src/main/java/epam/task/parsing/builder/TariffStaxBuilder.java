package epam.task.parsing.builder;

import epam.task.parsing.entity.*;
import epam.task.parsing.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Year;

import static epam.task.parsing.builder.TariffXmlTag.*;


public class TariffStaxBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';
    private final XMLInputFactory inputFactory;

    public TariffStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildTariffs(String filename) {
        try (FileInputStream inputStream = new FileInputStream("resources/tariffs.xml")) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();
                    if (name.equals(CONSTANT_TARIFF.getTagName()) || name.equals(TariffXmlTag.TEMPORARY_TARIFF.getTagName())) {
                        Tariff tariff = buildTariffs(reader);
                        tariffSet.add(tariff);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Reading of file failed. " + e);
        } catch (XMLStreamException e) {
            logger.error("Reading of xml data failed. " + e);
        } catch (CustomException e) {
            logger.error("XML file contains unknown tag. " + e);
        }
    }

    private Tariff buildTariffs(XMLStreamReader reader) throws XMLStreamException, CustomException {
        Tariff tariff = reader.getLocalName().equals(CONSTANT_TARIFF.getTagName()) ? new ConstantTariff() : new TemporaryTariff();
        tariff.setTariffId(reader.getAttributeValue(null, "tariff-id"));
        if (reader.getAttributeValue(null, "title") != null) {
            tariff.setTitle(reader.getAttributeValue(null, "title"));
        }
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> getStartElement(reader, tariff);
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if (name.equals(CONSTANT_TARIFF.getTagName())
                            || name.equals(TEMPORARY_TARIFF.getTagName())) {
                        return tariff;
                    }
                }
            }
        }
        return tariff;
    }

    private String getXMLText (XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private void getStartElement(XMLStreamReader reader, Tariff tariff) throws XMLStreamException, CustomException {
        String name = reader.getLocalName();
        String data = getXMLText(reader);
        TariffXmlTag currentTag = TariffXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
        switch (currentTag) {
            case NAME -> tariff.setName(data);
            case YEAR -> tariff.setYear(Year.parse(data));
            case OPERATOR_NAME -> tariff.setOperatorName(data);
            case PAYROLL -> tariff.setPayroll(data);
            case CALL_PRICES -> getPricesFromXML(reader, tariff.getCallPrices());
            case SMS_PRICE -> tariff.setSmsPrices(data);
            case PARAMETERS -> getParametersFromXML(reader, tariff.getParameters());
            case CONSTANT_TARIFF_PARAMETERS -> {
                ConstantTariff constantTariff = (ConstantTariff) tariff;
                constantTariff.setConstantTariffParameters(data);
            }
            case TEMPORARY_TARIFF_PARAMETERS -> {
                TemporaryTariff temporaryTariff = (TemporaryTariff) tariff;
                temporaryTariff.setTemporaryTariffParameters(data);
            }
            default -> {
                logger.error("Unknown tag: " + currentTag);
                throw new CustomException("Unknown tag: " + currentTag);
            }
        }
    }


     private void getParametersFromXML (XMLStreamReader reader, Parameters parameters) throws XMLStreamException{
            while (reader.hasNext()) {
                int type = reader.next();
                String name = reader.getLocalName();
                String data = getXMLText(reader);
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        TariffXmlTag currentTag = TariffXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                        switch (currentTag) {
                            case PRESENCE_OF_A_FAVORITE_NUMBER -> parameters.setFavoriteNumber(data);
                            case TARIFFICATION -> parameters.setTariffication(data);
                            case TARIFF_CONNECTION_FEE -> parameters.setTariffConnectionFee(data);
                            default -> logger.error("Unknown tag: " + currentTag);
                        }
                    }
                    case XMLStreamConstants.END_ELEMENT -> {
                        if (name.equals(TARIFF_CONNECTION_FEE.getTagName())) {
                            return;
                        }
                    }
                    default -> logger.error("Unknown element: " + type);
                }
            }
        }

     private void getPricesFromXML (XMLStreamReader reader,CallPrices callPrices) throws XMLStreamException{
        while (reader.hasNext()) {
            int type = reader.next();
            String name = reader.getLocalName();
            String data = getXMLText(reader);
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    TariffXmlTag currentTag = TariffXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (currentTag) {
                        case ON_NET_CALLS -> callPrices.setOnNetCalls(data);
                        case OUT_OF_NETWORK_CALLS-> callPrices.setOutOfNetworkCalls(data);
                        case CALLS_TO_LANDLINES -> callPrices.setCallsToLandlines(data);
                        default -> logger.error("Unknown tag: " + currentTag);
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    if (name.equals(CALLS_TO_LANDLINES.getTagName())) {
                        return;
                    }
                }
                default -> logger.error("Unknown element: " + type);
            }
        }
    }

}
