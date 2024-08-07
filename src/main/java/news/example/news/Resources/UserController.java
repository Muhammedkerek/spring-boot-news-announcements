package news.example.news.Resources;


import news.example.news.DOMAIN.Announcement;
import news.example.news.DOMAIN.News;
import news.example.news.Services.AnnouncementService;
import news.example.news.Services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final NewsService newsService;
    private final AnnouncementService announcementService;

    public UserController(NewsService newsService, AnnouncementService announcementService) {
        this.newsService = newsService;
        this.announcementService = announcementService;
    }

    @GetMapping("/user")
    public String adminPage() {
        return "user";
    }
    @GetMapping("/user/news")
    public String listNews(ModelMap model) {
        List<News> newsList = newsService.getAllNews();
        System.out.println("News List: " + newsList);
        model.put("newsList", newsList);
        return "user-news";
    }
    @GetMapping("/Announcements")
    public String listAnnouncements(ModelMap model) {
        List<Announcement> announcementsList = announcementService.listAllAnnouncements();
        model.put("announcementsList", announcementsList);
        return "user-announcements";
    }

}
