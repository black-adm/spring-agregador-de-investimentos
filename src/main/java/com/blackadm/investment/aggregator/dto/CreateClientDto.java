package com.blackadm.investment.aggregator.dto;

public record CreateClientDto(
        String nickname,
        String email,
        String password
) {
}
