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
public class CreateAccountDto {
    private String userName;
    private int userPin;
    private BigDecimal accValue;
}
