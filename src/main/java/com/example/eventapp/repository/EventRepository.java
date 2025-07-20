package com.example.eventapp.repository;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Cacheable("eventsByUser")
    List<Event> findByCreator(User creator);
}
