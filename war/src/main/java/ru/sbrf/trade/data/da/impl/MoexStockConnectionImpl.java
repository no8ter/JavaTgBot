package ru.sbrf.trade.data.da.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import ru.sbrf.trade.data.da.StockConnection;
import ru.sbrf.trade.data.da.entity.moex.Root;

import java.io.IOException;

public class MoexStockConnectionImpl implements StockConnection {

    private final String URI;
    private final CloseableHttpClient httpClient;

    public MoexStockConnectionImpl(String URI, CloseableHttpClient httpClient) {
        this.URI = URI;
        this.httpClient = httpClient;
    }

    public Root downloadMoexDataByDate(String date) throws IOException {

        HttpGet request = new HttpGet(URI + date);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            Root dayStat = new Root();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                dayStat = mapper.readValue(result, Root.class);
            }
            return dayStat;
        }
    }
}
