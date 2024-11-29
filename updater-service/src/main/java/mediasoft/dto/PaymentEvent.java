package mediasoft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEvent {
    private Long id;
    private String accountNumber;
    private BigDecimal amount;
    private String okved;
    private String accountNumberReceiver;
    private String bankBIC;
    private LocalDate createAt;
}
