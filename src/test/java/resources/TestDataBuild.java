package resources;

import Pojo.AddApi;
import Pojo.Location;
import StepDefinition.AddPlace;

import java.util.ArrayList;

public class TestDataBuild {
    public AddApi addPlacePayload(String name,String language,String address)
    {
        AddApi p=new AddApi();
        //setting the values of java objects
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://rahulshettyacademy.com");
        //types is a list of strings
        ArrayList<String> type=new ArrayList<>();
        type.add("shoe park");
        type.add("shop");
        p.setTypes(type);

        //location has type Location class and expects a location object
        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deleteApiPayload(String placeId)
    {
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}";
    }
}
