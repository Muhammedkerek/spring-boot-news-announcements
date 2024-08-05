package news.example.news.Repository;

import news.example.news.DOMAIN.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
