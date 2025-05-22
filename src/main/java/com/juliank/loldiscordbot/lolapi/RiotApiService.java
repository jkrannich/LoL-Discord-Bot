package com.juliank.loldiscordbot.lolapi;

import com.juliank.loldiscordbot.lolapi.accountv1.AccountV1Endpoint;
import com.juliank.loldiscordbot.lolapi.dtos.AccountDto;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class RiotApiService {

    private final Dotenv config;
    private final AccountV1Endpoint accountV1Endpoint;

    public RiotApiService(Dotenv config) {
        this.config = Dotenv.configure().load();
        String token = config.get("RIOTAPIKEY");
        this.accountV1Endpoint = new AccountV1Endpoint(config);
    }

    public AccountDto getAccountByRiotId(String routingValue, String tag, String name) throws IOException, InterruptedException {
        return accountV1Endpoint.getSummonerByRiotId(routingValue, tag, name);
    }
}
