package com.example.poll_automation_api.service;

import com.example.poll_automation_api.entity.Poll;
import com.example.poll_automation_api.entity.PollOption;
import com.example.poll_automation_api.entity.PollStatus;
import com.example.poll_automation_api.entity.Vote;
import com.example.poll_automation_api.exception.ResourceNotFoundException;
import com.example.poll_automation_api.repository.PollOptionRepository;
import com.example.poll_automation_api.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
    @RequiredArgsConstructor
    public class VoteService {

        private final VoteRepository voteRepository;
        private final PollOptionRepository pollOptionRepository;


        public Vote castVote(Long optionId) {
            PollOption option = pollOptionRepository.findById(optionId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Poll option not found with id: " + optionId));

            Poll poll = option.getPoll();

            LocalDateTime now = LocalDateTime.now();

            if (now.isBefore(poll.getStartTime())
                    || !now.isBefore(poll.getEndTime())) {
                throw new IllegalArgumentException(
                        "Voting is allowed only while the poll is active");
            }

            poll.setStatus(PollStatus.ACTIVE);

            Vote vote = new Vote();
            vote.setOption(option);

            return voteRepository.save(vote);
        }

    }

