package StepDefinition;

import io.cucumber.core.backend.StepDefinition;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        AddPlace m=new AddPlace();
        if(AddPlace.placeId==null) {
            m.addPlacePayloadWith("Megha Tripathi", "Bhojpuri", "Ranchi");
            m.userCallsWithHttpRequest("AddPlaceApi", "POST");
            m.verifyPlace_idMappedToUsing("Megha Tripathi", "GetPlaceApi");
        }
    }

}
