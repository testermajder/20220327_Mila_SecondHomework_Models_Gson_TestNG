package test.suites.CRUD_Operations;

import calls.CrocodilesAPI;
import data.models.RegistrationAndAuthentication.RegisterANewUserRequest;
import data.models.RegistrationAndAuthentication.RegisterANewUserResponse;
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
    @Description("verify crocodile is created")
    public void createCrocodileTest() {
        CreateCrocodileRequest createCrocodileRequest = new CreateCrocodileRequest("New crocodile created1", "M", "2021-03-14");

        CrocodileResponse createCrocodileResponse = CrocodilesAPI.createNewCrocodile(accessToken, createCrocodileRequest);

        crocodileAsserts.assertCreateNewCrocodile(createCrocodileResponse, createCrocodileRequest);
    }


    @Test
    @Description("Get my crocodiles")
    public void getListOfMyCrocodiles() {
        CrocodileResponse[] getCrocodileResponse = CrocodilesAPI.getMyCrocodilesResponses(accessToken);
        crocodileAsserts.assertListOfCrocodiles(getCrocodileResponse);
    }

    @Test
    @Description("Get a single private crocodile")
    public void getMySingleCrocodile() {
        CrocodileResponse getCrocodileResponse = CrocodilesAPI.getMyCrocodileResponse(accessToken);
        crocodileAsserts.assertASingleCrocodile(getCrocodileResponse);
    }

    @Test
    @Description("Delete a single private crocodile")
    public void deleteMyCrocodile() {
        CrocodileResponse[] crocodileResponse = CrocodilesAPI.getMyCrocodilesResponses(accessToken);
        Integer id = crocodileResponse[RandomUtils.nextInt(0, crocodileResponse.length)].getId();

        CrocodileResponse crocodileSingleResponse = CrocodilesAPI.deleteMyCrocodileResponseById(accessToken, id);
        Assert.assertNull(crocodileSingleResponse, "Delete crocodile contains data");

    }

    @Test
    @Description("Update a single private crocodile with Patch")
    public void updateMyCrocodileWithPatch() {
        int id = Integer.parseInt(CrocodilesAPI.GetId(accessToken));
        CrocodileResponse crocodileResponse = new CrocodileResponse(id, "Gasa", null, null, null);
        CrocodileResponse crocodileResponsePatch = CrocodilesAPI.updateMyCrocodileResponsePatch(accessToken, id, crocodileResponse);
        crocodileAsserts.assertUpdateMyCrocodilePatch(crocodileResponsePatch, crocodileResponse);
    }


    @Test
    @Description("Update a single private crocodile with Put")
    public void updateMyCrocodileWithPut() throws ParseException {

        int id = Integer.parseInt(CrocodilesAPI.GetId(accessToken));
        LocalDate date = LocalDate.of(2000, 5, 20);
        CrocodileResponse crocodileResponse = new CrocodileResponse(id, "Milan", "M", "2003-05-20", CrocodilesAPI.YearsPassed(date));

        CrocodileResponse crocodileResponsePut = CrocodilesAPI.updateMyCrocodileResponsePut(accessToken,id, crocodileResponse);
        crocodileAsserts.assertupdateMyCrocodilePut(crocodileResponsePut, crocodileResponse);
    }



}







