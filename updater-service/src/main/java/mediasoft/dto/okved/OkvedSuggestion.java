package mediasoft.dto.okved;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OkvedSuggestion {
    @JsonProperty("suggestions")
    private List<Suggestion> suggestions;
}
