package ru.sbrf.trade.data.da.entity.moex;

import java.util.List;

public class HistoryCursor{
    public Metadata metadata;
    public List<String> columns;
    public List<List<Integer>> data;
}
