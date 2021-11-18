package epam.task.parsing.builder;

import epam.task.parsing.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.time.Year;

import static epam.task.parsing.builder.TariffXmlTag.*;

public class TariffDomBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilderFactory factory;
    private DocumentBuilder documentBuilder;


    @Override
    public void buildTariffs(String filename) {
        try {
            factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(filename);
            document.getDocumentElement().normalize();

            NodeList tariffList = document.getElementsByTagName(CONSTANT_TARIFF.getTagName());

            for (int i = 0; i < tariffList.getLength(); i++) {
                Node node = tariffList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tariffElement = (Element)node;
                    Tariff tariff = buildTariffs(tariffElement);
                    tariffSet.add(tariff);
                }
            }

            tariffList = document.getElementsByTagName(TEMPORARY_TARIFF.getTagName());
            for (int i = 0; i < tariffList.getLength(); i++) {
                Element tariffElement = (Element) tariffList.item(i);
                Tariff tariff = buildTariffs(tariffElement);
                tariffSet.add(tariff);
            }
        } catch (SAXException exception) {
            logger.error("File parsing failed", exception);
        } catch (IOException exception) {
            logger.error("Reading of file failed", exception);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Tariff buildTariffs(Element element) {
        Tariff tariff = element.getTagName().equals(CONSTANT_TARIFF.getTagName()) ?
                new ConstantTariff() : new TemporaryTariff();
        String data = element.getAttribute("tariff-id");
        tariff.setTariffId(data);
        data = element.getAttribute("title");
        tariff.setTitle(data);
        data = getElementTextContent(element, NAME.getTagName());
        tariff.setName(data);
        data = getElementTextContent(element, YEAR.getTagName());
        tariff.setYear(Year.parse(data));
        data = getElementTextContent(element, OPERATOR_NAME.getTagName());
        tariff.setOperatorName(data);
        data = getElementTextContent(element, PAYROLL.getTagName());
        tariff.setPayroll(data);

        CallPrices prices = tariff.getCallPrices();
        data = getElementTextContent(element, ON_NET_CALLS.getTagName());
        prices.setOnNetCalls(data);
        data = getElementTextContent(element, OUT_OF_NETWORK_CALLS.getTagName());
        prices.setOutOfNetworkCalls(data);
        data = getElementTextContent(element, CALLS_TO_LANDLINES.getTagName());
        prices.setCallsToLandlines(data);
        data = getElementTextContent(element, SMS_PRICE.getTagName());
        tariff.setSmsPrices(data);

        Parameters parameters = tariff.getParameters();
        data = getElementTextContent(element, PRESENCE_OF_A_FAVORITE_NUMBER.getTagName());
        parameters.setFavoriteNumber(data);
        data = getElementTextContent(element, TARIFFICATION.getTagName());
        parameters.setTariffication(data);
        data = getElementTextContent(element, TARIFF_CONNECTION_FEE.getTagName());
        parameters.setTariffConnectionFee(data);

        if (tariff instanceof ConstantTariff constantTariff) {
            data = getElementTextContent(element, CONSTANT_TARIFF_PARAMETERS.getTagName());
            constantTariff.setConstantTariffParameters(data);
        } else {
            data = getElementTextContent(element,TEMPORARY_TARIFF_PARAMETERS.getTagName());
            TemporaryTariff temporaryTariff = (TemporaryTariff) tariff;
            temporaryTariff.setTemporaryTariffParameters(data);
        }
        return tariff;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
