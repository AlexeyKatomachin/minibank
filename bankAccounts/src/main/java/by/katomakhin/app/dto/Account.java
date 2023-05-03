package by.katomakhin.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
public class Account {
    private String accnumber;
    private BigDecimal accvalue;
    private List<Transaction> transactions;
}
