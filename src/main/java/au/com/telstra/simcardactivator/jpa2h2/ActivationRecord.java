package au.com.telstra.simcardactivator.jpa2h2;

import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;

import javax.persistence.*;

@Entity
public class ActivationRecord {

    @Id
    @GeneratedValue()
    private long customerId;

    @Column(nullable = false)
    private String iccid;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private boolean active;

    // getter and setter

    // 用不到，可以设为protected
    protected ActivationRecord() {

    }
    public ActivationRecord(SimCard simCard, ActuationResult actuationResult) {
        this.iccid = simCard.getIccid();
        this.customerEmail = simCard.getCustomerEmail();
        this.active = actuationResult.isSuccess();
    }
    public long getId() {
        return customerId;
    }
    public void setId(long id) {
        this.customerId = id;
    }
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
        return "ActivationRecord{" +
                "id=" + customerId +
                ", iccid='" + iccid + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", active=" + active +
                '}';
    }
}
