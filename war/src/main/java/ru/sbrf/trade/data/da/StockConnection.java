package ru.sbrf.trade.data.da;

import ru.sbrf.trade.data.da.entity.moex.Root;

import java.io.IOException;

public interface StockConnection {

    Root downloadMoexDataByDate(String date) throws IOException;

}
