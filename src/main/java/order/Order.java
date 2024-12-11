package order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    private String track;

    public Order(String track) {
        this.track = track;
    }

    public Order (List<String> color) {
        this.firstName = "Naruto";
        this.lastName = "Uchiha";
        this.address = "Konoha, 142 apt.";
        this.metroStation = 4;
        this.phone = "+7 800 355 35 35";
        this.rentTime = 5;
        this.deliveryDate = "2024-12-28";
        this.comment = "Saske, come back to Konoha";
        this.color = color;
    }

    public Order() {
    }

}
