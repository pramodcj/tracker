package pramod.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleStatistics {

    @JsonProperty("VIN")
    private String vin;
    @JsonProperty("highalertcount")
    private long count;

    public VehicleStatistics(String vin, long count) {
        this.vin = vin;
        this.count = count;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
