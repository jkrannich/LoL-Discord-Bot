package com.juliank.loldiscordbot.lolapi;

import com.juliank.loldiscordbot.lolapi.accountv1.AccountV1Api;
import com.juliank.loldiscordbot.lolapi.dtos.AccountDto;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class RiotApiService {

    private final Dotenv config;
    private final AccountV1Api accountV1Api;

    public RiotApiService(Dotenv config) {
        this.config = Dotenv.configure().load();
        String token = config.get("RIOTAPIKEY");
        this.accountV1Api = new AccountV1Api(config);
    }

    public AccountDto getAccountByRiotId(String routingValue, String tag, String name) throws IOException, InterruptedException {
        return accountV1Api.getSummonerByRiotId(routingValue, tag, name);
    }
}
