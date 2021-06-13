package app.domain.model;

import app.domain.shared.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.time.Period;

public class Data {

    private String historicalDays;
    private int date;
    private String confidenceLevelIC;
    private String significanceLevel;
    private LocalDate intervalStartDate;
    private LocalDate intervalEndDate;

    public Data(String historicalDays, int date, String confidenceLevelIC, String significanceLevel) {

        this.historicalDays = historicalDays;
        this.date = date;
        this.confidenceLevelIC = confidenceLevelIC;
        this.significanceLevel = significanceLevel;

    }

    public Data() {

        this.historicalDays = null;
        this.date = 0;
        this.confidenceLevelIC = null;
        this.significanceLevel = null;
        this.intervalStartDate=null;
        this.intervalEndDate=null;

    }

    public void setConfidenceLevelIC(String confidenceLevelIC) throws ConfidenceLevelInvalidException, ConfidenceLevelICEmptyException {
        checkConfidenceLevelIC(confidenceLevelIC);
        this.confidenceLevelIC = confidenceLevelIC;
    }

    public void setHistoricalDays(String historicalDays) throws HistoricalDaysEmptyException, HistoricalDaysInvalidException {
        checkHistoricaldays(historicalDays);
        this.historicalDays = historicalDays;
    }

    public void setIntervalDates(int date) throws DateEmptyException, DateInvalidException {
        checkIntervalDates(date);
        this.date = date;
    }

    public void setDates(LocalDate start , LocalDate end){
        this.intervalStartDate=start;
        this.intervalEndDate=end;
    }

    private void checkConfidenceLevelIC(String confidenceLevelIC) throws ConfidenceLevelInvalidException, ConfidenceLevelICEmptyException {

        if (confidenceLevelIC.isEmpty() || StringUtils.isBlank(confidenceLevelIC)) {
            throw new ConfidenceLevelICEmptyException();
        }

        int n = Integer.parseInt(confidenceLevelIC);

        if (n > 100 || n < 0) {
            throw new ConfidenceLevelInvalidException();
        }

    }

    private void checkIntervalDates(long date) throws DateEmptyException, DateInvalidException {

        if (date == 0) {
            throw new DateEmptyException();
        }

        if (date < 0) {
            throw new DateInvalidException();
        }

    }

    private void checkHistoricaldays(String historicalDays) throws HistoricalDaysEmptyException, HistoricalDaysInvalidException {

        if (historicalDays.isEmpty() || StringUtils.isBlank(historicalDays)) {
            throw new HistoricalDaysEmptyException();
        }

        int n = Integer.parseInt(historicalDays);

        if (n > 365) {
            throw new HistoricalDaysInvalidException();
        }

    }

   public int getHistoricalDaysInt() {
        return Integer.parseInt(historicalDays);
    }

    public String getConfidenceLevelIC() {
        return confidenceLevelIC;
    }

    public String getHistoricalDays() {
        return historicalDays;
    }

    public LocalDate getIntervalEndDate() {
        return intervalEndDate;
    }

    public LocalDate getIntervalStartDate() {
        return intervalStartDate;
    }

    public int getDifferenceInDates(){

        return Period.between(this.intervalStartDate,this.intervalEndDate).getDays();

    }

}

