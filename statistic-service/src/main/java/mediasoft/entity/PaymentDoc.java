package mediasoft.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "payments")
public class PaymentDoc {
    @Id
    private Long id;

    @Field(value = "category")
    private String okvedCategory;

    @Field(value = "amount")
    private BigDecimal amount;

    @Field(type = FieldType.Date, format = DateFormat.basic_date, pattern = "yyyy-MM-dd")
    private LocalDate createAt;

    @Field(value = "isInternalTransfer")
    private boolean isInternalTransfer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDoc that = (PaymentDoc) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
