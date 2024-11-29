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
    public List<PaymentStatisticsDto> getPaymentStatistics(StatisticsDto dto) {
        return clientRepository.getPaymentStatsByIdAndDateRange(dto.getUserId(), dto.getStartDate(), dto.getEndDate())
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
                .orElseThrow(() -> new EntityNotFoundException("Document nof found"));
    }
}
