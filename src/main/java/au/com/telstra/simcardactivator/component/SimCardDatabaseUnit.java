package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.jpa2h2.ActivationRecord;
import au.com.telstra.simcardactivator.jpa2h2.CustomerRespository;
import org.springframework.stereotype.Component;

@Component
public class SimCardDatabaseUnit {
    private final CustomerRespository customerRespository;
    public SimCardDatabaseUnit(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }

    public void save(SimCard simCard, ActuationResult actuationResult) {
        ActivationRecord activationRecord = new ActivationRecord(simCard, actuationResult);
        customerRespository.save(activationRecord);
    }

    public ActuationResult queryByIccid(String iccid) {
        var activationRecord =  customerRespository.findByIccid(iccid);
        return new ActuationResult(activationRecord.isActive());
    }
}
