package mediasoft.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentStatisticsDto {
    private String category;
    private BigDecimal amount;
}
