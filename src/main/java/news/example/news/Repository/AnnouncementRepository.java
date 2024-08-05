package news.example.news.Repository;

import news.example.news.DOMAIN.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement , Long> {

}
