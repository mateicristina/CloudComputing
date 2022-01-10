package com.cloud.bgmeetup.services.mapper;

import com.cloud.bgmeetup.services.dto.EventParticipantDto;
import com.cloud.bgmeetup.services.model.EventParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventParticipantMapper {

    @Mappings({
            @Mapping(target = "eventId", source = "dto.eventId"),
            @Mapping(target = "participantId", source = "dto.participantId")
    })
    EventParticipant toEntity(EventParticipantDto dto);

    @Mappings({
            @Mapping(target = "eventId", source = "entity.eventId"),
            @Mapping(target = "participantId", source = "entity.participantId")
    })
    EventParticipantDto toEntity(EventParticipant entity);
}
