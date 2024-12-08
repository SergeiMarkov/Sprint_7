import courier.Courier;
import courier.CourierSteps;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;

public class CreateCourierTest {
    String id;

    @After
    public void deleteCourier() {
        if (id != null) {
            CourierSteps.deleteCourier(id);
        }
    }

    @Test
    @DisplayName("Успешное создание курьера")
    public void createCourierTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        Response response = CourierSteps.createCourier(courier);
        id = CourierSteps.getId(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_CREATED).
                and()
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Создание курьера с уже имеющимся в системе login")
    public void createDuplicateCourierTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        CourierSteps.createCourier(courier);
        id = CourierSteps.getId(courier);
        Response response = CourierSteps.createCourier(courier);
        response
                .then().log().all()
                .assertThat().statusCode(SC_CONFLICT).
                and()
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без поля login")
    public void createCourierWithoutFieldLoginTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        courier.setLogin(null);
        Response response = CourierSteps.createCourier(courier);
        id = CourierSteps.getId(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST).
                and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без login")
    public void createCourierWithoutLoginTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        courier.setLogin("");
        Response response = CourierSteps.createCourier(courier);
        id = CourierSteps.getId(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST).
                and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без поля password")
    public void createCourierWithoutFieldPasswordTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        courier.setPassword(null);
        Response response = CourierSteps.createCourier(courier);
        id = CourierSteps.getId(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST).
                and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без password")
    public void createCourierWithoutPasswordTest() {
        Courier courier = CourierSteps.createCourierWithDefaultData();
        courier.setPassword("");
        Response response = CourierSteps.createCourier(courier);
        id = CourierSteps.getId(courier);
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST).
                and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

}
