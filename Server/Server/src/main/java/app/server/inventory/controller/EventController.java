//package app.server.inventory.controller;
//
//
//import app.server.inventory.dtos.EventsDto;
//import app.server.inventory.entities.Event;
//import app.server.inventory.mapper.EventMapper;
//import app.server.inventory.services.EventService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/event")
//public class EventController {
//
//    @Autowired
//    private EventService eventService;
//
//    @PostMapping
//    public ResponseEntity<EventsDto> createEvent(@RequestBody EventsDto eventsDto){
//        System.out.println(eventsDto);
//        Event event = eventService.createEvent(eventsDto);
//        System.out.println("-------------------------------------------OK-------------------------------");
//        EventsDto createdEvent = EventMapper.mapToEventsDto(event);
//        System.out.println(createdEvent);
//        return new ResponseEntity(createdEvent, HttpStatus.CREATED);
//    }
//
//    @GetMapping("{id}")
//    public  ResponseEntity<EventsDto> getEventById (@PathVariable("id") Long eventId){
//        Event event = eventService.getEventById(eventId);
//
//        EventsDto findingEvent = EventMapper.mapToEventsDto(event);
//
//        return ResponseEntity.ok(findingEvent);
//
//    }
//
//}
