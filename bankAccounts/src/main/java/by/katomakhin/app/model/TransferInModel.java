package by.katomakhin.app.model;

import by.katomakhin.app.dto.Account;
import by.katomakhin.app.dto.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
public class TransferInModel {
    private User userFrom;
    private Account accTo;
    private BigDecimal value;
}
