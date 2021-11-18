package epam.task.parsing.builder;

public enum TariffXmlTag {
    TARIFFS,
    TARIFF_ID,
    NAME,
    YEAR,
    OPERATOR_NAME,
    PAYROLL,
    CALL_PRICES,
    ON_NET_CALLS,
    OUT_OF_NETWORK_CALLS,
    CALLS_TO_LANDLINES,
    SMS_PRICE,
    PARAMETERS,
    PRESENCE_OF_A_FAVORITE_NUMBER,
    TARIFFICATION,
    TARIFF_CONNECTION_FEE,
    TEMPORARY_TARIFF,
    CONSTANT_TARIFF,
    CONSTANT_TARIFF_PARAMETERS,
    TEMPORARY_TARIFF_PARAMETERS;

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    public String getTagName() {
        String tageName = this.name();
        tageName = tageName.toLowerCase();
        tageName = tageName.replace(UNDERSCORE, HYPHEN);
        return tageName;
    }

}
