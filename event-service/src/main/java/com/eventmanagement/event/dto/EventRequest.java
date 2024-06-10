package com.eventmanagement.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {
    private String id;
    private String name;
    private String description;
    private LocalDateTime beginningTime;
    private LocalDateTime finishingTime;
    private BigDecimal price;
}
