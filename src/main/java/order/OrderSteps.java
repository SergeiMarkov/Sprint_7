package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static restapi.RestAPI.spec;

public class OrderSteps {
    private static final String ORDER_PATH = "/api/v1/orders";

    @Step("Создание заказа")
    public static Response createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH);
    }

    @Step("Получение списка заказов")
    public static Response getOrderList() {
        return spec()
                .when()
                .get(ORDER_PATH);
    }
}
