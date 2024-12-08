package restapi;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAPI {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    public static RequestSpecification spec() {
        return given().log().all()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);

    }
}
