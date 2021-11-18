package epam.task.parsing.factory;

import epam.task.parsing.builder.AbstractTariffBuilder;
import epam.task.parsing.builder.TariffDomBuilder;
import epam.task.parsing.builder.TariffSaxBuilder;
import epam.task.parsing.builder.TariffStaxBuilder;
import epam.task.parsing.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffsFactory {
    private static final Logger logger = LogManager.getLogger();

    private enum TypeParser{
        DOM, SAX,STAX
    }

    public static AbstractTariffBuilder createBuilder(String parserType) throws CustomException {
        TypeParser type = TypeParser.valueOf(parserType.toUpperCase());
        switch (type){
            case DOM -> {
                return new TariffDomBuilder();
            }
            case SAX -> {
                return  new TariffSaxBuilder();
            }
            case STAX -> {
                return new TariffStaxBuilder();
            }
            default -> {
                logger.error("Invalid type." + type);
                throw new CustomException("Invalid type." + type);
            }
        }
    }
}
