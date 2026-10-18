package com.example.poll_automation_api.repository;

import com.example.poll_automation_api.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollOptionRepository extends JpaRepository<PollOption,Long> {
}
