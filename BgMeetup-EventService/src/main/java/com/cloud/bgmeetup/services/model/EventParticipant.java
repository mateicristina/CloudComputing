package com.cloud.bgmeetup.services.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class EventParticipant {
    private UUID eventId;
    private UUID participantId;
}
