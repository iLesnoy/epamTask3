package epam.task.parsing.builder;

import epam.task.parsing.entity.Tariff;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTariffBuilder {

    protected Set<Tariff> tariffSet;

    protected AbstractTariffBuilder(){
        tariffSet = new HashSet<>();
    }

    protected AbstractTariffBuilder(Set<Tariff> tariffs){
        this.tariffSet = tariffs;
    }

    public Set<Tariff>getTariffs(){
        return tariffSet;
    }

    public abstract void buildTariffs(String filename);
}
