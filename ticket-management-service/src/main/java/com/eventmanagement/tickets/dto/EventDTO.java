package com.eventmanagement.tickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private String id;
    private String name;
    private String description;
    private LocalDateTime beginningTime;
    private LocalDateTime finishingTime;
    private BigDecimal price;
}
