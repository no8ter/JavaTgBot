package ru.sbrf.trade.data.util;

import ru.sbrf.trade.data.da.entity.ch.MoexDto;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapMoexDto {
    public static String mapToString(MoexDto moexDto) {

        return moexDto.shortname +
                " " +
                moexDto.open +
                "/" +
                moexDto.high +
                "/" +
                moexDto.low +
                "/" +
                moexDto.close;
    }

    public static String getGrowLider(List<MoexDto> moexDtoList) {
        String result = "\nЛидер роста\n";

        String temp = moexDtoList.stream()
                .filter(c -> c.close != null)
                .sorted(Comparator.comparingDouble(MapMoexDto::getDiffPersent).reversed())
                .limit(1)
                .map(c -> c.shortname+" - "+getDiffPersent(c))
                .findFirst()
                .orElse("");

        return result+temp;
    }

    public static double getDiffPersent(MoexDto moexDto) {
        return ((moexDto.close / moexDto.open)*100)-100;
    }
}
