package mediasoft.dto.okved;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OkvedSuggestion {
    @JsonProperty("suggestions")
    private final List<Suggestion> suggestions;
}
