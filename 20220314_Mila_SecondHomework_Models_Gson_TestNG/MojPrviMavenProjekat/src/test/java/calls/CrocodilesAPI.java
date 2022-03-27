package calls;

import common.GsonFunctions;
import common.RestAssuredFunctions;
import data.models.RegistrationAndAuthentication.RegisterANewUserRequest;
import data.models.RegistrationAndAuthentication.RegisterANewUserResponse;
import data.models.privateTest.CreateCrocodileRequest;
import data.models.privateTest.LoginRequest;
import data.models.privateTest.LoginResponse;
import data.models.publicTest.CrocodileResponse;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.groovy.parser.antlr4.GroovyParser;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class CrocodilesAPI {

    //post requests:
    public static LoginResponse login(LoginRequest loginRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post("auth/token/login/", loginRequest), LoginResponse.class);
    }

    public static CrocodileResponse createNewCrocodile(String accessToken, CreateCrocodileRequest createCrocodileRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post("my/crocodiles/", accessToken, createCrocodileRequest), CrocodileResponse.class);
    }

    public static RegisterANewUserResponse registerNewUser(RegisterANewUserRequest registerANewUserRequest){
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post("user/register/", registerANewUserRequest), RegisterANewUserResponse.class);
    }

    public static CrocodileResponse[] getPublicCrocodileResponse() {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get("public/crocodiles/"), CrocodileResponse[].class);
    }

    public static CrocodileResponse getASinglePublicCrocodileResponse(){
        return  GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get("/public/crocodiles/1/"),  CrocodileResponse.class);
    }

    public static CrocodileResponse getCrocodileById(int id, String accessToken){
        return  GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get("/my/crocodiles/" + id + "/", accessToken),  CrocodileResponse.class);
    }

    public static CrocodileResponse[] getMyCrocodilesResponses(String accessToken){
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get("/my/crocodiles/", accessToken), CrocodileResponse[].class);
    }

    public static CrocodileResponse getMyCrocodileResponse(String accessToken){
        return  GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get("/my/crocodiles/" + GetId(accessToken),accessToken),  CrocodileResponse.class);
    }

    public static String GetId(String accessToken) {
        Response responseAllCrocodiles = RestAssuredFunctions.get("my/crocodiles/" , accessToken);
        String responseAsString = responseAllCrocodiles.body().asString();
        String id = responseAsString.substring(responseAsString.indexOf("{\"id\":") + 6, responseAsString.indexOf(",\""));
        return id;
    }


    public static CrocodileResponse deleteMyCrocodileResponseById(String accessToken, int id) {
        CrocodileResponse crocodileResponse = GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.delete("/my/crocodiles/" + id + "/",accessToken),  CrocodileResponse.class);
        return crocodileResponse;
    }



    public static CrocodileResponse updateMyCrocodileResponsePatch(String accessToken, int id,  CrocodileResponse crocodileResponse) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.patch("/my/crocodiles/" + id +"/", accessToken, crocodileResponse), CrocodileResponse.class);
    }

    public static CrocodileResponse updateMyCrocodileResponsePut(String accessToken,  int id,  CrocodileResponse crocodileResponse) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.put("/my/crocodiles/" + id +"/", accessToken, crocodileResponse), CrocodileResponse.class);
    }

    public static int YearsPassed(LocalDate date) throws ParseException {
        LocalDate today = LocalDate.now();

        Period period = date.until(today);
        return period.getYears();
    }

}
