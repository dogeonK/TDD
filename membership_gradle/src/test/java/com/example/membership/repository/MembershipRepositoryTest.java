package com.example.membership.repository;

import com.example.membership.MembershipType;
import com.example.membership.domain.Membership;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    @DisplayName("널아님")
    public void MembershipRepositoryIsNotNULL(){

        assertThat(membershipRepository).isNotNull();
    }

    @Test
    @DisplayName("멤버십등록")
    public void MembershipAccount(){
        //given
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        //when
        final Membership result = membershipRepository.save(membership);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(10000);
    }

    @Test
    @DisplayName("멤버십존재하는지")
    public void MembershipIsExsit() {
        // given
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        // when
        membershipRepository.save(membership);
        final Membership findResult = membershipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

        // then
        assertThat(findResult).isNotNull();
        assertThat(findResult.getId()).isNotNull();
        assertThat(findResult.getUserId()).isEqualTo("userId");
        assertThat(findResult.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(findResult.getPoint()).isEqualTo(10000);
    }

    @Test
    public void 멤버십조회_사이즈가0() {
        // given

        // when
        List<Membership> result = membershipRepository.findAllByUserId("userId");

        // then
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void 멤버십조회_사이즈가2() {
        // given
        final Membership naverMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        final Membership kakaoMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.KAKAO)
                .point(10000)
                .build();

        membershipRepository.save(naverMembership);
        membershipRepository.save(kakaoMembership);

        // when
        List<Membership> result = membershipRepository.findAllByUserId("userId");

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
