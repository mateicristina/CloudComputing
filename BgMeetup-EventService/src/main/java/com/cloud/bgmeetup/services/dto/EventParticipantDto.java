package com.cloud.bgmeetup.services.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class EventParticipantDto {
    private UUID eventId;
    private UUID participantId;
    private String participantName;
}
