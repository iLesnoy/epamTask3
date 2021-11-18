package epam.task.parsing.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class tariffSchemValidatorTest {

    String fileName = "resources/tariffs.xml";

    @Test
    void xmlFileCheck() {
        TariffSchemValidator tariffSchemValidator = new TariffSchemValidator();

       assertTrue(tariffSchemValidator.xmlFileCheck(fileName));
    }
}