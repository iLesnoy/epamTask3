package epam.task.parsing.entity;

public class ConstantTariff extends Tariff {
    private String constantTariffParameters;

    public ConstantTariff() {
        super();
    }

    public String getConstantTariffParameters(){
        return constantTariffParameters;
    }

    public void setConstantTariffParameters(String constantTariffParameters){
        this.constantTariffParameters = constantTariffParameters;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConstantTariff that = (ConstantTariff) o;
        return constantTariffParameters == that.constantTariffParameters;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result * 31 + constantTariffParameters.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{").append(super.toString());
        sb.append("constantTariffParameters=").append(constantTariffParameters);
        sb.append('}');
        return sb.toString();
    }
}
