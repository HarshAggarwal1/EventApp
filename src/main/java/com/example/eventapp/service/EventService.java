package com.example.eventapp.service;
import com.example.eventapp.dto.EventDto;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.User;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.UserRepository;
import com.example.eventapp.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository repo;
    private final UserRepository userRepo;
    private final RabbitTemplate rabbit;

    public EventService(EventRepository repo, UserRepository userRepo, RabbitTemplate rabbit) {
        this.repo = repo; this.userRepo = userRepo; this.rabbit = rabbit;
    }

    public List<Event> listEvents() {
        String usern = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepo.findByUsername(usern).orElseThrow();
        return repo.findByCreator(u);
    }

    public Event createEvent(EventDto dto) {
        String usern = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepo.findByUsername(usern).orElseThrow();

        Event ev = new Event();
        ev.setTitle(dto.title());
        ev.setDescription(dto.description());
        ev.setCreator(u);
        Event saved = repo.save(ev);

        rabbit.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, saved);
        return saved;
    }
}
