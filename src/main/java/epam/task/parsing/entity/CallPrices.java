package epam.task.parsing.entity;

import java.util.Objects;

public class CallPrices {

    private String onNetCalls;
    private String outOfNetworkCalls;
    private String callsToLandlines;

    public CallPrices() {
    }

    public CallPrices(String onNetCalls, String outOfNetworkCalls, String callsToLandlines) {
        this.onNetCalls = onNetCalls;
        this.outOfNetworkCalls = outOfNetworkCalls;
        this.callsToLandlines = callsToLandlines;
    }

    public String getOnNetCalls() {
        return onNetCalls;
    }

    public void setOnNetCalls(String onNetCalls) {
        this.onNetCalls = onNetCalls;
    }

    public String getOutOfNetworkCalls() {
        return outOfNetworkCalls;
    }

    public void setOutOfNetworkCalls(String outOfNetworkCalls) {
        this.outOfNetworkCalls = outOfNetworkCalls;
    }

    public String getCallsToLandlines() {
        return callsToLandlines;
    }

    public void setCallsToLandlines(String callsToLandlines) {
        this.callsToLandlines = callsToLandlines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallPrices that = (CallPrices) o;
        return Objects.equals(onNetCalls, that.onNetCalls) &&
                Objects.equals(outOfNetworkCalls, that.outOfNetworkCalls) &&
                Objects.equals(callsToLandlines, that.callsToLandlines);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (onNetCalls != null ? onNetCalls.hashCode() : 0);
        result = 31 * result + (outOfNetworkCalls != null ? outOfNetworkCalls.hashCode() : 0);
        result = 31 * result + (callsToLandlines != null ? callsToLandlines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{");
        sb.append("onNetCalls='").append(onNetCalls).append('\'');
        sb.append(", outOfNetworkCalls='").append(outOfNetworkCalls).append('\'');
        sb.append(", callsToLandlines='").append(callsToLandlines).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
