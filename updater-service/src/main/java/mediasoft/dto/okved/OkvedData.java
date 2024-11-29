package mediasoft.dto.okved;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OkvedData {
    private String idx;
    private String razdel;
    private String code;
    private String name;
}
