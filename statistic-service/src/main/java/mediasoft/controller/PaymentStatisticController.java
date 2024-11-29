package mediasoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mediasoft.dto.PaymentStatisticsDto;
import mediasoft.dto.StatisticsDto;
import mediasoft.dto.TransferDto;
import mediasoft.dto.UserRequestStatisticsDto;
import mediasoft.mapper.PaymentStatisticsMapper;
import mediasoft.service.PaymentStatisticsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistic")
public class PaymentStatisticController {
    private final PaymentStatisticsServiceImpl paymentStatisticsService;
    private final PaymentStatisticsMapper paymentStatisticsMapper;

    @GetMapping("/")
    public ResponseEntity<List<PaymentStatisticsDto>> getPaymentStatistics(@RequestBody @Valid UserRequestStatisticsDto dto) {
        StatisticsDto statisticsDto = paymentStatisticsMapper.toStatisticsDto(dto);
        return ResponseEntity.ok(paymentStatisticsService.getPaymentStatistics(statisticsDto));
    }

    @GetMapping("/{client}")
    public ResponseEntity<TransferDto> getTransferStatistics(@PathVariable Long client) {
        return ResponseEntity.ok(paymentStatisticsService.getTransfersStatistics(client));
    }
}
