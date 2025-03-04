package com.example.eventservice.service;

import com.example.eventservice.mapper.EventMapper;
import com.example.eventservice.model.Event;
import com.example.shared.dto.EventDto;
import com.example.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public Event save(EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(Long eventId) {
        return eventRepository.findById(eventId);
    }
}
