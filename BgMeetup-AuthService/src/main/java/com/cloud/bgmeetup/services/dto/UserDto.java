package com.cloud.bgmeetup.services.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String location;
    private byte[] passwordSalt;
    private byte[] passwordHash;
}
