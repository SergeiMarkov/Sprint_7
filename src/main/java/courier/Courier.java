package courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier() {
    }

}
