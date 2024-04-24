package akselsoft.task;

import com.opencsv.bean.CsvBindByName;

public class HospitalData {

    @CsvBindByName(column = "region")
    private String region;

    @CsvBindByName(column = "period")
    private String period;

    @CsvBindByName(column = "pct_hospitals_mu")
    private double pctHospitalMu;

    // Getters and Setters

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getPctHospitalMu() {
        return pctHospitalMu;
    }

    public void setpctHospitalMu(double pctHospitalMu) {
        this.pctHospitalMu = pctHospitalMu;
    }
}
