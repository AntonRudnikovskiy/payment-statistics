package mediasoft.repository;

import mediasoft.entity.PaymentDoc;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
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
    Optional<PaymentDoc> findByUserIdAndCategoryIsNull(Long clientId);
}
