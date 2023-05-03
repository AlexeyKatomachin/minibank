package by.katomakhin.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
public class Transaction {
    private Date time;
    private BigDecimal trvalue;
}
