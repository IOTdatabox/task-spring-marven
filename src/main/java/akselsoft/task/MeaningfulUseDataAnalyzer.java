package akselsoft.task;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class MeaningfulUseDataAnalyzer {

    private static final String DATA_URL = "https://www.healthit.gov/sites/default/files/data/datasets/Meaningful-Use-Acceleration-Scorecard.csv";

    public static List<StateMeaningfulUseData> analyzeData() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URI.create(DATA_URL));
            InputStreamReader streamReader = new InputStreamReader(client.execute(request).getEntity().getContent());

            List<HospitalData> hospitalDataList = new CsvToBeanBuilder<HospitalData>(streamReader)
                    .withType(HospitalData.class)
                    .build()
                    .parse();

            List<StateMeaningfulUseData> meaningfulUseData = hospitalDataList.stream()
                    .filter(data -> "2014".equals(data.getPeriod()))
                    .map(StateMeaningfulUseData::new)
                    .sorted((d1, d2) -> Double.compare(d2.getPctHospitalMu(), d1.getPctHospitalMu()))
                    .collect(Collectors.toList());
            /*---------------------------------------------------*/
            // Print out the state and corresponding percentage
            meaningfulUseData.forEach(data -> System.out.println(data.getState() + ": " + data.getPctHospitalMu()));
            /*---------------------------------------------------*/
            return meaningfulUseData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class StateMeaningfulUseData {

        private String state;
        private double pctHospitalMu;

        public StateMeaningfulUseData(HospitalData hospitalData) {
            this.state = hospitalData.getRegion();
            this.pctHospitalMu = hospitalData.getPctHospitalMu();
            // Other relevant fields can be initialized if required.
        }

        public String getState() {
            return state;
        }

        public double getPctHospitalMu() {
            return pctHospitalMu;
        }
    }
}
