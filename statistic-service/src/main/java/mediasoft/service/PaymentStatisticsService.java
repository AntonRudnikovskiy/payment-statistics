package mediasoft.service;

import mediasoft.dto.PaymentStatisticsDto;
import mediasoft.dto.TransferDto;

import java.time.LocalDate;
import java.util.List;

public interface PaymentStatisticsService {
    List<PaymentStatisticsDto> getPaymentStatistics(Long id, LocalDate startDate, LocalDate endDate);

    TransferDto getTransfersStatistics(Long userId);
}
