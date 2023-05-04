package by.katomakhin.app.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class SpecificAccountTransactionsRequestDto {
    private String userName;
    private int userPin;
    private String accNumber;
}
