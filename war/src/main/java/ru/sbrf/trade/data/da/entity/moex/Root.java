package ru.sbrf.trade.data.da.entity.moex;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root{

    public Root(){
        history = new History();
        historyCursor = new HistoryCursor();
    }

    public History history;
    @JsonProperty("history.cursor")
    public HistoryCursor historyCursor;
}
