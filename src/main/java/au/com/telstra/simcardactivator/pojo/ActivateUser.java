package au.com.telstra.simcardactivator.pojo;

public class ActivateUser {
    private String iccid;
    private String customerEmail;

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
}
