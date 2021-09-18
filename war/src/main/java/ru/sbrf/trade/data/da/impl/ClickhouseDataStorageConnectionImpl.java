package ru.sbrf.trade.data.da.impl;

import ru.sbrf.trade.data.da.DataStorageConnection;
import ru.sbrf.trade.data.da.entity.ch.MoexColumnNameDto;
import ru.sbrf.trade.data.da.entity.ch.MoexDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class ClickhouseDataStorageConnectionImpl implements DataStorageConnection {

    private final Connection connection;

    public ClickhouseDataStorageConnectionImpl(Connection connection){
        this.connection = connection;
    }

    public void uploadClickhouse(List<MoexDto> moexDto, MoexColumnNameDto moexColumnNameDto) throws SQLException {
        String query =
                "insert into default.moex_shares (" +
                        "BOARDID, " +
                        "TRADEDATE, " +
                        "SHORTNAME, " +
                        "SECID," +
                        "NUMTRADES, " +
                        "VALUE, " +
                        "`OPEN`, " +
                        "LOW, " +
                        "HIGH," +
                        "LEGALCLOSEPRICE, " +
                        "WAPRICE, " +
                        "`CLOSE`, " +
                        "VOLUME," +
                        "MARKETPRICE2, " +
                        "MARKETPRICE3, " +
                        "ADMITTEDQUOTE," +
                        "MP2VALTRD, " +
                        "MARKETPRICE3TRADESVALUE, " +
                        "ADMITTEDVALUE, " +
                        "WAVAL, " +
                        "TRADINGSESSION " +
                        ") " +
                        "values ";

        for (MoexDto dto : moexDto) {
            String st = String.format(Locale.US,
                    "('%s', '%s', '%s', '%s', %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %d)",
                    dto.boardid,
                    dto.tradedate,
                    dto.shortname,
                    dto.secid,
                    dto.numtrades,
                    dto.value,
                    dto.open,
                    dto.low,
                    dto.high,
                    dto.legalcloseprice,
                    dto.waprice,
                    dto.close,
                    dto.volume,
                    dto.marketprice2,
                    dto.marketprice3,
                    dto.admittedquote,
                    dto.mp2VALTRD,
                    dto.marketprice3TRADESVALUE,
                    dto.admittedvalue,
                    dto.waval,
                    dto.tradingsession);
            query += st + ",";
        }
        query = query.substring(0, query.length() - 1);

        connection.createStatement().executeQuery(query);
    }

}
