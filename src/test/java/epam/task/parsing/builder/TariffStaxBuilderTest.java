package epam.task.parsing.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffStaxBuilderTest {

    private final TariffStaxBuilder staxBuilder = new TariffStaxBuilder();


    @Test
    void buildTariffs() {
        int expected = 4;
        TariffDomBuilder tariffDomBuilder = new TariffDomBuilder();
        staxBuilder.buildTariffs("resources/tariffs.xml");
        int actual = staxBuilder.getTariffs().size();
        assertEquals(expected,actual);
    }
}