package com.example.poll_automation_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CastVoteRequestDto {
    @NotNull(message = "Option ID is required")
    @Positive(message = "Option ID must be positive")
    private Long optionId;
}
