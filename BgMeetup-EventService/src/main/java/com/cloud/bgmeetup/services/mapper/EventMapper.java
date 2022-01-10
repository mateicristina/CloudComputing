package com.cloud.bgmeetup.services.mapper;

import com.cloud.bgmeetup.services.dto.EventDto;
import com.cloud.bgmeetup.services.dto.EventParticipantDto;
import com.cloud.bgmeetup.services.model.Event;
import com.cloud.bgmeetup.services.model.EventParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "hostId", source = "dto.hostId"),
            @Mapping(target = "title", source = "dto.title"),
            @Mapping(target = "location", source = "dto.location"),
            @Mapping(target = "date", source = "dto.date")
    })
    Event toEntity(EventDto dto);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "hostId", source = "entity.hostId"),
            @Mapping(target = "title", source = "entity.title"),
            @Mapping(target = "location", source = "entity.location"),
            @Mapping(target = "date", source = "entity.date")
    })
    EventDto toDto(Event entity);
}