package news.example.news.Repository;

import news.example.news.DOMAIN.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event ,Long> {

}
