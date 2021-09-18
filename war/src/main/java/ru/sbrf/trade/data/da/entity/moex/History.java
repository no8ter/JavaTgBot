package ru.sbrf.trade.data.da.entity.moex;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class History{

    public History(){
        metadata = new Metadata();
        columns = new ArrayList<>();
        data = new ArrayList<>();
    }

    @JsonProperty("metadata")
    public Metadata metadata;
    public List<String> columns;
    public List<List<Object>> data;
}
