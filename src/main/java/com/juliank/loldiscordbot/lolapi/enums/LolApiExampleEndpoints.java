package com.juliank.loldiscordbot.lolapi.enums;

import java.util.Map;

public enum LolApiExampleEndpoints {

    ACCOUNT_BY_RIOT_ID("https://{routingValue}.api.riotgames.com/riot/account/v1/accounts/by-riot-id/{name}/{tag}"),
    SUMMONER_BY_NAME("https://{routingValue}.api.riotgames.com/lol/summoner/v4/summoners/by-name/{name}"),
    ACTIVE_GAME_BY_SUMMONER("https://{routingValue}.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/{summoner}");

    private final String template;

    LolApiExampleEndpoints(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    public String build(Map<String, String> params) {
        String url = template;
        for (var entry : params.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            url = url.replace(placeholder, entry.getValue());
        }
        return url;
    }
}
