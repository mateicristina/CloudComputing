package com.cloud.bgmeetup.services.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class GameDto {
    private UUID id;
    private String title;
    private String description;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer playingTime;
}
