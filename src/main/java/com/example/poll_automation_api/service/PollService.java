package com.example.poll_automation_api.service;

import com.example.poll_automation_api.dto.CreatePollRequestDto;
import com.example.poll_automation_api.dto.PollOptionResultDto;
import com.example.poll_automation_api.entity.Poll;
import com.example.poll_automation_api.entity.PollOption;
import com.example.poll_automation_api.entity.PollStatus;
import com.example.poll_automation_api.exception.ResourceNotFoundException;
import com.example.poll_automation_api.repository.PollRepository;
import com.example.poll_automation_api.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PollService {
    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;
    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }
    public Poll getPollById(Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Poll not found with id: " + id));
    }
    public Poll createPoll(CreatePollRequestDto request) {

        if(!request.getStartTime().isAfter(LocalDateTime.now()))
        {
            throw new IllegalArgumentException("Start time must be in the future");
        }
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new IllegalArgumentException(
                    "End time must be after start time"
            );
        }
        Poll poll = new Poll();

        poll.setQuestion(request.getQuestion());
        poll.setStartTime(request.getStartTime());
        poll.setEndTime(request.getEndTime());
        poll.setCreatedAt(LocalDateTime.now());

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(request.getStartTime())) {
            poll.setStatus(PollStatus.UPCOMING);
        } else if (now.isAfter(request.getEndTime())) {
            poll.setStatus(PollStatus.COMPLETED);
        } else {
            poll.setStatus(PollStatus.ACTIVE);
        }

        List<PollOption> options = new ArrayList<>();

        for (String optionText : request.getOptions()) {
            PollOption option = new PollOption();
            option.setOptionText(optionText);
            option.setPoll(poll);

            options.add(option);
        }

        poll.setOptions(options);

        return pollRepository.save(poll);
    }
    public Poll updatePoll(Long id,CreatePollRequestDto request)
    {
        Poll poll=getPollById(id);
        if(!request.getStartTime().isAfter(LocalDateTime.now()))
        {
            throw new IllegalArgumentException(
                    "Start time must be in the future"
            );
        }
        if(!request.getEndTime().isAfter(request.getStartTime()))
        {
            throw new IllegalArgumentException(
                    "End time must be after start time"
            );
        }
        poll.setQuestion(request.getQuestion());
        poll.setStartTime(request.getStartTime());
        poll.setEndTime(request.getEndTime());

        List<PollOption> options=new ArrayList<>();
        for(String optionText : request.getOptions())
        {
            PollOption option=new PollOption();
            option.setOptionText(optionText);
            option.setPoll(poll);
            options.add(option);
        }
        poll.getOptions().clear();
        poll.getOptions().addAll(options);
        LocalDateTime now=LocalDateTime.now();
        if(now.isBefore(request.getStartTime()))
        {
            poll.setStatus(PollStatus.UPCOMING);
        }
        else if(now.isAfter(request.getEndTime()))
        {
            poll.setStatus(PollStatus.COMPLETED);
        }
        else
        {
            poll.setStatus(PollStatus.ACTIVE);
        }
        return pollRepository.save(poll);
    }
    public void deletePoll(Long id) {
        Poll poll = getPollById(id);
        pollRepository.delete(poll);
    }
    public List<PollOptionResultDto> getPollResults(Long pollId) {
        Poll poll = getPollById(pollId);

        List<PollOptionResultDto> results = new ArrayList<>();

        for (PollOption option : poll.getOptions()) {
            long voteCount =
                    voteRepository.countVotesByOptionId(option.getId());

            PollOptionResultDto result = new PollOptionResultDto(
                    option.getId(),
                    option.getOptionText(),
                    voteCount
            );

            results.add(result);
        }

        return results;
    }
}
