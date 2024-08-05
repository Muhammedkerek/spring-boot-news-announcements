package news.example.news.Services;


import news.example.news.DOMAIN.News;
import news.example.news.Repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    public void saveNews(String subject, String content, LocalDate validityDate , String newsLink){
        News news = new News();
        news.setNewsLink(newsLink);
        news.setContent(content);
        news.setValidityDate(validityDate);
        news.setSubject(subject);
        newsRepository.save(news);
    }
    public void updateNews(Long id, String subject, String content, LocalDate validityDate, String newsLink){
        News news = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid news ID: " + id));
        news.setSubject(subject);
        news.setContent(content);
        news.setValidityDate(validityDate);
        news.setNewsLink(newsLink);
        newsRepository.save(news);
    }
    public News getNewsById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid news ID: " + id));
    }
    public void deleteNewsById(Long id){
        News news = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid news ID: " + id));
        newsRepository.delete(news);
    }


    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

}
