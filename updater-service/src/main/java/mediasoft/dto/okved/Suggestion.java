package mediasoft.dto.okved;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Suggestion {
    private final String value;
    @JsonProperty("unrestricted_value")
    private final String unrestrictedValue;
    private final OkvedData data;
}
