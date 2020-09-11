package com.example.datajpashop.apicontroller;

import com.example.datajpashop.domain.Member;
import com.example.datajpashop.dto.CreateMemberRequest;
import com.example.datajpashop.dto.CreateMemberResponse;
import com.example.datajpashop.dto.MemberDto;
import com.example.datajpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberDto> members()
    {
        List<Member> members = memberService.findAll();
        return members.stream().map(m->new MemberDto(m))
                .collect(Collectors.toList());
    }

    @PostMapping("/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request)
    {
        Member member =new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/members/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") Long id,
                                             @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id,request.getName());
        Member one = memberService.findOne(id);

        return new UpdateMemberResponse(one.getId(), one.getName());
    }
    //수정 DTO
    @Data
    static class UpdateMemberRequest
    {
        private String name;
    }
    @Data
    static class UpdateMemberResponse
    {
        private Long id;
        private String name;

        public UpdateMemberResponse(Long id, String name) {
            this.id=id;
            this.name=name;
        }
    }
}
