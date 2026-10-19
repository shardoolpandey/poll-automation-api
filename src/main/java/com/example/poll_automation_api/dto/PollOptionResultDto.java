package com.example.poll_automation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PollOptionResultDto {
    private Long optionId;
    private String optionText;
    private long voteCount;
}
