import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.Order;
import order.OrderSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final List<String> color;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] createOrderTestData() {
        return new Object[][]{

                {List.of("GRAY")},
                {List.of("BLACK")},
                {List.of("GRAY, BLACK")},
                {List.of("")}

        };
    }

    @Test
    @DisplayName("Успешное создание заказа. Возвращение track")
    public void createOrderTest() {
        Order order = new Order(color);
        Response response = OrderSteps.createOrder(order);
        response
                .then().log().all()
                .assertThat().statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue());
    }

}
