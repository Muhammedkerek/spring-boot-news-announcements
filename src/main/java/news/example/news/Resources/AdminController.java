package news.example.news.Resources;


import ch.qos.logback.core.model.Model;
import news.example.news.DOMAIN.Announcement;
import news.example.news.DOMAIN.News;
import news.example.news.Services.AnnouncementService;
import news.example.news.Services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminController {
    private final NewsService newsService;
    private final AnnouncementService announcementService;

    public AdminController(NewsService newsService, AnnouncementService announcementService) {
        this.newsService = newsService;
        this.announcementService = announcementService;
    }


    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/admin/news")
    public String manageNews() {
        return "admin-news";  // This should map to admin-news.html
    }

    @GetMapping("/news")
    public String listNews(ModelMap model) {
        List<News> newsList = newsService.getAllNews(); // Add this method in NewsService
        model.put("newsList", newsList);
        return "news";  // This should map to news.html
    }

    @GetMapping("/news/update/{id}")
    public String updateNewsForm(@PathVariable("id") Long id, ModelMap modelMap) {
        News news = newsService.getNewsById(id);
        modelMap.put("news", news);
        return "update-news";  // This should map to update-news.html
    }

    @GetMapping("/news/{id}")
    public News findNewsById(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        return news;
    }

    @PostMapping("/news/update/{id}")
    public String updateNews(@PathVariable("id") Long id,
                             @RequestParam String subject,
                             @RequestParam String content,
                             @RequestParam("validityDate") String validityDateString,
                             @RequestParam String newsLink) {
        LocalDate validityDate = LocalDate.parse(validityDateString);
        newsService.updateNews(id, subject, content, validityDate, newsLink);
        return "redirect:/news";  // Redirect to the news listing page
    }

    @PostMapping("/admin/news")
    public String saveNews(@RequestParam String subject, @RequestParam String content, @RequestParam("validityDate") String validityDateString, @RequestParam String newsLink) {
        LocalDate validityDate = LocalDate.parse(validityDateString);
        newsService.saveNews(subject, content, validityDate, newsLink);
        return "redirect:/admin";
    }

    @PostMapping("/admin/news/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsService.deleteNewsById(id);
        return "redirect:/admin";

    }
    @GetMapping("/admin/announcements")
    public String manageAnnouncements() {
        return "admin-announcements";  // This should map to admin-announcements.html
    }
    @GetMapping("/announcements")
    public String listAnnouncements(ModelMap modelMap){
        List<Announcement> announcementsList = announcementService.listAllAnnouncements(); // Add this method in NewsService
        modelMap.put("announcementsList", announcementsList);
        return "announcements";


    }

    @PostMapping("/admin/announcements")
    public String saveAnnouncement(@RequestParam String subject,
                                   @RequestParam String content,
                                   @RequestParam("validityDate") String validityDateString,
                                   @RequestParam("image") MultipartFile imageFile) {
        LocalDate validityDate = LocalDate.parse(validityDateString);
        announcementService.saveAnnouncement(subject, content, validityDate, imageFile);
        return "admin";
    }
    @GetMapping("/announcements/update/{id}")
    public String updateAnnouncementForm(@PathVariable("id") Long id, ModelMap modelMap) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        modelMap.put("announcement", announcement);
        return "update-announcement";
    }
    @PostMapping("/announcements/update/{id}")
    public String updateAnnouncement(@PathVariable("id") Long id,
                                     @RequestParam String subject,
                                     @RequestParam String content,
                                     @RequestParam("validityDate") String validityDateString,
                                     @RequestParam("image") MultipartFile imageFile) {
        LocalDate validityDate = LocalDate.parse(validityDateString);
        announcementService.updateAnnouncement(id, subject, content, validityDate, imageFile);
        return "redirect:/announcements";  // Redirect to the announcements listing page
    }
    @PostMapping("/announcements/delete/{id}")
    public String deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncementById(id);
        return "admin";
    }




}
