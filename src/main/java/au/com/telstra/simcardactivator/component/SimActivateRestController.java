package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.jpa2h2.ActivationRecord;
import au.com.telstra.simcardactivator.jpa2h2.CustomerRespository;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimActivateRestController {
    private final SimActivateHandler simActivateHandler;
    private final CustomerRespository customerRespository;

    public SimActivateRestController(SimActivateHandler simActivateHandler, CustomerRespository customerRespository) {
        this.simActivateHandler = simActivateHandler;
        this.customerRespository = customerRespository;
    }

    @PostMapping("/activate")
    public String handleSimcardActivate(@RequestBody SimCard card) {
        var actuationResult = simActivateHandler.activate(card);
        customerRespository.save(new ActivationRecord(card.getIccid(),card.getCustomerEmail(), actuationResult.getSuccess()));
        System.out.println(actuationResult.getSuccess());
        return actuationResult.toString();
    }

    @GetMapping("/findCustomer")
    public String findCustomerById(@RequestParam Long simCardId) {
        return customerRespository.findByCustomerId(simCardId).toString();
    }
}
