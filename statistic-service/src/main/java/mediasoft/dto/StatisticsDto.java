package mediasoft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsDto {
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
