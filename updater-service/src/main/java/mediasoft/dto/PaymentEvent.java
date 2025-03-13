package mediasoft.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentEvent {
    private final Long id;
    private final String accountNumber;
    private final BigDecimal amount;
    private final String okved;
    private final String accountNumberReceiver;
    private final String bankBIC;
    private final LocalDate createAt;
}
