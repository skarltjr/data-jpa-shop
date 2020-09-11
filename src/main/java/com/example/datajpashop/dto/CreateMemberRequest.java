package com.example.datajpashop.dto;

import lombok.Data;

@Data
public class CreateMemberRequest {
    private String name;

    public CreateMemberRequest(String name) {
        this.name = name;
    }
}
