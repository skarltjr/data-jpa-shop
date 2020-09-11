package com.example.datajpashop.dto;

import com.example.datajpashop.domain.Address;
import com.example.datajpashop.domain.Member;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class MemberDto {
    private String name;
    private Address address;

    public MemberDto(Member member) {
        this.name=member.getName();
        this.address=member.getAddress();
    }
}
