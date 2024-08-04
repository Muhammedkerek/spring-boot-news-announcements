package news.example.news.Resources;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {

        @GetMapping("/admin")
        public String adminPage() {
            return "admin";
        }
}
