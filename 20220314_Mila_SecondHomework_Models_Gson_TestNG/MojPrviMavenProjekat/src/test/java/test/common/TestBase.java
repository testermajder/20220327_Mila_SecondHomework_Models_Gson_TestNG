package test.common;

import calls.CrocodilesAPI;
import data.models.privateTest.LoginRequest;
import environment.ConfigReader;
import environment.ConfigSetup;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public String accessToken;

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = ConfigSetup.getBaseUrl();
        accessToken = CrocodilesAPI.login(new LoginRequest(ConfigSetup.getMainUser(), ConfigSetup.getDefaultPsw())).getAccess();
    }
}
