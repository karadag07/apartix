package com.apartix.demo.controller;

import com.apartix.demo.dto.VoteRequest;
import com.apartix.demo.entity.Poll;
import com.apartix.demo.entity.PollOption;
import com.apartix.demo.entity.Vote;
import com.apartix.demo.repository.PollOptionRepository;
import com.apartix.demo.repository.PollRepository;
import com.apartix.demo.repository.VoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "*")
public class VoteController {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private final VoteRepository voteRepository;

    public VoteController(
            PollRepository pollRepository,
            PollOptionRepository pollOptionRepository,
            VoteRepository voteRepository) {

        this.pollRepository = pollRepository;
        this.pollOptionRepository = pollOptionRepository;
        this.voteRepository = voteRepository;
    }

    @GetMapping
    public List<Poll> getPolls(@RequestParam Integer siteId) {
        return pollRepository.findBySiteId(siteId);
    }

    @GetMapping("/{pollId}/options")
    public List<PollOption> getPollOptions(@PathVariable Integer pollId) {
        return pollOptionRepository.findByPollId(pollId);
    }

    @GetMapping("/{pollId}/votes")
    public List<Vote> getVotes(@PathVariable Integer pollId) {
        return voteRepository.findByPollId(pollId);
    }

    @PostMapping("/{pollId}/vote")
public Vote vote(
        @PathVariable Integer pollId,
        @RequestBody VoteRequest request) {

    boolean alreadyVoted =
            voteRepository.existsByPollIdAndUserId(
                    pollId,
                    request.getUserId());

    if (alreadyVoted) {
        throw new RuntimeException("Bu kullanıcı zaten oy kullanmış.");
    }

    Vote vote = new Vote();

    vote.setPollId(pollId);
    vote.setOptionId(request.getOptionId());
    vote.setUserId(request.getUserId());
    vote.setCreatedAt(
            new java.sql.Timestamp(System.currentTimeMillis()));

    return voteRepository.save(vote);
}
}