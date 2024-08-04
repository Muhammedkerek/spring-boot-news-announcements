package news.example.news.Services;

import news.example.news.DOMAIN.Event;
import news.example.news.DOMAIN.News;
import news.example.news.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> listAllEvents(){
        return eventRepository.findAll();
    }
    public Event save(Event event) {
        return eventRepository.save(event);
    }
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }




}
