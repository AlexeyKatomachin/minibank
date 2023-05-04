package by.katomakhin.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class SpecificUserAccountsResponseDto {
    private String userName;
    private List<UserAccInfoDto> userAccInfos;
}
