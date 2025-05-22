package com.juliank.loldiscordbot.lolapi.dtos;

import java.util.List;

public class ParticipantDto {
    private String puuid;
    private int teamId;
    private int spell1Id;
    private int spell2Id;
    private int championId;
    private int profileIconId;
    private String riotId;
    private boolean bot;
    private String summonerId;
    private List<Object> gameCustomizationObjects;

    public ParticipantDto() {}
}
