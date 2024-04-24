package akselsoft.task;

// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController
// @RequestMapping
@Controller
public class WebController {
    @GetMapping("/data")
    public String getData(Model model) {
        // Call the analysis function and add the data to the model
        model.addAttribute("dataList", MeaningfulUseDataAnalyzer.analyzeData());
        return "dataView"; // Name of the HTML template
    }
}