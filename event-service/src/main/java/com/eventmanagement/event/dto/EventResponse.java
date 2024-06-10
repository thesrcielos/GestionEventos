package com.eventmanagement.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EventResponse {
    private String id;
    private String name;
    private String description;
    private LocalDateTime beginningTime;
    private LocalDateTime finishingTime;
    private BigDecimal price;
}
