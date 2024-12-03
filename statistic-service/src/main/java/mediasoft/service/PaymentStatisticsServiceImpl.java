package mediasoft.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediasoft.dto.PaymentStatisticsDto;
import mediasoft.dto.StatisticsDto;
import mediasoft.dto.TransferDto;
import mediasoft.entity.PaymentDoc;
import mediasoft.exception.EntityNotFoundException;
import mediasoft.mapper.PaymentStatisticsMapper;
import mediasoft.repository.ElasticsearchClientRepository;
import mediasoft.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentStatisticsServiceImpl implements PaymentStatisticsService {
    private final PaymentRepository paymentRepository;
    private final PaymentStatisticsMapper statisticsMapper;
    private final ElasticsearchClientRepository clientRepository;

    @Override
    public List<PaymentStatisticsDto> getPaymentStatistics(Long id, LocalDate startDate, LocalDate endDate) {
        return clientRepository.getPaymentStatsByIdAndDateRange(id, startDate, endDate)
                .aggregations()
                .get("category")
                .sterms()
                .buckets()
                .array()
                .stream()
                .map(statisticsMapper::mapBucketToStatisticsDto)
                .toList();
    }

    @Override
    public TransferDto getTransfersStatistics(Long id) {
        PaymentDoc paymentDoc = paymentRepository.findByUserIdAndCategoryIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return Optional.of(clientRepository.getTransfersStatsById(paymentDoc.getId())
                .aggregations()
                .get("total_transfer_amount")
                .sum()
                .value())
                .map(statisticsMapper::mapBucketToTransferDto)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
    }
}
