package data.provider;

import com.github.javafaker.Faker;
import data.models.privateTest.CreateCrocodileRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class CrocodileProvider {

    public static CreateCrocodileRequest prepareCrocodileRequest(){
        CreateCrocodileRequest createCrocodileRequest = new CreateCrocodileRequest();
        createCrocodileRequest.setName(Faker.instance().gameOfThrones().character());
        createCrocodileRequest.setSex(getGender());
        //this part should be changed - not hardcoded:
        createCrocodileRequest.setDateOfBirth("2020-02-02");
        return createCrocodileRequest;
    }

    private static String getGender(){
        int ramdomN = Faker.instance().number().numberBetween(0, 1);
        if (ramdomN == 0) {
            return "M";
        } else {
            return "F";
        }
    }
}
