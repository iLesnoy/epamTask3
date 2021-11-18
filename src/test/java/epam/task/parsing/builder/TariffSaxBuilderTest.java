package epam.task.parsing.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffSaxBuilderTest {
    private TariffSaxBuilder builder;

    @BeforeEach
    public void init(){
        builder = new TariffSaxBuilder();
    }

    @Test
    void TariffSaxBuilder() {
        String fileName = "resources/tariffs.xml";
        builder.buildTariffs(fileName);
        int actual = builder.getTariffs().size();

        assertEquals(4,actual);
    }
}