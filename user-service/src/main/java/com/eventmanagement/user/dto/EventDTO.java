package com.eventmanagement.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventDTO {
    private String id;
    private String name;
    private String description;
    private LocalDateTime beginningTime;
    private LocalDateTime finishingTime;
    private BigDecimal price;
}
