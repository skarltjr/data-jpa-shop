package com.example.datajpashop.service;

import com.example.datajpashop.domain.Member;
import com.example.datajpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    @Transactional
    public Long join(Member member)
    {
        duplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void duplicate(Member member) {
        List<Member> byName = memberRepository.findByName(member.getName());
        if(!byName.isEmpty())
        {
            throw new IllegalStateException("이름 중복");
        }
    }

    //전체 회원조회
    public List<Member> findAll()
    {
        return memberRepository.findAll();
    }

    //단건조회
    public Member findOne(Long id) {
        return memberRepository.findById(id).get();
    }

    //update
    public void update(Long id,String name)
    {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }

}
