package com.example.poll_automation_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreatePollRequestDto {
    @NotBlank(message = "Question cannot be blank")
    private String question;
    @NotNull(message = "start time is required")
    private LocalDateTime startTime;
    @NotNull(message = "end time is required")
    private LocalDateTime endTime;
    @Size(min = 2, message = "A poll must have at least two options")
    private List<@NotBlank(message = "Poll options cannot be blank") String> options;

}
