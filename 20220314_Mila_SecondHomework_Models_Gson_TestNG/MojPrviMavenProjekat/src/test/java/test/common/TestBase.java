package test.common;

import calls.CrocodilesAPI;
import data.models.privateTest.LoginRequest;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public String accessToken;

    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://test-api.k6.io";
        accessToken = CrocodilesAPI.login(new LoginRequest("testermajder11", "test")).getAccess();
    }
}
