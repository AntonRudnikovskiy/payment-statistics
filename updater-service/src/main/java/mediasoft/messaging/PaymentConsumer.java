package mediasoft.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediasoft.dto.PaymentEvent;
import mediasoft.service.PaymentService;
import mediasoft.utils.JsonMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentService paymentService;
    private final JsonMapper jsonMapper;

    @KafkaListener(
            topics = "payment-events",
            groupId = "payment-events-listener-group"
    )
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
        try {
            log.info("Message received: {}", consumerRecord.value());
            jsonMapper.readValue(consumerRecord.value(), PaymentEvent.class)
                    .ifPresent(paymentService::savePaymentDoc);
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage());
        }
    }
}
