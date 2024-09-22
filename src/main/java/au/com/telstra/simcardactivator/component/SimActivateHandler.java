package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimActivateHandler {
    private final RestTemplate restTemplate;
    private final String inceptiveApiUrl;

    public SimActivateHandler(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
        this.inceptiveApiUrl = "http://localhost:8444/actuate";
    }

    public ActuationResult activate(SimCard simCard) {
        return restTemplate.postForObject(inceptiveApiUrl,simCard, ActuationResult.class);
    }

}
