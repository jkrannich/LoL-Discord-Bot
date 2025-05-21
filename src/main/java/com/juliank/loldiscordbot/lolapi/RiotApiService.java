package com.juliank.loldiscordbot.lolapi;

import io.github.cdimascio.dotenv.Dotenv;

public class RiotApiService {

    private final Dotenv config;
    private final String BASE_URL = "https://euw.api.riotgames.com";

    public RiotApiService(Dotenv config) {
        this.config = Dotenv.configure().load();
        String token = config.get("RIOTAPIKEY");
    }

    public String getSummonerByName(String name) {

    return null;
    }
}
