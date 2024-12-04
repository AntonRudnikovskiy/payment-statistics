package mediasoft.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonMapper {
    private final ObjectMapper objectMapper;

    public <T> Optional<T> readValue(String json, Class<T> clazz) {
        try {
            return Optional.ofNullable(objectMapper.readValue(json, clazz));
        } catch (IOException ex) {
            log.error("Error deserializing JSON to {}: {}", clazz.getName(), ex.getMessage());
            return Optional.empty();
        }
    }
}
