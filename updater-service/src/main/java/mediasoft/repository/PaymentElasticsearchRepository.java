package mediasoft.repository;

import mediasoft.entity.PaymentDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaymentElasticsearchRepository extends ElasticsearchRepository<PaymentDoc, Long> {
}
