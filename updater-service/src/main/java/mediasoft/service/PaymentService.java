package mediasoft.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediasoft.dto.PaymentEvent;
import mediasoft.entity.PaymentDoc;
import mediasoft.exceptions.OKVEDNotFound;
import mediasoft.mapper.PaymentEventMapper;
import mediasoft.repository.PaymentElasticsearchRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService implements EventProcessor {
    private final PaymentElasticsearchRepository paymentElasticsearchRepository;
    private final OkvedDescriptionCacheService okvedDescriptionCacheService;
    private final PaymentEventMapper paymentEventMapper;
    @Value("${bank.biks}")
    private Set<String> biks;

    public void savePaymentDoc(PaymentEvent paymentEvent) {
        try {
            log.info("PaymentEvent with id: {} was received", paymentEvent.getId());
            PaymentDoc paymentDoc = paymentEventMapper.toEntity(paymentEvent);
            if (paymentDoc.getOkvedCategory().isEmpty()) {
                if (biks.contains(paymentEvent.getBankBIC())) {
                    paymentDoc.setInternalTransfer(true);
                }
            } else {
                String okved = okvedDescriptionCacheService.getOkvedDescription(paymentEvent.getOkved());
                paymentDoc.setOkvedCategory(okved);
            }
            paymentDoc.setCreateAt(LocalDate.now());
            paymentElasticsearchRepository.save(paymentDoc);
            log.info("PaymentDoc with id: {} was saved", paymentDoc.getId());
        } catch (Exception e) {
            log.error("Error occurred {} while processing event with id: {}", e.getMessage(), paymentEvent.getId());
            throw new RuntimeException(e.getMessage());
        }
    }
}
