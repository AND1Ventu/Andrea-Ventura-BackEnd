package com.exercise.gestioneEventi.service;

import com.exercise.gestioneEventi.exception.BadRequestException;
import com.exercise.gestioneEventi.model.Event;
import com.exercise.gestioneEventi.dto.EventRequest;
import com.exercise.gestioneEventi.repository.EventRepository;
import com.exercise.gestioneEventi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventRequest eventRequest) {
        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setDescription(eventRequest.getDescription());
        event.setDate(eventRequest.getDate());
        event.setLocation(eventRequest.getLocation());
        event.setAvailableSeats(eventRequest.getAvailableSeats());

        return eventRepository.save(event);
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));
    }

    public List<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll();
    }

    public Event updateEvent(int eventId, EventRequest eventRequest) {
        Event event = getEventById(eventId);
        event.setTitle(eventRequest.getTitle());
        event.setDescription(eventRequest.getDescription());
        event.setDate(eventRequest.getDate());
        event.setLocation(eventRequest.getLocation());
        event.setAvailableSeats(eventRequest.getAvailableSeats());

        return eventRepository.save(event);
    }

    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event bookSeats(int eventId, int seatsToBook) {
        Event event = getEventById(eventId);

        int availableSeats = event.getAvailableSeats() - event.getBookedSeats();

        if (seatsToBook <= availableSeats) {
            event.setBookedSeats(event.getBookedSeats() + seatsToBook);
            return eventRepository.save(event);
        } else {
            throw new BadRequestException("Posti non disponibili");
        }
    }
}

