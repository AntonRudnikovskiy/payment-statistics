package mediasoft.mapper;

import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import mediasoft.dto.PaymentStatisticsDto;
import mediasoft.dto.StatisticsDto;
import mediasoft.dto.TransferDto;
import mediasoft.dto.UserRequestStatisticsDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentStatisticsMapper {
    public StatisticsDto toStatisticsDto(UserRequestStatisticsDto dto) {
        return StatisticsDto.builder()
                .userId(dto.getUserId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }

    public PaymentStatisticsDto mapBucketToStatisticsDto(StringTermsBucket bucket) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);

        if (bucket.aggregations().get("total_amount").isSum()) {
            totalAmount = BigDecimal.valueOf(bucket.aggregations().get("total_amount").sum().value());
        }

        return PaymentStatisticsDto.builder()
                .category(bucket.key().stringValue())
                .amount(totalAmount)
                .build();
    }

    public TransferDto mapBucketToTransferDto(double sum) {
        return TransferDto.builder()
                .totalTransferAmount(BigDecimal.valueOf(sum))
                .build();
    }
}
