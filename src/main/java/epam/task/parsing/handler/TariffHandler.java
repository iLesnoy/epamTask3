package epam.task.parsing.handler;

import epam.task.parsing.builder.TariffXmlTag;
import epam.task.parsing.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Year;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static epam.task.parsing.builder.TariffXmlTag.*;

public class TariffHandler extends DefaultHandler {

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    private Set<Tariff> tariffs;
    private EnumSet<TariffXmlTag> withText;
    TariffXmlTag currentTag;
    private Tariff tariff;

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public TariffHandler() {
        tariffs = new HashSet<>();
        withText = EnumSet.range(NAME,TEMPORARY_TARIFF_PARAMETERS);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String constantTariffTag = CONSTANT_TARIFF.getTagName();
        String temporaryTariffTag = TEMPORARY_TARIFF.getTagName();
        if(!qName.equals(TARIFFS) && constantTariffTag.equals(qName) || temporaryTariffTag.equals(qName)) {
                tariff = temporaryTariffTag.equals(qName) ? new TemporaryTariff() : new ConstantTariff();
                tariff.setTariffId(attributes.getValue("tariff-id"));
                tariff.setTitle(attributes.getValue("title"));
            } else {
                TariffXmlTag temp = valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE));
                if (withText.contains(temp)) {
                    currentTag = temp;
                }
            }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentTag != null) {
            switch (currentTag) {
                case NAME -> tariff.setName(data);
                case YEAR -> tariff.setYear(Year.parse(data));
                case OPERATOR_NAME -> tariff.setOperatorName(data);
                case PAYROLL -> tariff.setPayroll(data);
                case ON_NET_CALLS -> tariff.getCallPrices().setOnNetCalls(data);
                case OUT_OF_NETWORK_CALLS-> tariff.getCallPrices().setOutOfNetworkCalls(data);
                case CALLS_TO_LANDLINES -> tariff.getCallPrices().setCallsToLandlines(data);
                case SMS_PRICE -> tariff.setSmsPrices(data);
                case PRESENCE_OF_A_FAVORITE_NUMBER -> tariff.getParameters().setFavoriteNumber(data);
                case TARIFFICATION -> tariff.getParameters().setTariffication(data);
                case TARIFF_CONNECTION_FEE -> tariff.getParameters().setTariffConnectionFee(data);
                case CONSTANT_TARIFF_PARAMETERS -> {
                    ConstantTariff constantTariff = (ConstantTariff) tariff;
                    constantTariff.setConstantTariffParameters(data);
                }
                case TEMPORARY_TARIFF_PARAMETERS -> {
                    TemporaryTariff temporaryTariff = (TemporaryTariff) tariff;
                    temporaryTariff.setTemporaryTariffParameters(data);
                }
            }
            currentTag = null;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String constantTariffTag = CONSTANT_TARIFF.getTagName();
        String temporaryTariffTag = TEMPORARY_TARIFF.getTagName();
        if (constantTariffTag.equals(qName) || temporaryTariffTag.equals(qName)) {
            tariffs.add(tariff);
        }
    }


}
