package com.eventmanagement.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestUpdate {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
