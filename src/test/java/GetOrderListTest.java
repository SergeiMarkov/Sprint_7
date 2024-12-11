import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.OrderSteps;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class GetOrderListTest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Позитивная проверка на получение заказа и возвражение не пустого orders в ответе, endpoint /api/v1/orders")
    public void getOrderListTest() {
        Response response = OrderSteps.getOrderList();
        response
                .then().log().all()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("orders", notNullValue());
    }
}
