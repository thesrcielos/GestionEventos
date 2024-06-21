package com.eventmanagement.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String access_token;

    //@JsonProperty("expires_in")
    private int expires_in;

    //@JsonProperty("refresh_expires_in")
    private int refresh_expires_in;

    //@JsonProperty("refresh_token")
    private String refresh_token;

    //@JsonProperty("token_type")
    private String token_type;

    //@JsonProperty("not-before-policy")
    private int not_before_policy;

    //@JsonProperty("session_state")
    private String session_state;

    private String scope;
}
