//package mediasoft.service;
//
//import mediasoft.AbstractIntegrationTest;
//import mediasoft.dto.CategoryDto;
//import mediasoft.dto.PaymentStatisticsDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//class PaymentStatisticsServiceTest extends AbstractIntegrationTest {
//    @Autowired
//    private PaymentStatisticsServiceImpl paymentStatisticsService;
//
//    private final List<PaymentStatisticsDto> EXPECTED_ITEMS_SAME_DATE = List.of(
//            PaymentStatisticsDto.builder()
//                    .userId(1L)
//                    .categories(List.of(CategoryDto.builder()
//                            .amount(3900)
//                            .category("Растениеводство и животноводство, охота и предоставление соответствующих " +
//                                    "услуг в этих областях")
//                            .build()))
//                    .build(),
//            PaymentStatisticsDto.builder()
//                    .userId(1L)
//                    .categories(List.of(CategoryDto.builder()
//                            .amount(25555)
//                            .category("Торговля автотранспортными средствами")
//                            .build()))
//                    .build(),
//            PaymentStatisticsDto.builder()
//                    .userId(1L)
//                    .categories(List.of(CategoryDto.builder()
//                            .amount(50000)
//                            .category("Строительство")
//                            .build()))
//                    .build()
//    );
//
//    private final List<PaymentStatisticsDto> EXPECTED_ITEMS_DIFFERENT_DATE = List.of(
//            PaymentStatisticsDto.builder()
//                    .userId(2L)
//                    .categories(List.of(CategoryDto.builder()
//                            .amount(22000)
//                            .category("Продукты")
//                            .build()))
//                    .build(),
//            PaymentStatisticsDto.builder()
//                    .userId(2L)
//                    .categories(List.of(CategoryDto.builder()
//                            .amount(1500)
//                            .category("Интернет")
//                            .build()))
//                    .build(),
//            PaymentStatisticsDto.builder()
//                    .userId(2L)
//                    .categories(List.of(CategoryDto.builder()
//                            .amount(40000)
//                            .category("Техника")
//                            .build()))
//                    .build()
//    );
//
//    @Test
//    void getAggregatedPaymentStatistics_SameDateRange() {
//        List<PaymentStatisticsDto> statistics = paymentStatisticsService
//                .getPaymentStatistics(1L, LocalDate.of(2024, 11, 6),
//                        LocalDate.of(2024, 11, 6));
//
//        assertThat(statistics).containsAll(EXPECTED_ITEMS_SAME_DATE);
//    }
//
//    @Test
//    void getAggregatedPaymentStatistics_DifferentDateRange() {
//        List<PaymentStatisticsDto> statistics = paymentStatisticsService
//                .getPaymentStatistics(2L, LocalDate.of(2024, 11, 7),
//                        LocalDate.of(2024, 11, 9));
//
//        assertThat(statistics).containsAll(EXPECTED_ITEMS_DIFFERENT_DATE);
//    }
//}