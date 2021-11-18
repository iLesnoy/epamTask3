package epam.task.parsing.builder;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


class TariffDomBuilderTest {

    private TariffDomBuilder tariffDomBuilder;
    private String fileName = "resources/tariffs.xml";

    @BeforeEach
    public void init() {
        tariffDomBuilder = new TariffDomBuilder();
    }

    @Test
    public void buildTariffs() {
        tariffDomBuilder.buildTariffs(fileName);
        int actual = tariffDomBuilder.tariffSet.size();
        assertEquals(8,actual);
    }


}