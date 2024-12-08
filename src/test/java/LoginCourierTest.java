import courier.Courier;
import courier.CourierDefaultData;
import courier.CourierSteps;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class LoginCourierTest extends CourierDefaultData {
    String id;

    @After
    public void deleteCourier() {
        if (id != null) {
            CourierSteps.deleteCourier(id);
        }
    }

    @Test
    @DisplayName("Успешная авторизация курьера в системе с существующими login и password. Возвращение id")
    public void loginCourierTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера в системе с указанием неправильного (несуществующего) login")
    public void loginCourierWithWrongLoginTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setLogin(COURIER_WRONG_LOGIN);
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_NOT_FOUND).
                and()
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера в системе с указанием неправильного (несуществующего) password")
    public void loginCourierWithWrongPasswordTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setPassword(COURIER_WRONG_PASSWORD);
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера в системе с указанием неправильных (несуществующих) login и password")
    public void loginCourierWithWrongDataTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setLogin(COURIER_WRONG_LOGIN);
        courier.setPassword(COURIER_WRONG_PASSWORD);
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Логин в системе без поля login")
    public void loginCourierWithoutFieldLoginTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setLogin(null);
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин в системе без login")
    public void loginCourierWithoutLoginTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setLogin("");
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    // Тест выдает ошибку 504
    @Test
    @DisplayName("Логин в системе без поля password")
    public void loginCourierWithoutFieldPasswordTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setPassword(null);
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин в системе без password")
    public void loginCourierWithoutPasswordTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setPassword("");
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    // Тест выдает ошибку 504
    @Test
    @DisplayName("Логин в системе без поля login и password")
    public void loginCourierWithoutFieldDataTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setLogin(null);
        courier.setPassword(null);
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин в системе без login и password")
    public void loginCourierWithoutDataTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        courier.setFirstName(null);
        id = CourierSteps.getId(courier);
        courier.setLogin("");
        courier.setPassword("");
        Response response = CourierSteps.loginCourier(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }
}
