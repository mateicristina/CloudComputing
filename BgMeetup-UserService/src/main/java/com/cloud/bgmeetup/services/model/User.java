package com.cloud.bgmeetup.services.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String location;
}
