package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.jpa2h2.ActivationRecord;
import au.com.telstra.simcardactivator.jpa2h2.CustomerRespository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:8080/activate";
    private final String qryUrl = "http://localhost:8080/findByIccid";
    private SimCard simCard = new SimCard();
    private ActuationResult answer;
    private ActivationRecord record;
    private CustomerRespository customerRespository;
    private boolean status;

    @Given("I have a SIM card with ICCID \"1255789453849037777\"")
    public void i_have_a_SIM_card_with_ICCID() {
        simCard = new SimCard("1255789453849037777", "horatio.yakima@groovemail.com", false);
    }

    @Given("I have a SIM card with ICCID \"8944500102198304826\"")
    public void i_have_a_SIM_card_with_ICCID_8944500102198304826() {
        simCard = new SimCard("8944500102198304826", "notorious.criminal@gonepostal.com", false);
    }

    @When("I submit an activation request")
    public void i_submit_an_activation_request() {
        answer = restTemplate.postForObject(url, simCard, ActuationResult.class);
//        simCard.setActive(answer.isSuccess());
//        customerRespository.save(new ActivationRecord(simCard.getIccid(),simCard.getCustomerEmail(),answer.isSuccess()));
    }

    @Then("the activation should be successful")
    public void the_activation_should_be_successful() {
        assertThat(answer.isSuccess()).isTrue();
    }

    @Then("the activation should fail")
    public void the_activation_should_be_fail() {
        assertThat(answer.isSuccess()).isFalse();
    }

    @When("I query the activation status for ICCID {}")
    public void i_query_the_activation_status_for_ICCID(String iccid) {
        record = restTemplate.getForObject(qryUrl, ActivationRecord.class, iccid);
    }

    @Then("the status should be {}")
    public void the_status_should_be(String status) {
        assertThat(record.isActive()).isEqualTo(status);
    }
}