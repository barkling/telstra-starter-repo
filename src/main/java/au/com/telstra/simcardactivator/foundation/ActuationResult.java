package au.com.telstra.simcardactivator.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ActuationResult {
    private boolean success;

    public ActuationResult() {}

    public ActuationResult(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ActuationResult {" +
                "success=" + success +
                '}';
    }
}
