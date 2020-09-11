package com.example.datajpashop.repository;

import com.example.datajpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public List<Member> findByName(String name);

}
