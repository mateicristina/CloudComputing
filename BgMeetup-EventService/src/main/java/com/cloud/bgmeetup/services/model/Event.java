package com.cloud.bgmeetup.services.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {
    private UUID id;
    private UUID hostId;
    private String title;
    private String location;
    private String date;
}
