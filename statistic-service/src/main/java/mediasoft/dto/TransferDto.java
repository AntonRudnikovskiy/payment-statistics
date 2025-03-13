package mediasoft.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDto {
    private BigDecimal totalTransferAmount;
}
