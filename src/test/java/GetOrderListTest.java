import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.OrderSteps;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class GetOrderListTest {
    @Test
    @DisplayName("Получение списка заказов")
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
