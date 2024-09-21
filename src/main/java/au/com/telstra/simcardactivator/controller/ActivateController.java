package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.service.DataRelayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * a REST controller that accepts post requests to activate SIM cards.
 * These requests will contain JSON payloads including the SIM ICCID and the customer’s email address
 */
@RestController
public class ActivateController {

    private final DataRelayService dataRelayService;

    @Autowired
    public ActivateController(DataRelayService dataRelayService) {
        this.dataRelayService = dataRelayService;
    }

    @PostMapping("/relay")
    public ResponseEntity<String> activateUser(@RequestBody String payload) {
        try {
            String response = dataRelayService.relayToAnotherService(payload);
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
