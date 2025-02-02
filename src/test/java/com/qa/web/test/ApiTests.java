package com.qa.web.test;

import com.qa.enums.ApiRequest;
import com.qa.enums.AppConstants;
import com.qa.enums.ConfigKeys;
import com.qa.factory.RequestManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static com.qa.utils.ApiUtils.isMapKeysContainsInList;
import static com.qa.utils.WebUtils.assertEquals;

public class ApiTests extends BaseTest{

    @Test(description = "Verify 3BPIs and GBP ‘description’ equals ‘British Pound Sterling’")
    public void verifyCurrentPrice() {

        List<String> bpiListExpected = Arrays.asList("USD","GBP","EUR");
        String gbpDescriptionExpected = "British Pound Sterling";

        Response response = new RequestManager(ConfigKeys.API_URL)
                .setBasePath(AppConstants.API_BASEPATH_BPI)
                .createRequest(ApiRequest.GET);

        JsonPath jsonPath = response.jsonPath();

        Map<String,Object> bpiJson = jsonPath.getMap("bpi");

        isMapKeysContainsInList(bpiListExpected, bpiJson.keySet());

        String gbpDescriptionActual = jsonPath.getString("bpi.GBP.description");
        assertEquals(gbpDescriptionActual,gbpDescriptionExpected);
    }
}
