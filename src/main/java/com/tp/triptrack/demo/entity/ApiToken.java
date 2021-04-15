package com.tp.triptrack.demo.entity;

import lombok.Data;

@Data
public class ApiToken {
    public String type ;
    public String username ;
    public String application_name ;
    public String client_id ;
    public String token_type ;
    public String access_token ;
    public String expires_in ;
    public String state ;
    public String scope ;
}
