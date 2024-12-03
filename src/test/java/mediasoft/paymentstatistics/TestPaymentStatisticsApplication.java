package mediasoft.paymentstatistics;

import org.springframework.boot.SpringApplication;

public class TestPaymentStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaymentStatisticsApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
