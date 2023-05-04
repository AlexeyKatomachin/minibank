package by.katomakhin.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
public class TransferDto {
    private String userName;
    private int userPin;
    private String accNumberFrom;
    private String accNumberTo;
    private BigDecimal value;
}
