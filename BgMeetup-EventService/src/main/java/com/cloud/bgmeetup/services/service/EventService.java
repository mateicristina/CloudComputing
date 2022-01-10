package com.cloud.bgmeetup.services.service;

import com.cloud.bgmeetup.services.dto.EventDto;
import com.cloud.bgmeetup.services.dto.EventParticipantDto;
import com.cloud.bgmeetup.services.exceptions.EntityNotFoundException;
import com.cloud.bgmeetup.services.mapper.EventMapper;
import com.cloud.bgmeetup.services.mapper.EventParticipantMapper;
import com.cloud.bgmeetup.services.model.Event;
import com.cloud.bgmeetup.services.model.EventParticipant;
import com.cloud.bgmeetup.services.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final EventParticipantMapper eventParticipantMapper;

    @Autowired
    public EventService(EventMapper eventMapper, EventRepository eventRepository, EventParticipantMapper eventParticipantMapper) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
        this.eventParticipantMapper = eventParticipantMapper;
    }

    public EventDto get(String id) {
        return eventRepository.get(id).orElseThrow(()-> new EntityNotFoundException("Event"));
    }

    public List<EventDto> getAll() {
        return eventRepository.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(EventDto request) {
        Event event = eventMapper.toEntity(request);
        eventRepository.save(event);
    }

    public void delete(String id) {
        eventRepository.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addEventParticipant(EventParticipantDto request) {
        EventParticipant participant = eventParticipantMapper.toEntity(request);
        eventRepository.addEventParticipant(participant);
    }

    public List<EventParticipantDto> getEventParticipants(String eventId) {
        return eventRepository.getEventParticipants(eventId);
    }
}
