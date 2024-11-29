package mediasoft.service;

import lombok.RequiredArgsConstructor;
import mediasoft.client.OKVEDClient;
import mediasoft.dto.okved.Suggestion;
import mediasoft.exceptions.OKVEDNotFound;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OkvedDescriptionCacheService {
    private final OKVEDClient okvedClient;

    @Cacheable(value = "okvedDescriptions", key = "#code")
    public String getOkvedDescription(String code) {
        return okvedClient.findById(code).getSuggestions().stream()
                .map(Suggestion::getValue)
                .findFirst()
                .orElseThrow(() -> new OKVEDNotFound("okved not found"));
    }
}
