package au.com.telstra.simcardactivator.jpa2h2;

import javax.persistence.*;

@Entity
public class ActivationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

//    @Column(name = "iccid", nullable = false)
    private String iccid;

//    @Column(name = "customerEmail", nullable = false)
    private String customerEmail;

//    @Column(name = "active", nullable = false)
    private boolean active;

    // getter and setter

    // 用不到，可以设为protected
    protected ActivationRecord() {

    }
    public ActivationRecord(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
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
