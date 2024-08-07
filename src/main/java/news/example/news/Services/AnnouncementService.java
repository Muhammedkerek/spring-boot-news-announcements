package news.example.news.Services;

import news.example.news.DOMAIN.Announcement;
import news.example.news.Repository.AnnouncementRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement saveAnnouncement(String subject, String content, LocalDate validityDate, MultipartFile imageFile) {
        Announcement announcement = new Announcement();
        announcement.setSubject(subject);
        announcement.setContent(content);
        announcement.setValidityDate(validityDate);

        if (!imageFile.isEmpty()) {
            try {
                // Create the uploads directory if it doesn't exist
                Path uploadPath = Paths.get("uploads");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path filePath = uploadPath.resolve(imageFile.getOriginalFilename());
                Files.write(filePath, imageFile.getBytes());

                announcement.setImage(filePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       return announcementRepository.save(announcement);
    }
    public List<Announcement>listAllAnnouncements(){
        return announcementRepository.findAll();
    }
    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid announcement ID: " + id));
    }
    public void updateAnnouncement(Long id, String subject, String content, LocalDate validityDate, MultipartFile imageFile) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid announcement ID: " + id));
        announcement.setSubject(subject);
        announcement.setContent(content);
        announcement.setValidityDate(validityDate);

        if (!imageFile.isEmpty()) {
            try {
                // Create the uploads directory if it doesn't exist
                Path uploadPath = Paths.get("uploads");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path filePath = uploadPath.resolve(imageFile.getOriginalFilename());
                Files.write(filePath, imageFile.getBytes());

                announcement.setImage(filePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        announcementRepository.save(announcement);
    }
    public void deleteAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid announcement ID: " + id));
        announcementRepository.delete(announcement);
    }
}
