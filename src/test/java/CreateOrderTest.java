import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.Order;
import order.OrderSteps;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final List<String> color;
    protected String track;

    @After
    public void cancelOrder() {
        if (track != null) {
            OrderSteps.cancelOrder(track);
        }
    }

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет самоката: {0}")
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
    @Description("Позитивная проверка на создание заказа и возвражение не пустого поля track в отета, endpoint /api/v1/orders")
    public void createOrderTest() {
        Order order = new Order(color);
        Response response = OrderSteps.createOrder(order);
        track = OrderSteps.getTrack(order);
        response
                .then().log().all()
                .assertThat().statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue());
    }

}
