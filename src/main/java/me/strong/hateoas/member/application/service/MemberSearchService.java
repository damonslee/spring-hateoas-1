package me.strong.hateoas.member.application.service;

import me.strong.hateoas.member.domain.model.Member;
import me.strong.hateoas.member.domain.repository.MemberRepository;
import me.strong.hateoas.member.interfaces.model.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author taesu
 * @version 1.0
 * @since 1.0
 */
@Service
public class MemberSearchService {
    private MemberRepository memberRepository;

    public MemberSearchService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public Page<MemberDto.MemberSearchResponse> searchMember(Pageable pageable) {
        return this.memberRepository.findAll(pageable)
                .map(MemberDto::asSearchResponse);
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberSearchResponse searchMember(Long memberKey) {
        return MemberDto.asSearchResponse(this.memberRepository.findById(memberKey)
                                                  .orElseGet(Member::new));
    }

}
