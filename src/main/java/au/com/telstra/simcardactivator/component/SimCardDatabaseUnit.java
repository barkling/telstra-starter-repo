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

    public SimCard queryByIccid(long simCardId) {
        var activationRecord =  customerRespository.findById(simCardId).orElse(null);
        if (activationRecord == null) {
            return null;
        }
        return new SimCard(activationRecord);
    }
}
