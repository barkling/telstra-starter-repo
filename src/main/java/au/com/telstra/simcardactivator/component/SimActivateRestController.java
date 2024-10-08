package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.SimCard;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimActivateRestController {
    private final SimActivateHandler simActivateHandler;
    private final SimCardDatabaseUnit simCardDatabaseUnit;

    public SimActivateRestController(SimActivateHandler simActivateHandler, SimCardDatabaseUnit simCardDatabaseUnit) {
        this.simActivateHandler = simActivateHandler;
        this.simCardDatabaseUnit = simCardDatabaseUnit;
    }

    @PostMapping("/activate")
    public void handleSimcardActivate(@RequestBody SimCard card) {
        var actuationResult = simActivateHandler.activate(card);
        simCardDatabaseUnit.save(card, actuationResult);
        System.out.println(actuationResult.isSuccess());
    }

    @GetMapping("/findCustomer")
    public SimCard findCustomerById(@RequestParam Long simCardId) {
        return simCardDatabaseUnit.queryByIccid(simCardId);
    }

}
