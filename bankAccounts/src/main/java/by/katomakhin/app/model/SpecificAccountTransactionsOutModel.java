package by.katomakhin.app.model;

import by.katomakhin.app.dto.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class SpecificAccountTransactionsOutModel {
    List<User> users;
}
