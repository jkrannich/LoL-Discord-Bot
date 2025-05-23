package com.juliank.loldiscordbot.commands.command;

import com.juliank.loldiscordbot.commands.BotCommand;
import com.juliank.loldiscordbot.lolapi.RiotApiService;
import com.juliank.loldiscordbot.lolapi.dtos.CurrentGameInfoDto;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;
import java.util.NoSuchElementException;

public class GameInfoCommand implements BotCommand {

    private final RiotApiService riotApiService;

    public GameInfoCommand(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }

    @Override
    public String getName() {
        return "gameinfo";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException, InterruptedException {
        event.deferReply().queue();

        String region = event.getOption("region").getAsString();
        String name = event.getOption("name").getAsString();
        String tag = event.getOption("tag").getAsString();

        try {
            String puuid = riotApiService.getAccountByRiotId(region, tag, name).getPuuid();
            CurrentGameInfoDto currentGameInfoDto = riotApiService.getCurrentGameInfo(puuid);
            event.getHook()
                    .sendMessageEmbeds(
                            new EmbedBuilder()
                                    .setTitle(currentGameInfoDto.getGameMode() + " " + currentGameInfoDto.getGameType() + " " +  currentGameInfoDto.getPlatformId())
                                    .addField("Players", currentGameInfoDto.getParticipants().toString(), false)
                                    .build()
                    )
                    .queue();
        } catch (NoSuchElementException e) {
            event.getHook()
                    .sendMessage("❌ Summoner “" + name + "#" + tag + "” not found.")
                    .setEphemeral(true)
                    .queue();
        } catch (Exception e) {
            event.getHook()
                    .sendMessage("Error fetching from Riot: " + e.getMessage())
                    .setEphemeral(true)
                    .queue();

        }
    }
}
