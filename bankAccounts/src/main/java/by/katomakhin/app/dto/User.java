package by.katomakhin.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class User {
    private String name;
    private String pin;
    private List<Account> accounts;
}
