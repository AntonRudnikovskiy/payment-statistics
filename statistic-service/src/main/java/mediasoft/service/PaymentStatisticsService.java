package mediasoft.service;

import mediasoft.dto.PaymentStatisticsDto;
import mediasoft.dto.StatisticsDto;
import mediasoft.dto.TransferDto;

import java.util.List;

public interface PaymentStatisticsService {
    List<PaymentStatisticsDto> getPaymentStatistics(StatisticsDto dto);

    TransferDto getTransfersStatistics(Long userId);
}
