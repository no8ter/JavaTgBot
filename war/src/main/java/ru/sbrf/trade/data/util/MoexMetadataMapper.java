package ru.sbrf.trade.data.util;

import ru.sbrf.trade.data.da.entity.ch.MoexColumnNameDto;
import ru.sbrf.trade.data.da.entity.ch.MoexDto;
import ru.sbrf.trade.data.da.entity.moex.Root;

import java.util.ArrayList;
import java.util.List;

public class MoexMetadataMapper {

    public static MoexColumnNameDto fromMeta(Root root){
        MoexColumnNameDto dto = new MoexColumnNameDto();

        List<String> columns = root.history.columns;

        dto.boardid = columns.get(0);
        dto.tradedate = columns.get(1);
        dto.shortname = columns.get(2);
        dto.secid = columns.get(3);
        dto.numtrades = columns.get(4);
        dto.value = columns.get(5);
        dto.open = columns.get(6);
        dto.low = columns.get(7);
        dto.high = columns.get(8);
        dto.legalcloseprice = columns.get(9);
        dto.waprice = columns.get(10);
        dto.close = columns.get(11);
        dto.volume = columns.get(12);
        dto.marketprice2 = columns.get(13);
        dto.marketprice3 = columns.get(14);
        dto.admittedquote = columns.get(15);
        dto.mp2VALTRD = columns.get(16);
        dto.marketprice3TRADESVALUE = columns.get(17);
        dto.admittedvalue = columns.get(18);
        dto.waval = columns.get(19);
        dto.tradingsession = columns.get(20);

        return dto;
    }
    public static List<MoexDto> fromData(Root root){

        List<MoexDto> moexDto = new ArrayList<>();
        List<List<Object>> metadata = root.history.data;


        for (List<Object> currentSpecId : metadata) {
            MoexDto currentDto = new MoexDto();
            currentDto.boardid = (String) currentSpecId.get(0);
            currentDto.tradedate = (String) currentSpecId.get(1);
            currentDto.shortname = (String) currentSpecId.get(2);
            currentDto.secid = (String) currentSpecId.get(3);
            currentDto.numtrades = currentSpecId.get(4) != null ? Double.parseDouble(currentSpecId.get(4).toString()) : null;
            currentDto.value = currentSpecId.get(5) != null ? Double.parseDouble(currentSpecId.get(5).toString()) : null;
            currentDto.open = currentSpecId.get(6) != null ? Double.parseDouble(currentSpecId.get(6).toString()) : null;
            currentDto.low = currentSpecId.get(7) != null ? Double.parseDouble(currentSpecId.get(7).toString()) : null;
            currentDto.high = currentSpecId.get(8) != null ? Double.parseDouble(currentSpecId.get(8).toString()) : null;
            currentDto.legalcloseprice = currentSpecId.get(9) != null ? Double.parseDouble(currentSpecId.get(9).toString()) : null;
            currentDto.waprice = currentSpecId.get(10) != null ? Double.parseDouble(currentSpecId.get(10).toString()) : null;
            currentDto.close = currentSpecId.get(11) != null ? Double.parseDouble(currentSpecId.get(11).toString()) : null;
            currentDto.volume = currentSpecId.get(12) != null ? Double.parseDouble(currentSpecId.get(12).toString()) : null;
            currentDto.marketprice2 = currentSpecId.get(13) != null ? Double.parseDouble(currentSpecId.get(13).toString()) : null;
            currentDto.marketprice3 = currentSpecId.get(14) != null ? Double.parseDouble(currentSpecId.get(14).toString()) : null;
            currentDto.admittedquote = currentSpecId.get(15) != null ? Double.parseDouble(currentSpecId.get(15).toString()) : null;
            currentDto.mp2VALTRD = currentSpecId.get(16) != null ? Double.parseDouble(currentSpecId.get(16).toString()) : null;
            currentDto.marketprice3TRADESVALUE = currentSpecId.get(17) != null ? Double.parseDouble(currentSpecId.get(17).toString()) : null;
            currentDto.admittedvalue = currentSpecId.get(18) != null ? Double.parseDouble(currentSpecId.get(18).toString()) : null;
            currentDto.waval = currentSpecId.get(19) != null ? Double.parseDouble(currentSpecId.get(19).toString()) : null;
            currentDto.tradingsession = Integer.parseInt(currentSpecId.get(20).toString());

            moexDto.add(currentDto);
        }

        return moexDto;
    }

}
