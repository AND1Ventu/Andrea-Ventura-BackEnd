package com.exercise.gestioneEventi.controller;

import com.exercise.gestioneEventi.dto.EventRequest;
import com.exercise.gestioneEventi.exception.BadRequestException;
import com.exercise.gestioneEventi.model.Event;
import com.exercise.gestioneEventi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/event")
    public Page<Event> getAll(Pageable pageable){

        return (Page<Event>) eventService.getAllEvents(pageable);
    }
    @GetMapping("/event/{id}")
    public Event getEventById(@PathVariable int id){
        return eventService.getEventById(id);

    }
    @PostMapping("/event")
    public Event saveEvent(@RequestBody @Validated EventRequest eventRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventService.createEvent(eventRequest);
    }
    @PutMapping("/event/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody @Validated EventRequest eventRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventService.updateEvent(id, eventRequest);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable int id){
        eventService.deleteEvent(id);
    }
}
