package akselsoft.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeaningfulUseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeaningfulUseApplication.class, args);
        MeaningfulUseDataAnalyzer.analyzeData();
    }
}
