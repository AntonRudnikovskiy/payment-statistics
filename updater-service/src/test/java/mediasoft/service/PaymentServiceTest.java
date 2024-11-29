//package mediasoft.service;
//
//import mediasoft.AbstractIntegrationTest;
//import mediasoft.dto.PaymentEvent;
//import mediasoft.entity.PaymentDoc;
//import mediasoft.repository.PaymentElasticsearchRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class PaymentServiceTest extends AbstractIntegrationTest {
//    @Autowired
//    private PaymentService paymentService;
//    @Autowired
//    private PaymentElasticsearchRepository repository;
//    private PaymentEvent paymentEvent;
//    private PaymentDoc paymentDoc;
//
//    @BeforeEach
//    void setUp() {
//        paymentEvent = PaymentEvent.builder()
//                .id(1L)
//                .accountNumber("4567536767")
//                .amount(BigDecimal.valueOf(350.0))
//                .okved("01")
//                .accountNumberReceiver("678965785")
//                .bankBIC("546543")
//                .createAt(LocalDate.now())
//                .build();
//
//        paymentDoc = PaymentDoc.builder()
//                .id(paymentEvent.getId())
//                .okvedCategory("Растениеводство и животноводство, охота и предоставление соответствующих " +
//                        "услуг в этих областях")
//                .amount(paymentEvent.getAmount())
//                .createAt(paymentEvent.getCreateAt())
//                .isInternalTransfer(false)
//                .build();
//    }
//
//    @Test
//    void processEvent() {
//        paymentService.savePaymentDoc(paymentEvent);
//
//        Optional<PaymentDoc> paymentDocById = repository.findById(paymentEvent.getId());
//        assertEquals(paymentDoc, paymentDocById.get());
//    }
//}