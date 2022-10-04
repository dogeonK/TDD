package com.example.membership.repository;

import com.example.membership.MembershipType;
import com.example.membership.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Membership findByUserIdAndMembershipType(final String userId, final MembershipType membershipType);
}
