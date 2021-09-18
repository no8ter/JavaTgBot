package ru.sbrf.trade.data.context;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.sbrf.trade.data.bh.StockDataPipeline;
import ru.sbrf.trade.data.da.DataStorageConnection;
import ru.sbrf.trade.data.da.StockConnection;
import ru.sbrf.trade.data.da.impl.FileDataStorageConnectionImpl;
import ru.sbrf.trade.data.da.impl.MoexStockConnectionImpl;

import java.sql.SQLException;

@Configuration
public class AppConfig {

    private static final String URI = "http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.json?date=";
    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/default";

    @Bean(name = "stockDataPipeline")
    public StockDataPipeline stockDataPipelineInitMethod() {
        return new StockDataPipeline();
    }

    @Bean(name = "stockConnection")
    public StockConnection moexStockConnectionImplInitMethod() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return new MoexStockConnectionImpl(URI, httpClient);
    }

    @Bean(name = "storageConnection")
    public DataStorageConnection clickhouseDataStorageConnectionImpl() throws SQLException {
        return new FileDataStorageConnectionImpl();
    }

    @Bean(name = "consumerRestTemplate")
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        return new RestTemplate(factory);
    }
}
