package epam.task.parsing.entity;

public class TemporaryTariff extends Tariff {
    private String temporaryTariffParameters;

    public TemporaryTariff() {
        super();
    }

    public String getTariffParameters(){
        return temporaryTariffParameters;
    }

    public void setTemporaryTariffParameters(String temporaryTariffParameters){
        this.temporaryTariffParameters = temporaryTariffParameters;
   }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TemporaryTariff that = (TemporaryTariff) o;
        return temporaryTariffParameters == that.temporaryTariffParameters;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result * 31 + temporaryTariffParameters.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{").append(super.toString());
        sb.append("temporaryTariffParameters=").append(temporaryTariffParameters);
        sb.append('}');
        return sb.toString();
    }
}
