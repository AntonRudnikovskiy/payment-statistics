package mediasoft.service;

import mediasoft.dto.PaymentEvent;

public interface EventProcessor {
    void savePaymentDoc(PaymentEvent paymentEvent);
}
