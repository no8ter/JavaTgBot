package ru.sbrf.trade.data;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbrf.trade.data.bh.StockDataPipeline;
import ru.sbrf.trade.data.context.AppConfig;

import java.io.IOException;
import java.sql.SQLException;

public class MoexApp {

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        StockDataPipeline pipeline = (StockDataPipeline) ctx.getBean("stockDataPipeline");
        String[] split = args[0].split("=");
        switch (split[0]) {
            case "r":
                String[] dates = split[1].split(";");
                System.out.println(pipeline.rangePipeline(dates[0], dates[1]));
                break;
            case "t":
                System.out.println(pipeline.rangePipeline(split[1]));
                break;
            default:
                System.out.println("Некорректный аргумент. Формат r=<2020-03-20>;<2020-03-22>/t=<2020-03-22>");
        }
    }
}
