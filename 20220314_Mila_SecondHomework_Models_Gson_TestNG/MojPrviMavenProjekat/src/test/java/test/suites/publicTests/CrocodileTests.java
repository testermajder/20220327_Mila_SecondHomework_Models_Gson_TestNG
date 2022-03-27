package test.suites.publicTests;

import calls.CrocodilesAPI;
import data.models.privateTest.CreateCrocodileRequest;
import data.models.publicTest.CrocodileResponse;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.asserts.CrocodileAsserts;
import test.common.TestBase;

import java.text.ParseException;
import java.time.LocalDate;

public class CrocodileTests extends TestBase {

    public CrocodileAsserts crocodileAsserts = new CrocodileAsserts();


    @Test
    @Description("Get list of public crocodiles")
    public void getListOfPublicCrocodiles() {
        CrocodileResponse[] getPublicCrocodileResponse = CrocodilesAPI.getPublicCrocodileResponse();
        crocodileAsserts.assertListOfCrocodiles(getPublicCrocodileResponse);
    }


    @Test
    @Description("Get a single crocodile")
    public void getASingleCrocodile() {
        CrocodileResponse getASinglePublicCrocodileResponse = CrocodilesAPI.getASinglePublicCrocodileResponse();
        crocodileAsserts.assertASingleCrocodile(getASinglePublicCrocodileResponse);
    }


}







