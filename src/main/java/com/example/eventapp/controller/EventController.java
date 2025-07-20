package com.example.eventapp.controller;
import com.example.eventapp.dto.EventDto;
import com.example.eventapp.entity.Event;
import com.example.eventapp.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) { this.eventService = eventService; }

    @GetMapping public List<Event> getEvents() { return eventService.listEvents(); }
    @PostMapping public Event createEvent(@RequestBody EventDto dto) {
        return eventService.createEvent(dto);
    }
}
