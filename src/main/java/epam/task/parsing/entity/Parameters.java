package epam.task.parsing.entity;

import java.util.Objects;

public class Parameters {
    private String favoriteNumber;
    private String tariffication;
    private String tariffConnectionFee;

    public Parameters() {
    }

    public Parameters(String favoriteNumber, String tariffication, String tariffConnectionFee) {
        this.favoriteNumber = favoriteNumber;
        this.tariffication = tariffication;
        this.tariffConnectionFee = tariffConnectionFee;
    }

    public String getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(String favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public String getTariffication() {
        return tariffication;
    }

    public void setTariffication(String tariffication) {
        this.tariffication = tariffication;
    }

    public String getTariffConnectionFee() {
        return tariffConnectionFee;
    }

    public void setTariffConnectionFee(String tariffConnectionFee) {
        this.tariffConnectionFee = tariffConnectionFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Objects.equals(favoriteNumber, that.favoriteNumber) &&
               Objects.equals(tariffication, that.tariffication) &&
               Objects.equals(tariffication, that.tariffConnectionFee);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (favoriteNumber != null ? favoriteNumber.hashCode() : 0);
        result = 31 * result + (tariffication != null ? tariffication.hashCode() : 0);
        result = 31 * result + (tariffConnectionFee != null ? tariffConnectionFee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{");
        sb.append("favoriteNumber='").append(favoriteNumber).append('\'');
        sb.append(", tariffication='").append(tariffication).append('\'');
        sb.append(", tariffConnectionFee='").append(tariffConnectionFee).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
