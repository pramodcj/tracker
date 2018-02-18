package pramod.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(query = "select new pramod.entity.Alert(a.vin,count(a.alertLevel))from Alert a where a.alertLevel='HIGH' and a.alertTime>:paramalertTimeStamp group by a.vin",name = "Alert.findAllHighAlerts"),
        @NamedQuery(query = "select a from Alert a where a.vin=:alertparamVin order by a.alertTime desc ",name = "Alert.findAllByVin")
})

public class Alert {

    @Id
    private String alertID;
    public Alert(){
        this.alertID= UUID.randomUUID().toString();
    }

    private String vin;
    private String reason;
    private String alertLevel;
    @JsonProperty("alertTime")
    private Date alertTime;
    private String useremail;

    @JsonIgnore
    @Transient
    private long count;

    public String getAlertID() {
        return alertID;
    }

    public void setAlertID(String alertID) {
        this.alertID = alertID;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
