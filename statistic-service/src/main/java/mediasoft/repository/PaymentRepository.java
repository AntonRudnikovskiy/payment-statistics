package mediasoft.repository;

import mediasoft.entity.PaymentDoc;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface PaymentRepository extends ElasticsearchRepository<PaymentDoc, Long> {

    @Query("""
    {
      "bool": {
        "must": [
          { "term": { "id": "?0" } }
        ],
        "must_not": [
          { "exists": { "field": "category" } }
        ]
      }
    }
    """)
    Optional<PaymentDoc> findByUserIdAndCategoryIsNull(Long userId);
}
