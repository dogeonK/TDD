package com.example.membership;

import lombok.Builder;
import lombok.Getter;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipDetailResponse {
    private final Long id;
    private final MembershipType membershipType;
    private final Integer point;
    private final LocalDateTime createdAt;
}
