package mediasoft.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediasoft.exception.AggregationException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ElasticsearchClientRepository {
    private final ElasticsearchClient elasticsearchClient;

    public SearchResponse<ObjectNode> getPaymentStatsByIdAndDateRange(Long id, LocalDate startDate, LocalDate endDate) {
        try {
            SearchRequest request = new SearchRequest.Builder()
                    .index("payments")
                    .size(0)
                    .query(Query.of(queryBuilder -> queryBuilder
                            .bool(boolBuilder -> boolBuilder
                                    .must(MatchQuery.of(matchBuilder -> matchBuilder
                                            .field("id")
                                            .query(id)
                                    )._toQuery())
                                    .filter(RangeQuery.of(rangeBuilder -> rangeBuilder
                                            .date(dateBuilder -> dateBuilder
                                                    .field("paymentDate")
                                                    .gte(String.valueOf(startDate))
                                                    .lte(String.valueOf(endDate))
                                            )
                                    )._toQuery())
                            )
                    ))
                    .aggregations("category", agBuilder -> agBuilder
                            .terms(termsBuilder -> termsBuilder.field("category"))
                            .aggregations("total_amount", sa -> sa
                                    .sum(sumBuilder -> sumBuilder.field("amount"))
                            )
                    )
                    .build();
            return elasticsearchClient.search(request, ObjectNode.class);
        } catch (Exception e) {
            log.error("Error occurred with message: {}, while processing query", e.getMessage());
            throw new AggregationException(e.getMessage());
        }
    }

    public SearchResponse<ObjectNode> getTransfersStatsById(Long id) {
        try {
            SearchRequest request = new SearchRequest.Builder()
                    .index("payments")
                    .size(0)
                    .query(Query.of(queryBuilder -> queryBuilder
                            .bool(boolBuilder -> boolBuilder
                                    .must(MatchQuery.of(matchBuilder -> matchBuilder
                                                    .field("id")
                                                    .query(id)
                                            )._toQuery()
                                    ).mustNot(mustBuilder -> mustBuilder
                                            .term(termsBuilder -> termsBuilder
                                                    .field("isInternalTransfer")
                                                    .value(true)
                                            ))
                            )
                    )).aggregations("total_transfer_amount", sa -> sa
                            .sum(sumBuilder -> sumBuilder.field("amount"))
                    ).build();
            return elasticsearchClient.search(request, ObjectNode.class);
        } catch (Exception e) {
            log.error("Error occurred with message: {}, while processing query", e.getMessage());
            throw new AggregationException(e.getMessage());
        }
    }
}
