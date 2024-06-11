package com.eventmanagement.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDTO {
    private String id;
    private String name;
    private String description;
    private LocalDateTime beginningTime;
    private LocalDateTime finishingTime;
    private BigDecimal price;
}
