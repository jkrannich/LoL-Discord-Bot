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

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    public void setGameQueueConfigId(int gameQueueConfigId) {
        this.gameQueueConfigId = gameQueueConfigId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public long getGameLength() {
        return gameLength;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    public List<ParticipantDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDto> participants) {
        this.participants = participants;
    }
}
