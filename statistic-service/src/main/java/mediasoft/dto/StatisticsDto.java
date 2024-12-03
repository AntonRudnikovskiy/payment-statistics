package mediasoft.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsDto {
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
