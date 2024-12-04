package mediasoft;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UpdaterServiceApplication.class)
public abstract class AbstractIntegrationTest {
    @Container
    private static ElasticsearchContainer ELASTICSEARCH_CONTAINER = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.17.10")
            .withEnv("discovery.type", "single-node");

    @Test
    void contextLoads() {
        assertTrue(ELASTICSEARCH_CONTAINER.isRunning());
    }
}
