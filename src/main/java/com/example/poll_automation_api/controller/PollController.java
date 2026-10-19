package com.example.poll_automation_api.controller;

import com.example.poll_automation_api.dto.CreatePollRequestDto;
import com.example.poll_automation_api.dto.PollOptionResultDto;
import com.example.poll_automation_api.entity.Poll;
import com.example.poll_automation_api.service.PollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls")
@RequiredArgsConstructor
public class PollController {
    private final PollService pollService;
    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }
    @GetMapping("/{id}")
    public Poll getPollById(@PathVariable Long id) {
        return pollService.getPollById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Poll createPoll(@Valid @RequestBody CreatePollRequestDto request)
    {
        return pollService.createPoll(request);
    }

    @PutMapping("/{id}")
    public Poll updatePoll(
            @PathVariable Long id,
            @Valid @RequestBody CreatePollRequestDto request) {
        return pollService.updatePoll(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/results")
    public List<PollOptionResultDto> getPollResults(@PathVariable Long id) {
        return pollService.getPollResults(id);
    }


}
