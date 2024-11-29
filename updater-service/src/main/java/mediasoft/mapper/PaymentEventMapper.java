package mediasoft.mapper;

import mediasoft.dto.PaymentEvent;
import mediasoft.entity.PaymentDoc;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentEventMapper {

    PaymentEvent toDto(PaymentDoc paymentDoc);

    PaymentDoc toEntity(PaymentEvent paymentEvent);
}
