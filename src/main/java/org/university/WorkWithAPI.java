package org.university;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;


public class WorkWithAPI {
    private final String API_KEY;
    private final String ENDPOINT;

    public WorkWithAPI(String apiKey, String endpoint) {
        this.API_KEY = apiKey;
        this.ENDPOINT = endpoint;
    }

    public String doGetRequest(String endpointURL) throws IOException, ParseException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(endpointURL);
            httpGet.addHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity responseBody = response.getEntity();
                return EntityUtils.toString(responseBody);
            }
        }
    }

    public String buildUrl(String path, String queryOptions) {
        String resultUrl = ENDPOINT + path + "?key=" + API_KEY;

        if (!queryOptions.isEmpty()) {
            resultUrl += "&" + queryOptions;
        }

        return resultUrl;
    }

    protected String getData(String endpointPath, String queryParams) throws IOException, ParseException {
        String urlToUse = buildUrl(endpointPath, queryParams);
        return doGetRequest(urlToUse);
    }
}