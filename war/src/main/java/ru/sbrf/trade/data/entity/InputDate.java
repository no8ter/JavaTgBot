package ru.sbrf.trade.data.entity;

public class InputDate {
    private boolean range;
    private String startDate;
    private String endDate;
    private String singleDate;

    public boolean isRange() {
        return range;
    }

    public void setRange(boolean range) {
        this.range = range;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSingleDate() {
        return singleDate;
    }

    public void setSingleDate(String singleDate) {
        this.singleDate = singleDate;
    }
}
