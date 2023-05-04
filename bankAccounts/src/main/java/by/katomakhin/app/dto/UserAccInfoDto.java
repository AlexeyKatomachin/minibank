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
public class UserAccInfoDto {
    private String number;
    private BigDecimal value;
}
