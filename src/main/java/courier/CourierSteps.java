package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static restapi.RestAPI.spec;

public class CourierSteps extends CourierDefaultData {

    private static final String COURIER_PATH = "/api/v1/courier";

    public static Courier createCourierWithDefaultData() {
        return new Courier(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRSTNAME);
    }

    @Step("Создание курьера")
    public static Response createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH);
    }

    @Step("Авторизация курьера в системе")
    public static Response loginCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH + "/login");
    }

    @Step("Получаем id после успешной авторизации курьера в системе")
    public static String getId(Courier courier) {
        Response response = loginCourier(courier);
        if (response.getStatusCode() == 200) {
            return response
                    .then()
                    .extract()
                    .path("id").toString();
        } else {
            return null;
        }
    }

    @Step("Удаление курьера")
    public static void deleteCourier(String id) {
        spec()
                .delete(COURIER_PATH + "/" + id);
    }
}
