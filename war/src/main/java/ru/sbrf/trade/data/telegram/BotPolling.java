package ru.sbrf.trade.data.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sbrf.trade.data.bh.StockDataPipeline;
import ru.sbrf.trade.data.da.entity.ch.MoexDto;
import ru.sbrf.trade.data.util.MapMoexDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sbrf.trade.data.util.MapMoexDto.getGrowLider;

@Component
public class BotPolling extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotPolling.class);

    private final String botToken;
    private final String botName;
    private final StockDataPipeline stockDataPipeline;
    private final RestTemplate consumerRestTemplate;

    @Autowired
    public BotPolling(@Value("${telegram.bot.token}") String botToken,
                      @Value("BrokerJavaSbt_bot") String botName,
                      @Qualifier("stockDataPipeline") StockDataPipeline stockDataPipeline,
                      @Qualifier("consumerRestTemplate")RestTemplate consumerRestTemplate) {
        this.botToken = botToken;
        this.botName = botName;
        this.stockDataPipeline = stockDataPipeline;
        this.consumerRestTemplate = consumerRestTemplate;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText();
            StringBuilder head = new StringBuilder(message + "\nТИКЕР Open/High/Low/Close\n=============================\n");
            Long chatId = update.getMessage().getChatId();
            List<MoexDto> quotes = stockDataPipeline.rangePipeline(message);
            List<String> mappedQuotes = quotes
                    .stream()
                    .filter(c -> c.marketprice3TRADESVALUE > 15000000)
                    .sorted(Comparator.comparing(c -> -c.high))
                    .limit(10)
                    .map(MapMoexDto::mapToString)
                    .collect(Collectors.toList());

            for (String m: mappedQuotes) {
                head.append(m).append("\n");
            }
            head.append(getGrowLider(quotes));
            execute(new SendMessage(String.valueOf(chatId), head.toString()));
        } catch (IOException | SQLException | TelegramApiException e) {
            LOGGER.error(e.toString());
        }
    }

    private void replyToDB(List<String> quotes) {
        RestTemplate template = new RestTemplate();
        template.postForEntity("http://localhost:8000/shares-consumer", quotes, int.class);
    }
}
