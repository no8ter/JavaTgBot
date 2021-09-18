package ru.sbrf.trade.data.bh;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbrf.trade.data.da.DataStorageConnection;
import ru.sbrf.trade.data.da.StockConnection;
import ru.sbrf.trade.data.da.entity.ch.MoexDto;
import ru.sbrf.trade.data.da.entity.moex.Root;
import ru.sbrf.trade.data.util.TimePeriodUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.sbrf.trade.data.util.MoexMetadataMapper.fromData;
import static ru.sbrf.trade.data.util.MoexMetadataMapper.fromMeta;

public class StockDataPipeline {

    @Autowired
    private StockConnection stockConnection;
    @Autowired
    private DataStorageConnection storageConnection;

    //todo нужно возвращать что то для пинга. потом это будет в телегу отправляться

    /**
     * Метод чтения данных с рынка за период
     *
     * @param startDate - начало периода
     * @param endDate   - конец периода
     *                  <p>
     *                  todo должны быть метрики считанных данных
     */
    public List<List<MoexDto>> rangePipeline(String startDate, String endDate) throws IOException, SQLException, InterruptedException {

        List<String> dateRange = TimePeriodUtil.generateDateRange(new DateTime(startDate), new DateTime(endDate));
        int i = 0;
        int count = 0;
        List<List<MoexDto>> result = new ArrayList<>();
        for (String date : dateRange) {
            System.out.println("Считываем данные за " + i + "день из " + dateRange.size());
            Root root = stockConnection.downloadMoexDataByDate(date);
            List<MoexDto> list = fromData(root);
            result.add(list);
            count += list.size();
            if (list.size() != 0) storageConnection.uploadClickhouse(list, fromMeta(root));
            Thread.sleep(1000);
            i++;
        }
        System.out.println("Записано " + count);
        return result;
    }

    /**
     * Метод чтения данных с рынка за указанную дату
     *
     * @param date - дата за которую нужно загрузить данные
     */
    public List<MoexDto> rangePipeline(String date) throws IOException, SQLException {

        Root root = stockConnection.downloadMoexDataByDate(date);
        List<MoexDto> list = fromData(root);
        if (list.size() == 0) return null;

        storageConnection.uploadClickhouse(list, fromMeta(root));

        return list;
    }
}
