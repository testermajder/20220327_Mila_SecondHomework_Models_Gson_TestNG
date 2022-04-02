package test.asserts;

import calls.CrocodilesAPI;
import data.models.RegistrationAndAuthentication.RegisterANewUserRequest;
import data.models.RegistrationAndAuthentication.RegisterANewUserResponse;
import data.models.privateTest.CreateCrocodileRequest;
import data.models.publicTest.CrocodileResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CrocodileAsserts {

    public SoftAssert softAssert = new SoftAssert();


    public void assertCreateNewCrocodile(CrocodileResponse createCrocodileResponse, CreateCrocodileRequest createCrocodileRequest, String accessToken) {
        softAssert.assertEquals(createCrocodileResponse.getName(), createCrocodileRequest.getName(), "Name didn't match");
        softAssert.assertEquals(createCrocodileResponse.getDateOfBirth(), createCrocodileRequest.getDateOfBirth(), "date of birth didn't match");
        softAssert.assertEquals(createCrocodileResponse.getSex(), createCrocodileRequest.getSex(), "Sex didn't match");
        this.softAssert.assertTrue(isCrocodileExist(createCrocodileResponse.getId().toString(), accessToken), "Crocodile is not displayed on list of all users");
        softAssert.assertAll();
    }

    public static boolean isCrocodileExist(String id, String accessToken) {
        CrocodileResponse[] listOfCrocodileResponse = CrocodilesAPI.getMyCrocodilesResponses(accessToken);
        for (int i = 0; i < listOfCrocodileResponse.length; i++) {
            if(listOfCrocodileResponse[i].getId().equals(Integer.valueOf(id))) {
                return true;
            }
        }
        return false;
    }

    public void assertListOfCrocodiles(CrocodileResponse[] getCrocodileResponse) {
        for (int i = 0; i < getCrocodileResponse.length; i++) {
            softAssert.assertFalse(getCrocodileResponse[i].getName().isEmpty(), "Name is not empty");
            softAssert.assertFalse(getCrocodileResponse[i].getId().toString().isEmpty(), "Id is not empty");
            softAssert.assertFalse(getCrocodileResponse[i].getSex().isEmpty(), "Sex is not empty");
            softAssert.assertFalse(getCrocodileResponse[i].getDateOfBirth().isEmpty(), "Date of birth is not empty");
            softAssert.assertFalse(getCrocodileResponse[i].getAge().toString().isEmpty(), "Age is not empty");
        }
        softAssert.assertAll();
    }


    public void assertASingleCrocodile(CrocodileResponse getCrocodileResponse) {
        softAssert.assertFalse(getCrocodileResponse.getName().isEmpty(), "Name is not empty");
        softAssert.assertFalse(getCrocodileResponse.getId().toString().isEmpty(), "Id is not empty");
        softAssert.assertFalse(getCrocodileResponse.getSex().isEmpty(), "Sex is not empty");
        softAssert.assertFalse(getCrocodileResponse.getDateOfBirth().isEmpty(), "Date of birth is not empty");
        softAssert.assertFalse(getCrocodileResponse.getAge().toString().isEmpty(), "Age is not empty");
        softAssert.assertAll();
    }

    public void assertRegisterANewUser(RegisterANewUserResponse registerANewUserResponse, RegisterANewUserRequest registerANewUserRequest) {
        softAssert.assertEquals(registerANewUserResponse.getUsername(), registerANewUserRequest.getUsername(), "Username didn't match");
        softAssert.assertEquals(registerANewUserResponse.getFirstName(), registerANewUserRequest.getFirstName(), "First name didn't match");
        softAssert.assertEquals(registerANewUserResponse.getLastName(), registerANewUserRequest.getLastName(), "Last name didn't match");
        softAssert.assertEquals(registerANewUserResponse.getEmail(), registerANewUserRequest.getEmail(), "Email didn't match");
        softAssert.assertAll();
    }

    public void assertupdateMyCrocodilePut(CrocodileResponse crocodileResponsePut, CrocodileResponse crocodileResponse) {
        softAssert.assertEquals(crocodileResponsePut.getName(), crocodileResponse.getName(), "Name didn't match");
        softAssert.assertEquals(crocodileResponsePut.getAge(), crocodileResponse.getAge(), "Age didn't match");
        softAssert.assertEquals(crocodileResponsePut.getDateOfBirth(), crocodileResponse.getDateOfBirth(), "Date of birth didn't match");
        softAssert.assertEquals(crocodileResponsePut.getSex(), crocodileResponse.getSex(), "Sex didn't match");
        softAssert.assertAll();
    }

    public void assertUpdateMyCrocodilePatch(CrocodileResponse crocodileResponsePatch, CrocodileResponse crocodileResponse) {
        softAssert.assertEquals(crocodileResponsePatch.getName(), crocodileResponse.getName(), "Name didn't match");
        softAssert.assertAll();
    }
}