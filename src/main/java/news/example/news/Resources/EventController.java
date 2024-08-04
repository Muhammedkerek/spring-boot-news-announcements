package news.example.news.Resources;

import news.example.news.DOMAIN.Event;
import news.example.news.Services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/events")
    public List<Event> findAllEvents(){
        return eventService.listAllEvents();
    }
}
