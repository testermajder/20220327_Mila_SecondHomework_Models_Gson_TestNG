package test.suites.registrationAndAuthenticationTests;

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
    public void loginTest() {
        Assert.assertFalse(accessToken.isEmpty(), "Access token is empty");
    }


    @Test
    @Description("Register a new user")
    public void registerANewUser() {
        RegisterANewUserRequest registerANewUserRequest = new RegisterANewUserRequest("newUserMajdertester", "Mila", "Ajder", "newuser.milla.ajder.tester21@gmail.com", "test");
        RegisterANewUserResponse registerANewUserResponse = CrocodilesAPI.registerNewUser(registerANewUserRequest);
        crocodileAsserts.assertRegisterANewUser(registerANewUserResponse, registerANewUserRequest);
    }



}







