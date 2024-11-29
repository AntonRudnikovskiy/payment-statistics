package mediasoft.dto.okved;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suggestion {
    private String value;
    @JsonProperty("unrestricted_value")
    private String unrestrictedValue;
    private OkvedData data;
}
