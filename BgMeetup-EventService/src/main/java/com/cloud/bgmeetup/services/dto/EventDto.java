package com.cloud.bgmeetup.services.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class EventDto {
    private UUID id;
    private UUID hostId;
    private String hostName;
    private String title;
    private String location;
    private String date;
}
