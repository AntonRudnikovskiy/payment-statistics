package mediasoft.controller;

import lombok.RequiredArgsConstructor;
import mediasoft.dto.PaymentStatisticsDto;
import mediasoft.dto.TransferDto;
import mediasoft.service.PaymentStatisticsServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class PaymentStatisticController {
    private final PaymentStatisticsServiceImpl paymentStatisticsService;

    @GetMapping("/statistic")
    public ResponseEntity<List<PaymentStatisticsDto>> getPaymentStatistics(
            @RequestParam Long clientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(paymentStatisticsService.getPaymentStatistics(clientId, startDate, endDate));
    }

    @GetMapping("/statistic/{clientId}")
    public ResponseEntity<TransferDto> getTransferStatistics(@PathVariable Long clientId) {
        return ResponseEntity.ok(paymentStatisticsService.getTransfersStatistics(clientId));
    }
}
