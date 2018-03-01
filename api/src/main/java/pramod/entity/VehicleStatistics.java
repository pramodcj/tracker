package pramod.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "VehicleStatistics.findAllHighAlerts", query = "select vs from VehicleStatistics vs")
})

public class VehicleStatistics {

    @Id
    @JsonProperty("vin")
    private String vin;
    @JsonProperty("highalertcount")
    private long count;

    public VehicleStatistics(){

    }

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
