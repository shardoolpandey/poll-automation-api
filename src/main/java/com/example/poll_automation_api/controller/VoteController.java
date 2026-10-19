package com.example.poll_automation_api.controller;

import com.example.poll_automation_api.dto.CastVoteRequestDto;
import com.example.poll_automation_api.entity.Vote;
import com.example.poll_automation_api.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public Vote castVote(
            @Valid @RequestBody CastVoteRequestDto request) {
        return voteService.castVote(request.getOptionId());
    }
}
