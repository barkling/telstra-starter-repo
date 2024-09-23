package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.jpa2h2.ActivationRecord;
import au.com.telstra.simcardactivator.jpa2h2.CustomerRespository;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimActivateRestController {
    private final SimActivateHandler simActivateHandler;
    private final CustomerRespository customerRespository;
    private final SimCardDatabaseUnit simCardDatabaseUnit;

    public SimActivateRestController(SimActivateHandler simActivateHandler, CustomerRespository customerRespository, SimCardDatabaseUnit simCardDatabaseUnit) {
        this.simActivateHandler = simActivateHandler;
        this.customerRespository = customerRespository;
        this.simCardDatabaseUnit = simCardDatabaseUnit;
    }

    @PostMapping("/activate")
    public ActuationResult handleSimcardActivate(@RequestBody SimCard card) {
        var actuationResult = simActivateHandler.activate(card);
//        customerRespository.save(new ActivationRecord(card,actuationResult));
        simCardDatabaseUnit.save(card, actuationResult);
        System.out.println(actuationResult.isSuccess());
        return actuationResult;
    }

    @GetMapping("/findCustomer")
    public ActivationRecord findCustomerById(@RequestParam Long simCardId) {
        return customerRespository.findByCustomerId(simCardId);
    }
    @GetMapping("/findByIccid")
    public ActuationResult findByIccid(@RequestParam String iccid) {
        return simCardDatabaseUnit.queryByIccid(iccid);
    }
}
