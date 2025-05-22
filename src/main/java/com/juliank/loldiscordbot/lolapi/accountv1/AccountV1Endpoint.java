package com.juliank.loldiscordbot.lolapi.accountv1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliank.loldiscordbot.lolapi.dtos.AccountDto;
import com.juliank.loldiscordbot.lolapi.enums.LolApiExampleEndpoints;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;

public class AccountV1Endpoint {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String apiKey;

    public AccountV1Endpoint(Dotenv config) {
        this.apiKey = config.get("RIOTAPIKEY");
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public AccountDto getSummonerByRiotId(String region, String tag, String name) throws IOException, InterruptedException {
        Map<String, String> params = Map.of(
                "routingValue", region,
                "tag", tag,
                "name", name
        );

        String url = LolApiExampleEndpoints.ACCOUNT_BY_RIOT_ID.build(params);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Riot-Token", apiKey)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        switch (response.statusCode()) {
            case 200:
                return objectMapper.readValue(response.body(), AccountDto.class);
            case 404:
                System.out.println(request);
                throw new NoSuchElementException("No account found for: " + name + "#" + tag);
            case 429:
                throw new RuntimeException("Riot API rate limit exceeded");
            default:
                throw new RuntimeException("Unexpected HTTP code: " + response.statusCode());
        }
    }
}
