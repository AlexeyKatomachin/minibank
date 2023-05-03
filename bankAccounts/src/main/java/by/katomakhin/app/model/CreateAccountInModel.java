package by.katomakhin.app.model;

import by.katomakhin.app.dto.Account;
import by.katomakhin.app.dto.User;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountInModel {
    private User user;
}
