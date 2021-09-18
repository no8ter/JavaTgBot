package ru.sbrf.trade.data.da;

import ru.sbrf.trade.data.da.entity.ch.MoexColumnNameDto;
import ru.sbrf.trade.data.da.entity.ch.MoexDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DataStorageConnection {
    void uploadClickhouse(List<MoexDto> moexDto, MoexColumnNameDto moexColumnNameDto) throws SQLException, IOException;
}
