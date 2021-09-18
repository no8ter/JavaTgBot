package ru.sbrf.trade.data.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sbrf.trade.data.bh.StockDataPipeline;
import ru.sbrf.trade.data.entity.InputDate;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class MoexRequestController {
    private final StockDataPipeline stockDataPipeline;

    public MoexRequestController(@Qualifier("stockDataPipeline") StockDataPipeline stockDataPipeline) {
        this.stockDataPipeline = stockDataPipeline;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/request")
    public String getDataByDate(@Valid @ModelAttribute("inputDate") InputDate inputDate) {
        return "WEB-INF/view/requestData";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/result")
    public String getResult(@Valid @ModelAttribute("inputDate") InputDate inputDate) throws InterruptedException, IOException, SQLException {
        if (inputDate.isRange()) {
            System.out.println(stockDataPipeline.rangePipeline(inputDate.getStartDate(), inputDate.getEndDate()));
        } else if (!(inputDate.isRange())) {
            System.out.println(stockDataPipeline.rangePipeline(inputDate.getSingleDate()));
        } else {
            System.out.println("Некорректный аргумент. Формат r=<2020-03-20>;<2020-03-22>/t=");
        }

        return "WEB-INF/view/resultData";
    }
}
