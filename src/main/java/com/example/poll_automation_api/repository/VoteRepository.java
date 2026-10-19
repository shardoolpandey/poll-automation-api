package com.example.poll_automation_api.repository;

import com.example.poll_automation_api.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    @Query("SELECT COUNT(v) FROM Vote v WHERE v.option.id = :optionId")
    long countVotesByOptionId(@Param("optionId") Long optionId);
}

