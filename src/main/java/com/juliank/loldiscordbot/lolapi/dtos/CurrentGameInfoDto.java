package com.juliank.loldiscordbot.lolapi.dtos;

import java.util.List;

public class CurrentGameInfoDto {
    private long gameId;
    private int mapId;
    private String gameMode;
    private String gameType;
    private int gameQueueConfigId;
    private String platformId;
    private long gameStartTime;
    private long gameLength;

    private List<ParticipantDto> participants;

    public CurrentGameInfoDto() {}
}
