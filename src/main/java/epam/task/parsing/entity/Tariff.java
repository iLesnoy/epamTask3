package epam.task.parsing.entity;

import java.time.Year;
import java.util.Objects;

public class Tariff {

    private String tariffId;
    private String title;
    private String name;
    private Year year;
    private String operatorName;
    private String payroll;
    private CallPrices callPrices;
    private String smsPrices;
    private Parameters parameters;



    public Tariff() {
        callPrices = new CallPrices();
        parameters = new Parameters();
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTariffId() {
        return tariffId;
    }

    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getPayroll() {
        return payroll;
    }

    public void setPayroll(String payroll) {
        this.payroll = payroll;
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public String getSmsPrices() {
        return smsPrices;
    }

    public void setSmsPrices(String smsPrices) {
        this.smsPrices = smsPrices;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return  Objects.equals(tariffId, tariff.tariffId) &&
                Objects.equals(name, tariff.name) &&
                Objects.equals(year, tariff.year) &&
                Objects.equals(operatorName, tariff.operatorName) &&
                Objects.equals(payroll, tariff.payroll) &&
                Objects.equals(callPrices, tariff.callPrices) &&
                Objects.equals(title, tariff.title) &&
                Objects.equals(smsPrices, tariff.smsPrices) &&
                Objects.equals(parameters, tariff.parameters);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (tariffId != null ? tariffId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (operatorName != null ? operatorName.hashCode() : 0);
        result = 31 * result + (payroll != null ? payroll.hashCode() : 0);
        result = 31 * result + (callPrices != null ? callPrices.hashCode() : 0);
        result = 31 * result + (smsPrices != null ? smsPrices.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{");
        sb.append("tariffId='").append(tariffId).append('\'');
        sb.append("  title='").append(title).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", year='").append(year).append('\'');
        sb.append(", operatorName='").append(operatorName).append('\'');
        sb.append(", payroll='").append(payroll).append('\'');
        sb.append(", callPrices='").append(callPrices).append('\'');
        sb.append(", smsPrices='").append(smsPrices).append('\'');
        sb.append(", parameters='").append(parameters).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
