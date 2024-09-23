package au.com.telstra.simcardactivator.foundation;

import au.com.telstra.simcardactivator.jpa2h2.ActivationRecord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimCard {
    private String iccid;
    private String customerEmail;
    private boolean active;

    public SimCard(){

    }
    public SimCard(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    public SimCard(ActivationRecord activationRecord) {
        this.iccid = activationRecord.getIccid();
        this.customerEmail = activationRecord.getCustomerEmail();
        this.active = activationRecord.isActive();
    }
    // getters and setters
    public String getIccid() {
        return iccid;
    }
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SimCard {iccid=" + iccid + ", customerEmail=" + customerEmail + ", active=" + active + "}";
    }
}
