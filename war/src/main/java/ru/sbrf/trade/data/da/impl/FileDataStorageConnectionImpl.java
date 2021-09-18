package ru.sbrf.trade.data.da.impl;

import ru.sbrf.trade.data.da.DataStorageConnection;
import ru.sbrf.trade.data.da.entity.ch.MoexColumnNameDto;
import ru.sbrf.trade.data.da.entity.ch.MoexDto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FileDataStorageConnectionImpl implements DataStorageConnection {
    @Override
    public void uploadClickhouse(List<MoexDto> moexDto, MoexColumnNameDto moexColumnNameDto) throws IOException {
        String st = "";
        for (MoexDto dto : moexDto) {
            st = String.format(Locale.US,
                    "('BOARDID: %s', 'TRADEDATE: %s', 'SHORTNAME: %s', 'SECID: %s', NUMTRADES: %f, VALUE: %f," +
                            "`OPEN` : %f, LOW: %f, HIGH: %f, LEGALCLOSEPRICE: %f, WAPRICE: %f, `CLOSE`: %f, VOLUME: %f, " +
                            "MARKETPRICE2: %f, MARKETPRICE3: %f, ADMITTEDQUOTE: %f, MP2VALTRD: %f, MARKETPRICE3TRADESVALUE: %f," +
                            "ADMITTEDVALUE: %f, WAVAL: %f,TRADINGSESSION:  %d)",
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
            st += st + "\n,";
        }
        st = st.substring(0, st.length() - 1);
        whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo(st);
    }

    public void whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo(String text)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt", true));
        writer.append(' ');
        writer.append(text);
        writer.close();
    }

}
